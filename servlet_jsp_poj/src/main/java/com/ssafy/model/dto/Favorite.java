package com.ssafy.model.dto;

public class Favorite {

	private int favoriteId;
	private int videoId;
	private String userId;

	public Favorite() {
	}

	public Favorite(int favoriteId, int videoId, String userId) {
		super();
		this.favoriteId = favoriteId;
		this.videoId = videoId;
		this.userId = userId;
	}

	public int getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
