<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.springlab.biz.board.dao.*, com.springlab.biz.board.domain.*, java.util.List" %>
<%@ page import="com.springlab.biz.user.domain.*" %>
<%
	UserDO user = (UserDO)session.getAttribute("user");
%>
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
		<h3 align="right"><%= user.getName() %> 님, 환영합니다! &nbsp;
			<button type="button" onclick="location.href='logout.do'">로그아웃</button>
		</h3>
		<hr>
	</header>
	<article>
		<form action="insertBoard.do" method="POST">
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
						<input type="text" name="writer" size="15" value="<%= user.getId() %>" />
					</td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70" align="center">내용</td>
					<td align="left">
						<textarea name="content" cols="40" rows="10"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="글 등록" /></td>
				</tr>
			</table>
		</form>
		<hr>
		<div class="content">
			<button type="button" onclick="location.href='getBoardList.do'">글 목록</button>
		</div>
	</article>
</body>
</html>