package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.model.dto.Video;
import com.ssafy.util.DBUtil;

public class FavoriteDaoImpl implements FavoriteDao {

	private static FavoriteDaoImpl instance;
	private final DBUtil util = DBUtil.getInstance();

	private FavoriteDaoImpl() {
	}

	public static FavoriteDaoImpl getInstance() {
		if (instance == null) {
			instance = new FavoriteDaoImpl();
		}
		return instance;
	}

	@Override
	public void favorite(String userId, int videoId) {
		try (Connection conn = util.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("INSERT INTO favorites (videoId, userId) VALUES (?,?)")) {
			pstmt.setInt(1, videoId);
			pstmt.setString(2, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unfavorite(String userId, int videoId) {
		try (Connection conn = util.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("DELETE FROM favorites WHERE userId= ? AND videoId=?")) {
			pstmt.setString(1, userId);
			pstmt.setInt(2, videoId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isFavorite(String userId, int videoId) {
		boolean favoriteFlag = false;

		try (Connection conn = util.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("SELECT * FROM favorites WHERE userId=? AND videoId=?")) {
			pstmt.setString(1, userId);
			pstmt.setInt(2, videoId);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) favoriteFlag = true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return favoriteFlag;
	}
	
	
	public Map<Integer, Boolean> getFavoritesStatus(String userId, List<Video> videos) {
        Map<Integer, Boolean> favoriteStatusMap = new HashMap<>();
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT videoId FROM favorites WHERE userId=? AND videoId=?")) {
            for (Video video : videos) {
                pstmt.setString(1, userId);
                pstmt.setInt(2, video.getVideoId());
                try (ResultSet rs = pstmt.executeQuery()) {
                    favoriteStatusMap.put(video.getVideoId(), rs.next()); // 찜 여부를 Map에 저장
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteStatusMap;
    }
}
