<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.model.dto.User"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<style>
.container {
	max-width: 50rem;
}
</style>
</head>

<header>
	<nav class="navbar navbar-expand-lg navbar-light" style="margin: 0 20px">
		<a class="navbar-brand" href="#">Logo</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="main.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#">헬스장 찾기</a></li>
			</ul>
			<ul class="navbar-nav mb-2 mb-lg-0">
				<%
				if (request.getSession().getAttribute("user") != null) {
				%>
				<li class="nav-item"><span class="nav-link">환영합니다 <%=((User) request.getSession().getAttribute("user")).getUsername()%>님</span></li>
				<li class="nav-item"><a class="nav-link" href="main?action=mypage&userId=<%=((User) request.getSession().getAttribute("user")).getUserId()%>">마이페이지</a></li>
				<li class="nav-item"><a class="nav-link" href="main?action=logout">로그아웃</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link"
					href="main?action=regist">회원가입</a></li>
				<li class="nav-item"><a class="nav-link"
					href="main?action=login">로그인</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</nav>
</header>
<body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
    crossorigin="anonymous"></script>
</body>

</html>
