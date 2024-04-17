package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.model.dto.User;
import com.ssafy.util.DBUtil;

public class UserDaoImpl implements UserDao {
	
	private static UserDaoImpl instance;
	private final DBUtil util = DBUtil.getInstance();

    private UserDaoImpl() {}

    public static UserDaoImpl getInstance() {
    	if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean registUser(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean success = false;

        try {
            conn = util.getConnection();
            
            // 이미 존재하는 ID인지 확인하는 쿼리
            String checkSql = "SELECT userId FROM users WHERE userId = ?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setString(1, user.getUserId());
            rs = pstmt.executeQuery();
            
            // 이미 존재하는 ID라면 가입 실패
            if (rs.next()) {
                return false;
            }
            
            String sql = "INSERT INTO users (userId, password, username, email) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getEmail());
            

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }

        return success;
    }
    
    @Override
    public User getUserById(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = util.getConnection();
            String sql = "SELECT * FROM users WHERE userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getString("userId"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }

        return user;
    }
}
