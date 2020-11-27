<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%
Customer registerCustomer = (Customer)session.getAttribute("registerCustomer");
String passLength ="●";
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>ユーザー登録</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
</head>

<body>
	<div class="container">

		<div class="row justify-content-sm-center">
			<div class="col-sm-auto">
				<header>
					<h2>新規会員登録</h2>
				</header>
				<p class="text_normal">
					以下の情報で会員登録します。<br>
					ご登録内容はマイページから変更できます。
				</p>
			</div>
		</div>

		<div class=form>
		<dl class="form_list">
			<div class="row">
				<div class="col-sm-3">
					<dt class="input_title">お名前</dt>
				</div>
				<div class="col-sm-9">
					<dd class="form_list_input"><%= registerCustomer.getLastName() + " " + registerCustomer.getFirstName() + "様"%></dd>
				</div>
			</div>
		</dl>

		<dl class="form_list">
			<div class="row">
				<div class="col-sm-3">
					<dt class="input_title">メール</dt>
				</div>
				<div class="col-sm-9">
					<dd class="form_list_input"><%= registerCustomer.getMail() %></dd>
				</div>
			</div>
		</dl>

		<dl class="form_list">
			<div class="row">
				<div class="col-sm-3">
					<dt class="input_title">パスワード</dt>
				</div>
				<div class="col-sm-9">
					<dd class="form_list_input">
						<%  for (int i = 0; i<registerCustomer.getPass().length(); i++) { %>
						<%=passLength%>
						<% 	 } %>
					</dd>
				</div>
			</div>
		</dl>

		<dl class="form_list">
			<div class="row">
				<div class="col-sm-3">
					<dt class="input_title">郵便番号</dt>
				</div>
				<div class="col-sm-9">
					<dd class="form_list_input">
						<%= registerCustomer.getPostal()%>
					</dd>
				</div>
			</div>
		</dl>

		<dl class="form_list">
			<div class="row">
				<div class="col-sm-3">
					<dt class="input_title">住所</dt>
				</div>
				<div class="col-sm-9">
					<dd class="form_list_input">
						<%= registerCustomer.getPref() + registerCustomer.getMuni() + registerCustomer.getStAd() %>
					</dd>
				</div>
			</div>
		</dl>

		<dl class="form_list">
			<div class="row">
				<div class="col-sm-3">
					<dt class="input_title">電話番号</dt>
				</div>
				<div class="col-sm-9">
					<dd class="form_list_input">
						<%= registerCustomer.getTell() %>
					</dd>
				</div>
			</div>
		</dl>

		<div class="row justify-content-sm-center form_list">
			<div class="col-sm-auto register_return">
				<a href="javascript:history.back()" class="btn btn-outline-dark">戻る</a>
			</div>
			<div class="col-sm-auto　register_ok">
				<a href="../RegisterUser?action=done" class="btn btn-dark">登録</a>
			</div>
		</div>

	  </div>
  </div>
</body>
</html>