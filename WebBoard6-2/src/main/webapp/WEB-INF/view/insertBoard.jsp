<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
<style type="text/css">
.content {
	text-align: center;
}

table {
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
}

tr, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<header>
		<h1 class="content">게시글 쓰기</h1>
		<h3 align="right">${user.name}님, 환영합니다! &nbsp;
			<button type="button" onclick="location.href='logout'">로그아웃</button>
		</h3>
		<hr>
	</header>
	<article>
		<form action="insertBoard" method="POST" enctype="multipart/form-data">
			<table>
				<tr>
					<td bgcolor="orange" width="70" align="center">제목</td>
					<td align="left">
						<input type="text" name="title" size="50" />
					</td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70" align="center">작성자</td>
					<td align="left">
						<input type="text" name="writer" size="15" value="${user.id}" />
					</td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70" align="center">내용</td>
					<td align="left">
						<textarea name="content" cols="40" rows="10"></textarea>
					</td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70" align="center">첨부파일</td>
					<td align="left">
						<input type="file" name="uploadFile"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70" align="center">첨부파일</td>
					<td align="left">
						<input type="file" name="uploadFile"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="글 등록" /></td>
				</tr>
				
			</table>
		</form>
		<hr>
		<div class="content">
			<button type="button" onclick="location.href='getBoardList'">글 목록</button>
		</div>
	</article>
</body>
</html>