package com.ssafy.model.dto;

public class Review {
	private int reviewId;
	private int videoId;
	private String content;
	private String userId;

	public Review() {
	}

	public Review(int reviewId, int videoId, String content, String userId) {
		super();
		this.reviewId = reviewId;
		this.videoId = videoId;
		this.content = content;
		this.userId = userId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", videoId=" + videoId + ", content=" + content + ", userId=" + userId
				+ "]";
	}

}
