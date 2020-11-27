<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../css/orderstyle.css">
<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
<title>メールアドレス変更</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("../Login?action=login");}%>

<%if(loginCustomer != null){ %>
	<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
        <div class=header>
          <h1>アカウント設定</h1>
        </div>

		    <div class=page_title>
			    <h2 class="title_mail">メールアドレス変更</h2>
		    </div>

		<form action="../AccountDetails?action=change_mail" method="post">
			<div class="form">
				<dl class="form_list_name">
					<div class="row">
                  		<div class="col-sm-3 text-nowrap">
							<dt class="form_class_title">
								<label class="form_label_mail">
									設定中のメールアドレス
								</label>
							</dt>
            </div>
            <div class="col-1"></div>
						<div class="col-sm-8 text-nowrap">
							<dd class="form_class_input_mail">
								<%=loginCustomer.getMail()%>
							</dd>
						</div>
					</div>
				</dl>
				<dl class="form_list_name">
					<div class="row">
                  		<div class="col-sm-3 text-nowrap">
							<dt class="form_class_title">
								<label class="form_label_mail">
									メールアドレス
									<span class="form_list_label_mail">必須</span>
								</label>
							</dt>
            </div>
            <div class="col-1 text-nowrap"></div>
						<div class="col-sm-8 text-nowrap">
							<dd class="form_class_input_mail">
								<input type="email" name ="newMail" class="form_input long, form_input_mail" required>
								<p class="change_mail_text">
								※入力したメールアドレスがお間違い無いか、もう一度確認してください
								</p>
							</dd>
						</div>
					</div>
				</dl>
				<div class="button_box">
						<input class="btn btn-dark" type="submit" value="変更する">
					</div>
				</div>
			</form>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
<%} %>
</body>
</html>