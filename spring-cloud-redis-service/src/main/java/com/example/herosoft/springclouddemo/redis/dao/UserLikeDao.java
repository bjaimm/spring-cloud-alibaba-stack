package com.example.herosoft.springclouddemo.redis.dao;

import com.example.herosoft.springclouddemo.common.domain.entity.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikeDao extends JpaRepository<UserLike,Integer> {
    @Query(value="select * from userlike where liked_user_id = ?1 and liked_post_id =?2",nativeQuery = true)
    UserLike findByLikedUserIdAndLikedPostUserId(String likeUserId, String likedPostUserId);

    @Query(value = "select * from userlike where liked_user_id = ?1",nativeQuery = true)
    Page<UserLike> findByLikedUserId(String likedUserId, Pageable pageable);

    @Query(value = "select * from userlike where liked_post_id = ?1",nativeQuery = true)
    Page<UserLike> findByLikedPostUserId(String likedPostUserId, Pageable pageable);
}
