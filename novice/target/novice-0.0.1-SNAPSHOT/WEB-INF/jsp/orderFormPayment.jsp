<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("../Login?action=login");}else{%>
	<div class=header>
  		<h1>オーダーフォーム</h1>
	</div>
	<div class="payment_form_content">
		<form action="../Order?action=confirm" method="post" class="select_payment">
			<div class="orderform_title">
				<h2>支払い方法を選択してください</h2>
			</div>
			<div class="payment_method">
				<ul>
					<li><input type="radio" class="payment_select" name="payment"  required  value="cod">代金引換</li>
					<li><input type="radio" class="payment_select" name="payment"  required  value="bty">銀行振り込み（ゆうちょ銀行）</li>
					<li><input type="radio" class="payment_select" name="payment"  required  value="btj">銀行振り込み（ジャパンネット銀行）</li>
				</ul>
			</div>
			<input type=submit value="確認画面へ">
		</form>

	</div>
	<% }%>
</body>
</html>