<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<h3 align="right">${user.name} 님, 환영합니다! &nbsp;
			<button type="button" onclick="location.href='logout'">로그아웃</button>
		</h3>
		<hr>
	</header>
	<article>
		<form action="getBoardList" method="POST">
			<table>
				<tr>
					<td align="right">
						<select name="searchCondition">
						  <c:forEach items="${conditionMap}" var="option">
							<option value="${option.key}">${option.value}</option>
					      </c:forEach>
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
        <c:forEach items="${board_list }" var="board">
			<tr>
				<td>${board.seq }</td>
				<td align="left"><a href="getBoard?seq=${board.seq} ">
					${board.title }</a></td>
				<td>${board.writer }</td>
				<td>${board.regDate }</td>
				<td>${board.cnt }</td>
			</tr>	
		</c:forEach>	
		</table>
		<div class="content">
			<br>
			<button type="button" onclick="location.href='insertBoard'">새글 등록</button>
		</div>
	</article>
</body>
</html>