package com.example.herosoft.springclouddemo.redis.service;

import com.example.herosoft.springclouddemo.common.domain.entity.UserLike;
import com.example.herosoft.springclouddemo.common.domain.model.LikedCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserLikeRedisService {
    void saveLikedToRedis(String likedUserId, String likedPostUserId);

    void unlikdedFromRedis(String likeUserId, String likedPostUserId);

    void deleteLikedFromRedis(String likedUserId,String likedPostUserId);

    void incrementLikedCount(String likedUserId);

    void decrementLikedCount(String likedUserId);

    List<UserLike> getLikedDataFromRedis();

    List<LikedCountDTO> getLikedCountDTOFromRedis();

    UserLike save(UserLike userLike);

    List<UserLike> saveAll(List<UserLike> list);

    Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable);

    Page<UserLike> getLikedListByLikedPostUserId(String likedPostUserId, Pageable pageable);

    UserLike getByLikedUserIdAndLikedPostUserId(String likeUserId, String likedPostUserId);

    void transLikedFromRedisToDB();

    void transLikedCountFromRedisToDB();

}
