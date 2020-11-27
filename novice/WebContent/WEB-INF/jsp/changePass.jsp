<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<%String message = (String)request.getAttribute("message") ;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../css/orderstyle.css">
<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
<title>password change</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("../Login?action=login");}%>
<%if(loginCustomer != null){ %>
	<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
      <div class=header>
        <h1>アカウント設定</h1>
    </div>
    <div class="form_content">
      <div class="form_title_passchange">
        <h2 class="title_header_passchange">パスワード変更</h2>
      </div>
	<%if(message != null) { %>
	<p><%=message%></p>
	<%} %>
	<form action="../AccountDetails?action=change_pass" method="post">
        <div class="form">
          <dl class="form_list_name">
            <div class="row">
              <div class="col-4 text-nowrap">
                <dt class="form_class_title">
                <label class="form_oldpass_label">
                現在のパスワード
                </label>
                </dt>
              </div>
              <div class="col-8 text-nowrap">
                <dd class="form_class_input">
                  <input type="password" name = "oldPass" class="form_input" required>
                </dd>
              </div>
            </div>
          </dl>
          <dl class="form_list_name">
            <div class="row">
              <div class="col-4 text-nowrap">
                <dt class="form_class_title">
                <label class="form_newpass_label">
                新しいパスワード
                </label>
                </dt>
              </div>
              <div class="col-8 text-nowrap">
                <dd class="form_class_input">
                  <ul class="form_contents_list">
                   <li>
                   <input type="password" name="newPassA" required="required"
                   pattern="^[a-zA-Z0-9#$%&amp;'*,!-`{}()=+\[\].^:;?@~]{6,30}$"
                   title="6〜30桁の半角英数字・記号で入力してください。"
                   autocomplete="new-password" class="form_input">
                   </li>
                   <li class="pass_change_input">
                   <input type="password"name="newPassB" placeholder="確認のため再度入力してください" required="required"
                    pattern="^[a-zA-Z0-9#$%&amp;'*,!-`{}()=+\[\].^:;?@~]{6,30}$"
                    title="6〜30桁の半角英数字・記号で入力してください。" autocomplete="new-password" class="form_input">
                    </li>
                  </ul>
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

    </div>
    <div class="col-2"></div>
  </div>
<%} %>
</body>
</html>