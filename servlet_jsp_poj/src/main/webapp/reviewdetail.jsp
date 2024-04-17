<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 목록</title>

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

h2 {
    text-align: center;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

th, td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
    text-align: center;
}

th {
    background-color: #f2f2f2;
    white-space: nowrap;
}

td {
    background-color: #fff;
}

a.btn {
    display: inline-block;
    padding: 8px 16px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-decoration: none;
}

a.btn:hover {
    background-color: #0056b3;
}

.btn-container {
    text-align: center;
}
</style>
</head>
<body>
    <%@include file="/header.jsp" %>
    <div class="container">
        <h2>리뷰 목록</h2>
        <table>
            <tr style="text-align:center">
                <th>리뷰 ID</th>
                <th>내용</th>
                <th>작성자</th>
                <th>수정</th>
                <th>삭제</th>
            </tr>
            <c:forEach items="${reviews}" var="review">
                <tr>
                    <td>${review.reviewId}</td>
                    <td>${review.content}</td>
                    <td>
                        <!-- 작성자 정보로 이동하는 링크 -->
                        <c:choose>
                            <c:when test="${sessionScope.user.userId eq review.userId}">
                                <a href="main?action=mypage&userId=${review.userId}" class="btn">내 정보</a>
                            </c:when>
                            <c:otherwise>
                                <a href="main?action=mypage&userId=${review.userId}" class="btn">작성자 정보</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <!-- 작성자와 현재 로그인한 사용자가 같은 경우에만 수정 버튼을 표시 -->
                        <c:if test="${sessionScope.user.userId eq review.userId}">
                            <a href="main?action=updateReviewForm&reviewId=${review.reviewId}" class="btn">수정</a>
                        </c:if>
                    </td>
                    <td>
                        <!-- 작성자와 현재 로그인한 사용자가 같은 경우에만 삭제 버튼을 표시 -->
                        <c:if test="${sessionScope.user.userId eq review.userId}">
                            <a href="main?action=deleteReview&reviewId=${review.reviewId}" class="btn">삭제</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <div class="btn-container">
            <c:if test="${empty sessionScope.user}">
                <a href="main?action=loginForm" class="btn">로그인 후 리뷰 추가</a>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <a href="main?action=addReviewForm&videoId=${requestScope.videoId}" class="btn">리뷰 추가</a>
            </c:if>
            <a href="main?action=list" class="btn">영상 목록으로 돌아가기</a>
            <a href="./" class="btn">홈으로</a>
        </div>
    </div>
</body>
</html>
