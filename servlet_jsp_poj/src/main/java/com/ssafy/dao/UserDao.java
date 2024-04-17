package com.ssafy.dao;

import com.ssafy.model.dto.User;

public interface UserDao {
	boolean registUser(User user);
	User getUserById(String id);
}
