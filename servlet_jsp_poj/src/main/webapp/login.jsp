<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <%@include file="/header.jsp" %>
</head>
<body>
	
    <h2>로그인</h2>
    <form action="main" method="post">
        <input type="hidden" name="action" value="login">
        <div>
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" required>
        </div>
        <div>
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">로그인</button>
    </form>
    <p>아직 회원이 아니신가요? <a href="main?action=registForm">회원가입</a></p>
</body>
</html>
