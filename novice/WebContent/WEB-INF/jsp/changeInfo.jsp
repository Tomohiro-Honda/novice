<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント情報変更</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("/novice/Login?action=login");}%>

<%if(loginCustomer != null){ %>
	<div class=page_title>
		<h2>アカウント情報変更</h2>
	</div>

	<form action="/novice/AccountDetails?action=change_info" method="post">
		<div class="form">
			<dl class="form_list_name">
				<dt class="form_list_title">
				お名前
				</dt>
				<dd class="form_class_input">
					<label>姓</label>
					<input type="text" name="lastName" class="form_input_short" value="<%=loginCustomer.getLastName() %>"  required>
				</dd>
				<dd class="form_class_input">
					<label>名</label>
					<input type="text" name="firstName" class="form_input_short" value="<%=loginCustomer.getFirstName() %>" required>
				</dd>
			</dl>
			<dl class="form_list_name">
				<dt class="form_class_title">
				郵便番号
				</dt>
					<dd class="form_class_input">
					<input type="text" name="postal" class="form_input_short" value="<%=loginCustomer.getPostal()%>" required>
					</dd>
			</dl>
			<dl class="form_list_name">
			<dt class="form_class_title">
			都道府県
			</dt>
			<dd class="form_class_input">
			<input type="text" name="pref" class="form_input_short" value="<%=loginCustomer.getPref()%>" required>
			</dd>
			</dl>
			<dl class="form_list_name">
			<dt class="form_class_title">
			市区町村
			</dt>
			<dd class="form_class_input">
			<input type="text" name="muni" class="form_input_short" value="<%=loginCustomer.getMuni()%>" required>
			</dd>
			</dl>
			<dl class="form_list_name">
			<dt class="form_class_title">
			番地以降
			</dt>
			<dd class="form_class_input">
			<input type="text" name="stAd" class="form_input_short" value="<%=loginCustomer.getStAd()%>" required>
			</dd>
			</dl>
			<dl class="form_list_name">
			<dt class="form_class_title">
			電話番号
			</dt>
			<dd class="form_class_input">
			<input type="text" name="tell" class="form_input_short" value="<%=loginCustomer.getTell()%>" required>
			</dd>
			</dl>
			<div class="button_box">
				<input class="btn" type="submit" value="変更する">
			</div>
		</div>
	</form>
<%} %>
</body>
</html>