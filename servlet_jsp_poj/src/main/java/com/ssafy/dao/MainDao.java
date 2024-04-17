package com.ssafy.dao;

import java.util.List;

import com.ssafy.model.dto.Video;

public interface MainDao {
	public void addVideoList(List<Video> videoList);
	public abstract List<Video> selectInterestViewFitVideo();
	public abstract List<Video> selectPartFitVideo(String part);
}
