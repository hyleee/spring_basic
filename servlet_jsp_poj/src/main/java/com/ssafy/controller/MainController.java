package com.ssafy.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.dao.FavoriteDaoImpl;
import com.ssafy.dao.FollowDao;
import com.ssafy.dao.FollowDaoImpl;
import com.ssafy.dao.MainDao;
import com.ssafy.dao.MainDaoImpl;
import com.ssafy.dao.ReviewDao;
import com.ssafy.dao.ReviewDaoImpl;
import com.ssafy.dao.UserDao;
import com.ssafy.dao.UserDaoImpl;
import com.ssafy.model.dto.Review;
import com.ssafy.model.dto.User;
import com.ssafy.model.dto.Video;
import com.ssafy.util.DBUtil;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainController extends HttpServlet {

	private MainDao mainDao;
	private ReviewDao reviewDao;
	private UserDao userDao;
	private FollowDao followDao;

	@Override
	public void init() throws ServletException {
		super.init();
		// 서블릿 컨텍스트에서 DAO 인스턴스를 가져옵니다.
		this.mainDao = (MainDao) getServletContext().getAttribute("mainDao");
		this.reviewDao = (ReviewDao) getServletContext().getAttribute("reviewDao");
		this.userDao = (UserDao) getServletContext().getAttribute("userDao");
		this.followDao = (FollowDao) FollowDaoImpl.getInstance();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "home": // 홈으로
			redirectToHome(request, response);
			break;
		case "list":
			doList(request, response);
			break;
		case "reviewList":
			doReviewList(request, response);
			break;
		case "addReviewForm":
			showAddReviewForm(request, response);
			break;
		case "addReview":
			addReview(request, response);
			break;
		case "updateReviewForm":
			showUpdateReviewForm(request, response);
			break;
		case "updateReview":
			updateReview(request, response);
			break;
		case "deleteReview":
			deleteReview(request, response);
			break;
		case "registForm": // 회원가입 폼 요청 시
			showRegistForm(request, response);
			break;
		case "regist": // 회원가입 요청 시
			doRegist(request, response);
			break;
		case "loginForm": // 로그인 폼 요청 시
			showLoginForm(request, response);
			break;
		case "login": // 로그인 요청 시
			doLogin(request, response);
			break;
		case "logout": // 로그아웃 요청 시
			doLogout(request, response);
			break;
		case "mypage":
			showMyPage(request, response);
			break;
		case "follow":
			followUser(request, response);
			break;
		case "unfollow":
			unfollowUser(request, response);
			break;
		case "toggleFavorite":
			toggleFavorite(request, response);
			break;
//		case "favorite":
//			favoriteVideo(request, response);
//			break;
//		case "unfavorite":
//			unfavoriteVideo(request, response);
		}
	}

	// 홈으로 돌아가는 기능 구현
	private void redirectToHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 홈 페이지로 리다이렉트합니다.
		response.sendRedirect("main.jsp");
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String part = request.getParameter("part");
		List<Video> videos;
		if (part == null) {
			videos = mainDao.selectInterestViewFitVideo();
		} else {
			videos = mainDao.selectPartFitVideo(part);
		}
		User user = (User) request.getSession().getAttribute("user");
		Map<Integer, Boolean> favoriteStatusMap;
		if (user != null) {
			favoriteStatusMap = FavoriteDaoImpl.getInstance().getFavoritesStatus(user.getUserId(), videos);
		} else {
			favoriteStatusMap = new HashMap<>(); // 사용자가 로그인하지 않은 경우 빈 Map 사용
		}
		request.setAttribute("videos", videos);
		request.setAttribute("favoriteStatusMap", favoriteStatusMap);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

	private void doReviewList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		request.setAttribute("videoId", videoId);
		List<Review> reviews = reviewDao.selectAllReviewsByVideoId(videoId);
		request.setAttribute("reviews", reviews);
		request.getRequestDispatcher("/reviewdetail.jsp").forward(request, response);
	}

	private void showAddReviewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		request.setAttribute("videoId", videoId);
		request.getRequestDispatcher("/addReview.jsp").forward(request, response);
	}

	private void addReview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자 정보가 없으면 로그인 폼으로 이동
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("main?action=loginForm");
			return;
		}

		int videoId = Integer.parseInt(request.getParameter("videoId"));
		String content = request.getParameter("content");
		Review review = new Review();
		review.setVideoId(videoId);
		review.setContent(content);
		review.setUserId(user.getUserId());
		reviewDao.insertReview(review);
		response.sendRedirect("main?action=reviewList&videoId=" + videoId);
	}

	private void showUpdateReviewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		Review review = reviewDao.selectReviewById(reviewId);
		request.setAttribute("reviewId", reviewId);
		request.setAttribute("content", review.getContent());
		request.getRequestDispatcher("/updateReview.jsp").forward(request, response);
	}

	private void updateReview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 사용자 정보가 없으면 로그인 폼으로 이동
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("main?action=loginForm");
			return;
		}

		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		Review review = reviewDao.selectReviewById(reviewId);

		// 사용자가 리뷰의 작성자인지 확인
		if (!review.getUserId().equals(user.getUserId())) {
			response.sendRedirect(request.getRequestURI()); // 해당 유저가 작성한 리뷰가 아닌 경우 변화x
			return;
		}

		String content = request.getParameter("content");
		review.setContent(content);
		reviewDao.updateReview(review);
		int videoId = review.getVideoId();
		response.sendRedirect("main?action=reviewList&videoId=" + videoId);
	}

	private void deleteReview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자 정보가 없으면 로그인 폼으로 이동
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("main?action=loginForm");
			return;
		}

		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		Review review = reviewDao.selectReviewById(reviewId);

		// 사용자가 리뷰의 작성자인지 확인
		if (!review.getUserId().equals(user.getUserId())) {
			response.sendRedirect(request.getRequestURI()); // 해당 유저가 작성한 리뷰가 아닌 경우 변화x
			return;
		}

		int videoId = review.getVideoId();
		reviewDao.deleteReview(reviewId);
		response.sendRedirect("main?action=reviewList&videoId=" + videoId);
	}

	// 회원가입 폼 보여주기
	private void showRegistForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/regist.jsp").forward(request, response);
	}

	// 회원가입 처리
	private void doRegist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String email = request.getParameter("email");

		// 회원 정보 생성
		User user = new User();
		user.setUserId(id);
		user.setPassword(password);
		user.setUsername(username);
		user.setEmail(email);

		// 회원가입 처리
		boolean success = false;
		try {
			success = userDao.registUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (success) {
			// 회원가입 성공 시 로그인 페이지로 이동
			response.sendRedirect("main?action=loginForm");
		} else {
			// 회원가입 실패 시 다시 회원가입 폼으로 이동
			response.sendRedirect("main?action=registForm");
		}
	}

	// 로그인 폼 보여주기
	private void showLoginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	// 로그인 처리
	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		User user = userDao.getUserById(id);

		if (user != null && user.getPassword().equals(password)) {

			request.getSession().setAttribute("user", user);
			response.sendRedirect("main.jsp");
		} else {

			response.sendRedirect("main?action=loginForm");
		}
	}

	// 로그아웃 처리
	private void doLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션 종료
		request.getSession().invalidate();
		response.sendRedirect("main.jsp");
	}

	// 마이페이지를 보여주는 메서드 구현
	private void showMyPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User loginUser = (User) request.getSession().getAttribute("user");

		if (loginUser == null) {
			response.sendRedirect("main?action=loginForm");
			return;
		}

		String targetUserId = request.getParameter("userId");
		User targetUser;

		if (targetUserId == null || targetUserId.isEmpty() || targetUserId.equals(loginUser.getUserId())) {
			targetUser = loginUser;
		} else {
			targetUser = userDao.getUserById(targetUserId);

			if (targetUser == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 사용자를 찾을 수 없습니다.");
				return;
			}
		}

		List<User> followers = followDao.getFollowers(targetUser.getUserId());
		List<User> followings = followDao.getFollowings(targetUser.getUserId());
		boolean isFollowing = followDao.isFollowing(loginUser.getUserId(), targetUser.getUserId());

		request.setAttribute("user", targetUser);
		request.setAttribute("followers", followers);
		request.setAttribute("followings", followings);
		request.setAttribute("isFollowing", isFollowing);

		request.getRequestDispatcher("/mypage.jsp").forward(request, response);
	}

	// 팔로우 기능 메서드 구현
	private void followUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			String userIdToFollow = request.getParameter("userIdToFollow");
			// 여기에 팔로우 기능을 구현합니다.
			followDao.follow(user.getUserId(), userIdToFollow);
			// 팔로우 기능 실행 후 현재 페이지에 남음
			response.sendRedirect("main?action=mypage&userId=" + userIdToFollow);
		} else {
			response.sendRedirect("main?action=loginForm");
		}
	}

	// 팔로우 취소 기능 구현
	private void unfollowUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			String userIdToUnfollow = request.getParameter("userIdToUnfollow");
			// 여기에 팔로우 취소 기능을 구현합니다.
			followDao.unfollow(user.getUserId(), userIdToUnfollow);
			// 팔로우 취소 기능 실행 후 현재 페이지에 남음
			response.sendRedirect("main?action=mypage&userId=" + userIdToUnfollow);
		} else {
			response.sendRedirect("main?action=loginForm");
		}
	}

//	// 영상 찜하기 구현
//	private void favoriteVideo(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		User user = (User) request.getSession().getAttribute("user");
//		if (user != null) {
//			int videoId = Integer.parseInt(request.getParameter("videoId"));
//			// 찜
//			FavoriteDaoImpl.getInstance().favorite(user.getUserId(), videoId);
//			// 찜하기 기능 실행 후 현재 페이지에 남음
//			response.sendRedirect("main?action=list");
//		} else {
//			response.sendRedirect("main?action=loginForm");
//		}
//
//	}
//
//	// 영상 찜 취소하기 구현
//	private void unfavoriteVideo(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		User user = (User) request.getSession().getAttribute("user");
//		if (user != null) {
//			int unfavoriteVideoId = Integer.parseInt(request.getParameter("videoId"));
//			// 찜 취소
//			FavoriteDaoImpl.getInstance().unfavorite(user.getUserId(), unfavoriteVideoId);
//			// 찜 취소 기능 실행 후 현재 페이지에 남음
//			response.sendRedirect("main?action=list");
//		} else {
//			response.sendRedirect("main?action=loginForm");
//		}
//	}


	private void toggleFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			int videoId = Integer.parseInt(request.getParameter("videoId"));
			// 상태 확인
			boolean isFavorite = FavoriteDaoImpl.getInstance().isFavorite(user.getUserId(), videoId);

			if (isFavorite) // 이미 찜한 경우, 찜 취소 처리
				FavoriteDaoImpl.getInstance().unfavorite(user.getUserId(), videoId);
			else // 찜하지 않은 경우, 찜 처리
				FavoriteDaoImpl.getInstance().favorite(user.getUserId(), videoId);
			// 기능 실행 후 현재 페이지에 남음
			response.sendRedirect("main?action=list");
		} else {
			response.sendRedirect("main?action=loginForm");
		}
	}

}
