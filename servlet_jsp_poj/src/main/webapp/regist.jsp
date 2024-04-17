<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <%@include file="/header.jsp" %>
</head>
<body>
    <h2>회원가입</h2>
    <form action="main" method="post">
    	<input type="hidden" name="action" value="regist">
    	<div>
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" required>
        </div>
        <div>
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <label for="username">사용자명</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" required>
        </div>
        <button type="submit">가입하기</button>
    </form>
</body>
</html>
