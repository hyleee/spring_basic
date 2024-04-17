<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }
    h2 {
        text-align: center;
        margin-top: 20px;
    }
    form {
        max-width: 600px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    label {
        font-weight: bold;
    }
    textarea {
        width: 100%;
        max-width: 600px; /* 최대 너비를 고정 */
        padding: 10px;
        margin-top: 5px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        resize: none;
        box-sizing: border-box; /* 패딩과 경계선을 포함하여 너비를 계산 */
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
    <h2>리뷰 수정</h2>
    <form action="main?action=updateReview" method="post">
        <input type="hidden" name="reviewId" value="${requestScope.reviewId}" />
        <label for="content">내용:</label><br>
        <textarea id="content" name="content" rows="4" cols="50">${requestScope.content}</textarea><br>
        <input type="submit" value="수정">
    </form>
    
    <a href="javascript:history.back()">뒤로 가기</a>
</body>
</html>
