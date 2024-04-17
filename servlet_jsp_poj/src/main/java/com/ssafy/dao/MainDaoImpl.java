package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import com.ssafy.model.dto.Video;
import com.ssafy.util.DBUtil;

public class MainDaoImpl implements MainDao {

	private static final DBUtil util = DBUtil.getInstance();
	private static MainDaoImpl instance;
	private static List<Video> list;

	private MainDaoImpl() {
		System.out.println("created.");
		
	    list = new ArrayList<Video>();
		list.add(new Video(1, "gMaB-fG4u4g", "ThankyouBUBU", 10, "전신", "전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]"));
		list.add(new Video(2, "swRNeYw1JkY", "ThankyouBUBU", 12, "전신", "하루 15분! 전신 칼로리 불태우는 다이어트 운동"));
		list.add(new Video(3, "54tTYO-vU2E", "ThankyouBUBU", 20, "상체", "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]"));
		list.add(new Video(4, "QqqZH3j_vH0", "ThankyouBUBU", 2, "상체", "상체비만 다이어트 최고의 운동 [상체 핵매운맛]"));
		list.add(new Video(5, "tzN6ypk6Sps", "김강민", 17, "하체", "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]"));
		list.add(new Video(6, "u5OgcZdNbMo", "GYM종국", 120, "하체", "저는 하체 식주의자 입니다"));
		list.add(new Video(7, "PjGcOP-TQPE", "ThankyouBUBU", 1, "복부", "11자복근 복부 최고의 운동 [복근 핵매운맛]"));
		list.add(new Video(8, "7TLk7pscICk", "SomiFit", 0, "복부", "(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)"));

	    addVideoList(list);
	    System.out.println(list.toString());
	}


	public void addVideoList(List<Video> videoList) {
		System.out.println("add video list");
	    try (Connection conn = util.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(
	             "INSERT INTO videos (videoId, youtubeId, channelName, viewCnt, fitPartName, title) VALUES (?, ?, ?, ?, ?, ?)")) {
	        
	        for (Video video : videoList) {
	            pstmt.setInt(1, video.getVideoId());
	            pstmt.setString(2, video.getYoutubeId());
	            pstmt.setString(3, video.getChannelName());
	            pstmt.setInt(4, video.getViewCnt());
	            pstmt.setString(5, video.getFitPartName());
	            pstmt.setString(6, video.getTitle());
	            pstmt.addBatch(); // 일괄 삽입을 위해 배치에 추가
	        }
	        
	        pstmt.executeBatch(); // 일괄 삽입 실행
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	public static MainDao getInstance() {
		if (instance == null)
			instance = new MainDaoImpl();
		return instance;
	}

	public List<Video> selectInterestViewFitVideo() {
		Collections.sort(list, new Comparator<Video>() {
			@Override
			public int compare(Video v1, Video v2) {
				return v2.getViewCnt() - v1.getViewCnt();
			}
		});
		return list;
	}

	public List<Video> selectPartFitVideo(String part) {
		List<Video> part_list = new ArrayList<>();

		for (Video video : list) {
			if (video.getFitPartName().equals(part)) {
				part_list.add(video);
			}
		}
		return part_list;
	}
}
