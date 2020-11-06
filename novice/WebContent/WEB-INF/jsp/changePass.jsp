<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<%String message = (String)request.getAttribute("message") ;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更</title>
<script type="text/javascript" src="/novice/js/formScript.js"></script>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("/novice/Login?action=login");}%>

<%if(loginCustomer != null){ %>
	<div class=page_title>
		<h2>パスワード変更</h2>
	</div>
	<%if(message != null) { %>
	<p><%=message%></p>
	<%} %>
	<form action="/novice/AccountDetails?action=change_pass" method="post">
		<div class="form">
			<dl class="form_list_name">
				<dt class="form_class_title">
					<label class="form_label">
						現在のパスワード
					</label>
				</dt>
				<dd class="form_class_input">
					<input type="password" name = "oldPass" class="form_input" required>
				</dd>
			</dl>
			<dl class="form_list_name">
				<dt class="form_class_title">
					<label class="form_label">
						新しいパスワード
					</label>
				</dt>
				<dd class="form_class_input">
					<ul class="form_contents_list">
						<li>
						<input type="password" name="newPassA" required="required"
						pattern="^[a-zA-Z0-9#$%&amp;'*,!-`{}()=+\[\].^:;?@~]{6,30}$"
						title="6〜30桁の半角英数字・記号で入力してください。"
						 autocomplete="new-password" class="form_input">
						 </li>
						 <li>
						 <input type="password"name="newPassB" placeholder="確認のため再度入力してください" required="required"
						  pattern="^[a-zA-Z0-9#$%&amp;'*,!-`{}()=+\[\].^:;?@~]{6,30}$"
						  title="6〜30桁の半角英数字・記号で入力してください。" autocomplete="new-password" class="form_input">
						  </li>
						  </ul>
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