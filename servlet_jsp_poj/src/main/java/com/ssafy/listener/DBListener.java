package com.ssafy.listener;

import com.ssafy.dao.MainDao;
import com.ssafy.dao.MainDaoImpl;
import com.ssafy.dao.ReviewDao;
import com.ssafy.dao.ReviewDaoImpl;
import com.ssafy.dao.UserDao;
import com.ssafy.dao.UserDaoImpl;
import com.ssafy.util.DBUtil;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DBListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	    // DAO 인스턴스 생성
	    MainDao mainDao = MainDaoImpl.getInstance();
	    ReviewDao reviewDao = ReviewDaoImpl.getInstance();
	    UserDao userDao = UserDaoImpl.getInstance();

	    // 서블릿 컨텍스트에 DAO 인스턴스 저장
	    sce.getServletContext().setAttribute("mainDao", mainDao);
	    sce.getServletContext().setAttribute("reviewDao", reviewDao);
	    sce.getServletContext().setAttribute("userDao", userDao);
	}
}
