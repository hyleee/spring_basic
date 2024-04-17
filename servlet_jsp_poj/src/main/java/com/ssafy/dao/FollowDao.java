package com.ssafy.dao;

import java.util.List;

import com.ssafy.model.dto.Follow;
import com.ssafy.model.dto.User;

public interface FollowDao {
    void follow(String followingId, String followerId);
    void unfollow(String followingId, String followerId);
    boolean isFollowing(String followingId, String followerId);
    List<User> getFollowers(String userId);
    List<User> getFollowings(String userId);
}