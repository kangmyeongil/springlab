<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>산술 연산 오류 화면</title>
<style>
table {
	margin: 0 auto; 
	width: 800px; 
	border: 1px solid black; 
	cellspacing: 0; 
	cellpadding: 0;
}
</style>
</head>
<body>
	<div align="center">
		<img src='${pageContext["request"]["contextPath"]}/resources/images/error.png' />	
	</div>
	<table>
		<tr><td align="center" bgcolor="orange"><b>기본 오류 화면</b></td></tr>
	</table>
	<br>
	<table>
		<tr>
			<td align="center">
			<br><br><br>
			Message: ${exception.message}
			<br><br><br>
			</td>
		</tr>
	</table>
</body>
</html>