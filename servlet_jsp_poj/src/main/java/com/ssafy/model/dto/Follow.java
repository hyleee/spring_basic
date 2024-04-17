package com.ssafy.model.dto;

public class Follow {
	private int followId;
    private String followingId; // 하는 놈
    private String followerId; // 받는 놈
    
    public Follow() {}
    
	public Follow(int followId, String followingId, String followerId) {
		super();
		this.followId = followId;
		this.followingId = followingId;
		this.followerId = followerId;
	}

	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	@Override
	public String toString() {
		return "Follow [followId=" + followId + ", followingId=" + followingId + ", followerId=" + followerId + "]";
	}
    
    
}
