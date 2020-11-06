<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント情報</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("/novice/Login?action=login");}%>

<%if(loginCustomer != null){ %>
<h1>アカウント情報</h1>
<p>お名前：<%=loginCustomer.getLastName() + " " + loginCustomer.getFirstName() %>様</p>
<p>郵便番号：<%=loginCustomer.getPostal() %></p>
<p>住所：<%=loginCustomer.getPref() + loginCustomer.getMuni() + loginCustomer.getStAd() %></p>
<p>電話番号：<%=loginCustomer.getTell() %></p>


<a href="/novice/AccountDetails?action=change_info">アカウント情報変更</a><br>
<a href="/novice/AccountDetails?action=change_mail">メールアドレス変更</a><br>
<a href="/novice/AccountDetails?action=change_pass">パスワード変更</a><br>
<a href="/novice/AccountDetails?action=mypage">マイページ</a>
<%} %>
</body>
</html>