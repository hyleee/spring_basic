<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>영상 목록</title>
<%@include file="/header.jsp"%>
<style>
.container {
	max-width: 90%;
	margin: 20px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
	grid-gap: 20px;
}

.video-container {
	margin-bottom: 20px;
}

.video {
	position: relative;
	padding-bottom: 56.25%; /* 16:9 비율 */
	height: 0;
	overflow: hidden;
	border-radius: 10px;
	cursor: pointer; /* 커서를 포인터로 변경하여 사용자에게 재생 가능함을 알림 */
}

.video iframe {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	border-radius: 10px;
}

.video-info {
	padding: 10px;
	background-color: rgba(255, 255, 255, 0.8);
	border-radius: 5px;
	margin-bottom: 10px;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
}

.video-info .title {
	font-size: 18px;
	margin-bottom: 5px;
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 2; /* 두 줄까지 표시 */
	-webkit-box-orient: vertical;
}

.video-info span {
	font-size: 14px;
	color: #666;
}

.video-info .metadata {
	display: flex;
	justify-content: space-between; /* 왼쪽과 오른쪽 끝에 붙이기 위해 추가 */
	align-items: center;
	width: 100%; /* 추가된 내용 */
}

.video-info a {
	font-size: 15px;
	text-decoration: none;
	color: blue;
}
</style>
</head>
<body>
	<div class="container">
		<c:forEach items="${videos}" var="video">
			<div class="video-container">
				<div class="video">
					<iframe
						src="https://www.youtube.com/embed/${video.youtubeId}?enablejsapi=1"
						frameborder="0" allowfullscreen></iframe>
				</div>
				<div class="video-info">
					<div class="title">${video.title}</div>
					<div class="metadata">
						<span>조회수 ${video.viewCnt}</span> <a
							href="main?action=reviewList&videoId=${video.videoId}">리뷰 목록</a>
						<!-- 찜하기/찜 취소 버튼 추가 -->
						<a href="main?action=toggleFavorite&videoId=${video.videoId}"
							class="btn"> ${favoriteStatusMap[video.videoId] ? '찜 취소' : '찜하기'}
						</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
