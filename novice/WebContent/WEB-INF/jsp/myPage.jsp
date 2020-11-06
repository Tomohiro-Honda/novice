<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("/novice/Login?action=login");}%>

<% if(loginCustomer != null) {%>
<p><%=loginCustomer.getLastName() + " " + loginCustomer.getFirstName() %>様のページ</p>
<a href="/novice/AccountDetails?action=info">アカウント情報</a><br>
<a>購入履歴</a><br>
<a>退会</a>
<a href="/novice/index.jsp">Topページ</a>
<a href="/novice/Login?action=logout" >ログアウト</a>
<%} %>

</body>
</html>