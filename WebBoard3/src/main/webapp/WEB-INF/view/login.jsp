<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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
<!-- <center>
 -->	
 	<header>
		<h1 class="content">로그인</h1>
		<hr>
	</header>
	<article>
		<form action="login.do" method="POST">
			<table>
				<tr>
					<td bgcolor="orange">아이디</td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">비밀번호</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인" />
					</td>
				</tr>
			</table>
		</form>
	</article>
	<footer>
		<hr>
	</footer>
<!-- </center>
 --></body>
</html>