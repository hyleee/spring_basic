package com.ssafy.dao;

import java.util.List;
import com.ssafy.model.dto.Review;

public interface ReviewDao {
    List<Review> selectAllReviewsByVideoId(int videoId);
    Review selectReviewById(int id);
    void insertReview(Review review);
    void updateReview(Review review);
    void deleteReview(int id);
}
