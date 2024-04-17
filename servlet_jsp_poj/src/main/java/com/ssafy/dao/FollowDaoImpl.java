package com.ssafy.dao;

import com.ssafy.model.dto.User;
import com.ssafy.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FollowDaoImpl implements FollowDao {
	
    private static FollowDaoImpl instance;
    private final DBUtil util = DBUtil.getInstance();

    private FollowDaoImpl() {}

    public static FollowDaoImpl getInstance() {
        if (instance == null) {
            instance = new FollowDaoImpl();
        }
        return instance;
    }
    
    @Override
    public void follow(String followingId, String followerId) {
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO follows (followingId, followerId) VALUES (?, ?)")) {
            pstmt.setString(1, followingId);
            pstmt.setString(2, followerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unfollow(String followingId, String followerId) {
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "DELETE FROM follows WHERE followingId = ? AND followerId = ?")) {
            pstmt.setString(1, followingId);
            pstmt.setString(2, followerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean isFollowing(String followingId, String followerId) {
        boolean followingFlag = false;
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM follows WHERE followingId = ? AND followerId = ?")) {
            pstmt.setString(1, followingId);
            pstmt.setString(2, followerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                	followingFlag = true; // 팔로우 중인 경우 true로 설정
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return followingFlag;
    }
    
    @Override
    public List<User> getFollowers(String userId) { // 해당 id'를' '팔로우하는' 사람 목록
        List<User> followers = new ArrayList<>();
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
            		 "SELECT * FROM users WHERE userId IN (SELECT followingId FROM follows WHERE followerId = ?)")) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User follower = new User();
                    follower.setUserId(rs.getString("userId"));
                    follower.setPassword(rs.getString("password"));
                    follower.setUsername(rs.getString("username"));
                    follower.setEmail(rs.getString("email"));
                    followers.add(follower);
                }
            }
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return followers;
    }
    
    @Override
    public List<User> getFollowings(String userId) { // 해당 id'가' '팔로우하는' 사람 목록
        List<User> followings = new ArrayList<>();
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
            		 "SELECT * FROM users WHERE userId IN (SELECT followerId FROM follows WHERE followingId = ?)")) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User following = new User();
                    following.setUserId(rs.getString("userId"));
                    following.setPassword(rs.getString("password"));
                    following.setUsername(rs.getString("username"));
                    following.setEmail(rs.getString("email"));
                    followings.add(following);
                }
            }
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return followings;
    }
}
