package com.example.herosoft.springclouddemo.common.domain.entity;

import com.example.herosoft.springclouddemo.common.domain.enums.LikedStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name="userlike")
public class UserLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String likedUserId;
    private String likedPostId;
    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    public UserLike(){}

    public UserLike(String likedUserId, String likedPostId, Integer status) {
        this.likedUserId = likedUserId;
        this.likedPostId = likedPostId;
        this.status = status;
    }
}
