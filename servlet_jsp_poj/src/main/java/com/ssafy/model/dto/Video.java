package com.ssafy.model.dto;

public class Video {

	private int videoId;
	private String youtubeId;
	private String channelName;
	private int viewCnt;
	private String fitPartName;

	public Video(int videoId, String youtubeId, String channelName, int viewCnt, String fitPartName, String title) {
		super();
		this.videoId = videoId;
		this.youtubeId = youtubeId;
		this.channelName = channelName;
		this.viewCnt = viewCnt;
		this.fitPartName = fitPartName;
		this.title = title;
	}

	private String title;

	public Video() {
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFitPartName() {
		return fitPartName;
	}

	public void setFitPartName(String fitPartName) {
		this.fitPartName = fitPartName;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", title=" + title + ", fitPartName=" + fitPartName + ", youtubeId="
				+ youtubeId + ", channelName=" + channelName + ", viewCnt=" + viewCnt + "]";
	}

}
