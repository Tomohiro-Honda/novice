<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../css/style.css">
<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
<title>アカウント情報変更</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("../Login?action=login");}%>

<%if(loginCustomer != null){ %>
	<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<div class=page_title>
					<h2>アカウント情報変更</h2>
				</div>

				<form action="../AccountDetails?action=change_info" method="post">
					<div class="form">
						<dl class="form_list">
							<div class="row">
								<div class="col-sm-3 text-nowrap">
									<dt class="input_title">
										お名前
									</dt>
								</div>
								<div class="col-sm-9 text-nowrap">
									<dd class="form_list_input">
										<label class="input_first_name">姓</label>
										<input type="text" name="lastName" class="form_input_short" value="<%=loginCustomer.getLastName() %>"  required>
										<label>名</label>
										<input type="text" name="firstName" class="form_input_short" value="<%=loginCustomer.getFirstName() %>" required>
									</dd>
								</div>
							</div>
						</dl>
						<dl class="form_list">
							<div class="row">
									<div class="col-sm-3 text-nowrap">
										<dt class="input_title">
											郵便番号
										</dt>
									</div>
									<div class="col-sm-9">
										<dd class="form_list_input">
											<input type="text" name="postal" class="form_input_short" value="<%=loginCustomer.getPostal()%>" required>
										</dd>
									</div>
								</div>
							</dl>
							<dl class="form_list">
								<div class="row">
									<div class="col-sm-3 text-nowrap">
										<dt class="input_title">
										都道府県
										</dt>
									</div>
									<div class="col-sm-9">
										<dd class="form_list_input">
											<input type="text" name="pref" class="form_input_short" value="<%=loginCustomer.getPref()%>" required>
										</dd>
									</div>
								</div>
							</dl>
							<dl class="form_list">
								<div class="row">
									<div class="col-sm-3 text-nowrap">
										<dt class="input_title">
											市区町村
										</dt>
									</div>
									<div class="col-sm-9">
										<dd class="form_list_input">
											<input type="text" name="muni" class="form_input_short" value="<%=loginCustomer.getMuni()%>" required>
										</dd>
									</div>
								</div>
							</dl>
							<dl class="form_list">
								<div class="row">
									<div class="col-sm-3 text-nowrap">
										<dt class="input_title">
											番地以降
										</dt>
									</div>
									<div class="col-sm-9">
										<dd class="form_list_input">
											<input type="text" name="stAd" class="form_input_short" value="<%=loginCustomer.getStAd()%>" required>
										</dd>
									</div>
								</div>
							</dl>
							<dl class="form_list">
								<div class="row">
									<div class="col-sm-3 text-nowrap">
										<dt class="input_title">
											電話番号
										</dt>
									</div>
									<div class="col-sm-9">
										<dd class="form_list_input">
											<input type="text" name="tell" class="form_input_short" value="<%=loginCustomer.getTell()%>" required>
										</dd>
									</div>
								</div>
							</dl>
							<div class="row justify-content-sm-center">
								<div class="col-sm-auto">
									<a href="../AccountDetails?action=mypage" class="btn btn-outline-dark">マイページへ</a>
									<input class="btn btn-dark" type="submit" value="変更する">
								</div>
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