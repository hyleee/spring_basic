package com.ssafy.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.model.dto.Video;

public interface FavoriteDao {
	
	void favorite(String userId, int videoId);
	void unfavorite(String userId, int videoId);
	boolean isFavorite(String userId, int videoId);
	public Map<Integer, Boolean> getFavoritesStatus(String userId, List<Video> videos);
}
