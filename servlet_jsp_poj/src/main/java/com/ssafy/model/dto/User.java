package com.ssafy.model.dto;

public class User {

	private String userId;
	private String password;
	private String username;
	private String email;

	public User() {
	}

	public User(String userId, String password, String username, String email) {
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", username=" + username + ", email=" + email
				+ "]";
	}

}
