<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
</head>
<body>

<form action="/novice/Login" method="post">
		Email: <input type="text" name = "mail">
		Password: <input type="password" name = "pass">
		<input type="submit" value="Login">
</form>

<input type="button"  value="戻る" onclick="history.back()">
<a href="/novice/index.jsp">Topページ</a>

</body>
</html>