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
<link rel="stylesheet" href="../css/orderstyle.css">
<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
<title>my page</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("../Login?action=login");}%>

<% if(loginCustomer != null) {%>

<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
      <div class=header>
        <h1>マイページ</h1>
    </div>
    <div class="form_content">
        <div class="form_title_passchange">
         <div class="row">
             <div class="col-4 text-nowrap">
              <h2 class="title_header_mypage"><%=loginCustomer.getLastName() + loginCustomer.getFirstName() %>様のページ</h2>
              </div>
            <div class="col-8">
              <div class="logout_box">
                <a href="../Login?action=logout" class="btn btn-dark">ログアウト</a>
              </div>
            </div>
          </div>
        </div>
        <div class="form">
          <a  href="../AccountDetails?action=info" class="text-dark">
          <dl class="form_list_name">
            <div class="row">
              <div class="col-4 text-nowrap">
                <dt class="form_class_title">
                 <p class="account_info_title">アカウント情報</p>
                </dt>
              </div>
              <div class="col-8 text-nowrap">
                <dd class="form_class_input">
                  <p class="account_info_text">設定したアカウント情報の確認と変更ができます。<p>
                </dd>
              </div>
            </div>
          </dl>
        </a>
        <a  href="../History?action=view_history" class="text-dark">
          <dl class="form_list_name">
            <div class="row">
              <div class="col-4 text-nowrap">
                <dt class="form_class_title">
                 <p class="account_info_title">購入履歴</p>
                </dt>
              </div>
              <div class="col-8 text-nowrap">
                <dd class="form_class_input">
                  <p class="account_info_text">購入履歴が確認できます。<p>
                </dd>
              </div>
            </div>
          </dl>
        </a>
          <div class="button_box">
            <a href="../index.jsp" class="text-dark">Topページへ</a>
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