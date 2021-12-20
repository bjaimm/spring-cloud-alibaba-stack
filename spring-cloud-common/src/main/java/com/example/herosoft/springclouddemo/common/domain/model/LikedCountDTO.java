package com.example.herosoft.springclouddemo.common.domain.model;

import lombok.Data;

@Data
public class LikedCountDTO {
    private String likedUserId;
    private Integer likedCount;

    public LikedCountDTO(String likedUserId, Integer likedCount) {
        this.likedUserId = likedUserId;
        this.likedCount = likedCount;
    }
}
