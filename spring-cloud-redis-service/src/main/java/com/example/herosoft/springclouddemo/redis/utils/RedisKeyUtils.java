package com.example.herosoft.springclouddemo.redis.utils;

public class RedisKeyUtils {
    public static final String MAP_KEY_USER_LIKED="MAP_USER_LIKED";
    public static final String MAP_KEY_USER_LIKED_COUNT="MAP_USER_LIKED_COUNT";

    public static String getLikedKey(String likedUserId, String likedPostUserId){
        StringBuilder builder = new StringBuilder();

        builder.append(likedUserId);
        builder.append("::");
        builder.append(likedPostUserId);
        return builder.toString();
    }
}
