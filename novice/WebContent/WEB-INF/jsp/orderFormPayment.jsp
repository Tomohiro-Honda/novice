<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html lang=ja>
<head>
<meta charset="UTF-8">
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="../css/orderstyle.css">
<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
<title>OrderConfirm</title>
</head>
<body>
	<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
      <div class=header>
        <h1>オーダーフォーム</h1>
    </div>
      <form action="../Order?action=confirm" method="post" class="select_payment">
        <div class="orderform_title_payment text-nowrap">
          <h2>お支払い方法を選択してください</h2>
        </div>
        <div class="payment_method">
          <ul class="payment_check text-nowrap">
            <li class="payment_list"><input type="radio" class="payment_select" name="payment"  required  value="cod">代金引換</li>
            <li class="payment_list"><input type="radio" class="payment_select" name="payment"  required  value="bty">銀行振り込み（ゆうちょ銀行）</li>
            <li class="payment_list"><input type="radio" class="payment_select" name="payment"  required  value="btj">銀行振り込み（ジャパンネット銀行）</li>
          </ul>
        </div>
        <div class="btn_box_payment">
        <input class="btn btn-dark" type=submit value="確認画面へ">
        </div>
      </form>
        </form>
    </div>
    </div>
    <div class="col-2"></div>
  </div>
</body>
</html>