<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../css/orderstyle.css">
<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
<title>AccountInfo</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("../Login?action=login");}%>

<%if(loginCustomer != null){ %>
<div class="container">
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8">
        <div class=header>
          <h1>アカウント情報</h1>
        </div>

        <div class="order_form_content">
          <div class="title_header_accountinfo">
            <h2>お客様情報</h2>
          </div>

            <div class="form">
              <dl class="form_list_name">
                <div class="row">
                  <div class="col-4 text-nowrap">
                    <dt class="form_list_title">
                      お名前
                    </dt>
                  </div>
                  <div class="col-1"></div>
                  <div class="col-7 text-nowrap">
                    <dd class="form_class_input">
                      <%=loginCustomer.getLastName() %>  <%=loginCustomer.getFirstName() %>
                    </dd>
                  </div>
                </div>
              </dl>

              <dl class="form_list_name">
                <div class="row">
                  <div class="col-4 text-nowrap">
                    <dt class="form_list_title">
                      郵便番号
                    </dt>
                  </div>
                  <div class="col-1"></div>
                  <div class="col-7 text-nowrap">
                    <dd class="form_class_input">
                      <%=loginCustomer.getPostal()%>
                    </dd>
                  </div>
                </div>
              </dl>

              <dl class="form_list_name">
                <div class="row">
                  <div class="col-4 text-nowrap">
                    <dt class="form_list_title">
                      住所
                    </dt>
                  </div>
                  <div class="col-1"></div>
                  <div class="col-7 text-nowrap">
                    <dd class="form_class_input">
                      <%=loginCustomer.getPref()%> <%=loginCustomer.getMuni()%> <%=loginCustomer.getStAd()%>
                    </dd>
                  </div>
                </div>
              </dl>

              <dl class="form_list_name">
                <div class="row">
                  <div class="col-4 text-nowrap">
                    <dt class="form_list_title">
                      電話番号
                    </dt>
                  </div>
                  <div class="col-1"></div>
                  <div class="col-7 text-nowrap">
                    <dd class="form_class_input">
                     <%=loginCustomer.getTell()%>
                    </dd>
                  </div>
               </div>
              </dl>

              <dl class="form_list_name">
                <div class="row">
                  <div class="col-4 text-nowrap">
                    <dt class="form_list_title">
                      メールアドレス
                    </dt>
                  </div>
                  <div class="col-1"></div>
                  <div class="col-7 text-nowrap">
                    <dd class="form_class_input">
                      <%=loginCustomer.getMail()%>
                    </dd>
                  </div>
                </div>
              </dl>
            </div>

          <div class="info_change_box">
            <div class="title_header_accountinfo">
              <h2>
                各種情報変更
              </h2>
          </div>
            <div class="form">
              <a href="../AccountDetails?action=change_info" class="text-dark">
                <dl class="form_list_account">
                  <div class="row">
                    <div class="col-4 text-nowrap">
                      <dt class="form_class_title">
                       <p class="change_info_title">アカウント情報変更</p>
                      </dt>
                    </div>
                    <div class="col-8 text-nowrap">
                      <dd class="form_class_input">
                      </dd>
                    </div>
                  </div>
                </dl>
              </a>

              <a href="../AccountDetails?action=change_mail" class="text-dark">
                <dl class="form_list_account">
                  <div class="row">
                    <div class="col-4 text-nowrap">
                      <dt class="form_class_title">
                       <p class="change_info_title">メールアドレス変更</p>
                      </dt>
                    </div>
                    <div class="col-8 text-nowrap">
                      <dd class="form_class_input">
                      </dd>
                    </div>
                  </div>
                </dl>
              </a>

              <a href="../AccountDetails?action=change_pass" class="text-dark">
                <dl class="form_list_account">
                  <div class="row">
                    <div class="col-4 text-nowrap">
                      <dt class="form_class_title">
                       <p class="change_info_title">パスワード変更</p>
                      </dt>
                    </div>
                    <div class="col-1 text-nowrap"></div>
                    <div class="col-7 text-nowrap">
                      <dd class="form_class_input">
                      </dd>
                    </div>
                  </div>
                </dl>
              </a>
            </div>

            <div class="button_box_pagelink">
              <a href="../AccountDetails?action=mypage" class="mypage_link text-dark">マイページへ</a>
              <a href="../index.jsp" class="text-dark">Topページへ</a>
            </div>

          </div>
      </div>
      <div class="col-2"></div>
    </div>
  </div>
<%} %>
</body>
</html>