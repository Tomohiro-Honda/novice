<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メールアドレス変更</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("../Login?action=login");}%>

<%if(loginCustomer != null){ %>
	<div class=page_title>
		<h2>メールアドレス変更</h2>
	</div>

	<form action="../AccountDetails?action=change_mail" method="post">
		<div class="form">
			<dl class="form_list_name">
				<dt class="form_class_title">
					<label class="form_label">
						設定中のメールアドレス
					</label>
				</dt>
				<dd class="form_class_input">
					<%=loginCustomer.getMail()%>
				</dd>
			</dl>
			<dl class="form_list_name">
				<dt class="form_class_title">
					<label class="form_label">
						メールアドレス
						<span class="form_list_label">必須</span>
					</label>
				</dt>
				<dd class="form_class_input">
					<input type="email" name ="newMail" class="form_input long" required>
					<p>
						::before
						"※入力したメールアドレスがお間違い無いか、もう一度確認してください"
					</p>
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