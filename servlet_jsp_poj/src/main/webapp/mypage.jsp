<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.model.dto.User"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.container {
	max-width: 800px;
	margin: 20px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1, h2, h3 {
	text-align: left;
}

ul {
	list-style-type: none;
	padding: 0;
}

li {
	margin-bottom: 10px;
}

.button-group {
	display: flex;
	justify-content: space-between;
	margin-top: 20px;
}

.button {
	padding: 8px 16px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.button:hover {
	background-color: #0056b3;
}

.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

.modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
	max-width: 500px;
}

.modal {
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>
			<%
			User user = (User) request.getAttribute("user");
			User loginUser = (User) request.getSession().getAttribute("user");
			if (loginUser != null) {
				if (user.getUserId().equals(loginUser.getUserId())) {
			%>
			내 정보
			<%
			} else {
			%>
			<%=user.getUserId()%>의 정보
			<%
			}
			} else {
			%>
			사용자 정보를 불러오는 데 문제가 발생했습니다.
			<%
			}
			%>
		</h1>
		<br /> <br />
		<%
		if (user != null) {
		%>
		<h2>사용자 정보</h2>
		<ul>
			<li>ID: <%=user.getUserId()%></li>
			<li>이름: <%=user.getUsername()%></li>
			<li>Email: <%=user.getEmail()%></li>
		</ul>

		<div class="button-group">
			<%
			if (loginUser != null && !loginUser.getUserId().equals(user.getUserId())) {
				if ((Boolean) request.getAttribute("isFollowing")) {
			%>
			<form action="main" method="post">
				<input type="hidden" name="action" value="unfollow"> <input
					type="hidden" name="userIdToUnfollow" value="<%=user.getUserId()%>">
				<button class="button" type="submit">팔로우 취소</button>
			</form>
			<%
			} else {
			%>
			<form action="main" method="post">
				<input type="hidden" name="action" value="follow"> <input
					type="hidden" name="userIdToFollow" value="<%=user.getUserId()%>">
				<button class="button" type="submit">팔로우</button>
			</form>
			<%
			}
			}
			%>
			<form action="main" method="get">
				<input type="hidden" name="action" value="list">
				<button class="button" type="submit">리뷰 목록으로 돌아가기</button>
			</form>
		</div>

		<div class="button-group">
			<%
			if (loginUser != null && loginUser.getUserId().equals(user.getUserId())) {
			%>
			<form action="main" method="get">
				<input type="hidden" name="action" value="home">
				<button class="button" type="submit">홈으로 돌아가기</button>
			</form>
			<%
			}
			%>
		</div>

		<div class="button-group">
			<button class="button" onclick="showModal('followersModal')">팔로워
				목록 보기</button>
			<button class="button" onclick="showModal('followingsModal')">팔로잉
				목록 보기</button>
		</div>

		<!-- 팔로워 목록 모달 -->
		<div id="followersModal" class="modal">
			<div class="modal-content">
				<span class="close" onclick="closeModal('followersModal')">&times;</span>
				<h2>이 유저를 팔로우한 사람들 목록</h2>
				<ul>
					<%
					List<User> followers = (List<User>) request.getAttribute("followers");
					if (followers != null && !followers.isEmpty()) {
						for (User follower : followers) {
					%>
					<li>팔로워 ID: <%=follower.getUserId()%> / NAME: <%=follower.getUsername()%></li>
					<%
					}
					} else {
					%>
					<li>팔로우한 사람이 없습니다.</li>
					<%
					}
					%>
				</ul>
			</div>
		</div>

		<!-- 팔로잉 목록 모달 -->
		<div id="followingsModal" class="modal">
			<div class="modal-content">
				<span class="close" onclick="closeModal('followingsModal')">&times;</span>
				<h2>이 유저가 팔로우한 사람들 목록</h2>
				<ul>
					<%
					List<User> followings = (List<User>) request.getAttribute("followings");
					if (followings != null && !followings.isEmpty()) {
						for (User following : followings) {
					%>
					<li>팔로잉 ID: <%=following.getUserId()%> / NAME: <%=following.getUsername()%></li>
					<%
					}
					} else {
					%>
					<li>팔로우한 사람이 없습니다.</li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</div>
	<%
	} else {
	%>
	<p>사용자 정보를 불러오는 데 문제가 발생했습니다.</p>
	<%
	}
	%>
	</div>
	<script>
		function showModal(modalId) {
			var modal = document.getElementById(modalId);
			modal.style.display = "block";
		}

		function closeModal(modalId) {
			var modal = document.getElementById(modalId);
			modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target.classList.contains('modal')) {
				event.target.style.display = "none";
			}
		}
	</script>

</body>
</html>