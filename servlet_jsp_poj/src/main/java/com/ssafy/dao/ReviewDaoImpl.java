package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.dto.Review;
import com.ssafy.util.DBUtil;

public class ReviewDaoImpl implements ReviewDao {
	
    private static ReviewDaoImpl instance;
    private final DBUtil util = DBUtil.getInstance();

    private ReviewDaoImpl() {
    }

    public static ReviewDaoImpl getInstance() {
        if (instance == null) {
            instance = new ReviewDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Review> selectAllReviewsByVideoId(int videoId) {
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM reviews WHERE videoId = ?")) {
            pstmt.setInt(1, videoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Review review = new Review();
                    review.setReviewId(rs.getInt("reviewId"));
                    review.setVideoId(rs.getInt("videoId"));
                    review.setContent(rs.getString("content"));
                    review.setUserId(rs.getString("userId"));
                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public Review selectReviewById(int id) {
        Review review = null;
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM reviews WHERE reviewId = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    review = new Review();
                    review.setReviewId(rs.getInt("reviewId"));
                    review.setVideoId(rs.getInt("videoId"));
                    review.setContent(rs.getString("content"));
                    review.setUserId(rs.getString("userId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public void insertReview(Review review) {
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO reviews (reviewId, videoId, content, userId) VALUES (?, ?, ?, ?)")) {
            pstmt.setInt(1, review.getReviewId());
            pstmt.setInt(2, review.getVideoId());
            pstmt.setString(3, review.getContent());
            pstmt.setString(4, review.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   

    @Override
    public void updateReview(Review review) {
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE reviews SET content = ? WHERE reviewId = ?")) {
            pstmt.setString(1, review.getContent());
            pstmt.setInt(2, review.getReviewId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReview(int id) {
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM reviews WHERE reviewId = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
