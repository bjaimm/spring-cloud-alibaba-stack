package com.example.herosoft.springclouddemo.redis.service.impl;

import com.example.herosoft.springclouddemo.common.domain.entity.UserLike;
import com.example.herosoft.springclouddemo.common.domain.enums.LikedStatusEnum;
import com.example.herosoft.springclouddemo.common.domain.model.LikedCountDTO;
import com.example.herosoft.springclouddemo.redis.dao.UserLikeDao;
import com.example.herosoft.springclouddemo.redis.service.UserLikeRedisService;
import com.example.herosoft.springclouddemo.redis.utils.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserLikeRedisServiceImpl implements UserLikeRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserLikeDao userLikeDao;

    @Override
    public void saveLikedToRedis(String likedUserId, String likedPostUserId) {
        String key = RedisKeyUtils.getLikedKey(likedUserId,likedPostUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED,key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikdedFromRedis(String likeUserId, String likedPostUserId) {
        String key = RedisKeyUtils.getLikedKey(likeUserId,likedPostUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED,key,LikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(String likedUserId, String likedPostUserId) {
        String key = RedisKeyUtils.getLikedKey(likedUserId,likedPostUserId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED,key);
    }

    @Override
    public void incrementLikedCount(String likedUserId) {
        stringRedisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT,likedUserId,1);
    }

    @Override
    public void decrementLikedCount(String likedUserId) {
        stringRedisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT,likedUserId,-1);
    }

    @Override
    public List<UserLike> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLike> list = new ArrayList<>();
        while(cursor.hasNext()){
            Map.Entry<Object,Object> entry = cursor.next();
            String key = (String) entry.getKey();
            String[] split=key.split("::");
            String likedUserId = split[0];
            String likedPostUserId=split[1];
            Integer likedStatus = (Integer) entry.getValue();

            UserLike userLike = new UserLike(likedUserId,likedPostUserId,likedStatus);
            list.add(userLike);
            deleteLikedFromRedis(likedUserId,likedPostUserId);
        }
        return list;
    }

    @Override
    public List<LikedCountDTO> getLikedCountDTOFromRedis() {
        Cursor<Map.Entry<Object,Object>> cursor = stringRedisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT,ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while(cursor.hasNext()){
            Map.Entry<Object,Object> entry = cursor.next();
            String key = (String)entry.getKey();
            Integer likedCount = Integer.parseInt( entry.getValue().toString());
            LikedCountDTO likedCountDTO = new LikedCountDTO(key,likedCount);

            list.add(likedCountDTO);
            //redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT,key);
        }
        return list;
    }


    @Override
    public UserLike save(UserLike userLike) {
        return userLikeDao.save(userLike);
    }

    @Override
    public List<UserLike> saveAll(List<UserLike> list) {
        return userLikeDao.saveAll(list);
    }

    @Override
    public Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable) {
        return userLikeDao.findByLikedUserId(likedUserId,pageable);
    }

    @Override
    public Page<UserLike> getLikedListByLikedPostUserId(String likedPostUserId, Pageable pageable) {
        return userLikeDao.findByLikedPostUserId(likedPostUserId,pageable);
    }

    @Override
    public UserLike getByLikedUserIdAndLikedPostUserId(String likeUserId, String likedPostUserId) {
        return userLikeDao.findByLikedUserIdAndLikedPostUserId(likeUserId,likedPostUserId);
    }

    @Override
    @Transactional
    public void transLikedFromRedisToDB() {
        List<UserLike> list = getLikedDataFromRedis();
        list.stream().forEach(userLike -> {
            UserLike existingUserLike = getByLikedUserIdAndLikedPostUserId(userLike.getLikedUserId(), userLike.getLikedPostId());
            if(existingUserLike!=null){
                userLike.setId(existingUserLike.getId());
            }
            save(userLike);
        });
    }

    @Override
    @Transactional
    public void transLikedCountFromRedisToDB() {

    }
}
