<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.springlab.biz.board.dao.*, com.springlab.biz.board.domain.*, java.util.List" %>
<%@ page import="com.springlab.biz.user.domain.*" %>
<%
	List<BoardDO> boardList = (List<BoardDO>)request.getAttribute("board_list");
	
	UserDO user = (UserDO)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<style type="text/css">
.content {
	text-align: center;
}

table {
	width: 700px;
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
		<h1 class="content">게시글 목록</h1>
		<h3 align="right"><%= user.getName() %> 님, 환영합니다! &nbsp;
			<button type="button" onclick="location.href='logout'">로그아웃</button>
		</h3>
		<hr>
	</header>
	<article>
		<form action="" method="POST">
			<table>
				<tr>
					<td align="right">
						<select name="searchCondition">
							<option value="TITLE">제목</option>
							<option value="CONTENT">내용</option>
							<option value="WRITER">작성자</option>
						</select>
						<input type="text" name="searchKeyword" />
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
	</article>
	<article>
		<table>
			<tr>
				<td bgcolor="orange" width="100">번호</td>
				<td bgcolor="orange" width="200">제목</td>
				<td bgcolor="orange" width="150">작성자</td>
				<td bgcolor="orange" width="150">등록일</td>
				<td bgcolor="orange" width="100">조회수</td>
			</tr>		
<%
	if (boardList != null && boardList.size() > 0) {
		for (BoardDO board : boardList) {
%>
			<tr>
				<td><%= board.getSeq() %></td>
				<td align="left"><a href="getBoard?seq=<%= board.getSeq() %>">
					<%= board.getTitle() %></a></td>
				<td><%= board.getWriter() %></td>
				<td><%= board.getRegDate() %></td>
				<td><%= board.getCnt() %></td>
			</tr>		
<%
		}
	}
%>
		</table>
		<div class="content">
			<br>
			<button type="button" onclick="location.href='insertBoard'">새글 등록</button>
		</div>
	</article>
</body>
</html>