<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 추가</title>
<%@include file="/header.jsp" %>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 20px; /* 위아래 여백 20px */
    }
    h2 {
        text-align: center;
        margin-top: 20px;
    }
    form {
        width: 600px; /* 폼의 너비를 600px로 설정 */
        margin: 0 auto; /* 가운데 정렬 */
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    label {
        font-weight: bold;
    }
    textarea {
    
        width: calc(100% - 20px); /* 너비를 100%로 설정하되 좌우 여백을 고려하여 20px 뺀 값으로 설정 */
        padding: 10px;
        margin-top: 5px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        resize: none;
    }
    input[type="submit"] {
        display: block;
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    input[type="submit"]:hover {
        background-color: #0056b3;
    }
    a {
        display: block;
        text-align: center;
        margin-top: 10px;
        text-decoration: none;
    }
</style>
</head>
<body>
    <h2>리뷰 추가</h2>
    <form action="main?action=addReview" method="post">
        <input type="hidden" name="videoId" value="${requestScope.videoId}" />
        <label for="content">내용:</label><br>
        <textarea id="content" name="content" rows="4" cols="50"></textarea><br>
        <input type="submit" value="추가">
    </form>
    
    <a href="main?action=reviewList&videoId=${requestScope.videoId}">뒤로 가기</a>
</body>
</html>
