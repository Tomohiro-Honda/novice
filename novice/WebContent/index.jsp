<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%
Customer loginCustomer = (Customer)session.getAttribute("login_customer");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Novice </title>
</head>
<body>
<h1>NOVICE </h1>

<%
if(loginCustomer==null)
	{
	%>
	<a href="/novice/Login?action=login" >ログイン</a>
	<br/>
		<a href="/novice/RegisterUser">会員登録</a>
	<%
	}
if(loginCustomer!=null)
	{
	%>
<p>ようこそ<%=loginCustomer.getLastName() %>様</p>
	<a href="/novice/Login?action=logout" >ログアウト</a>
	<br/>
	<a href="/novice/AccountDetails?action=mypage">マイページ</a>
	<br/>
	<a href="/novice/Cart?action=view_cart">カート</a>
	<br/>
	<section class="item">
		<div class="text_border">
			<h2>ITEM</h2>
		</div>
	<ul class="itemlist">
		<li>
			<form method="post" name="product1" action="/novice/ProductView?action=product_info">
				<input type="hidden" name="productCode" value="TS-H01" />
				<a href="javascript:product1.submit()"><img src="img/sample.png" alt="sample">
				 <p class="name">猫が鳴いてるTシャツ</p><p class="price">2,800円</p></a></form>
		</li>
		<li>
			<form method="post" name="product2" action="/novice/ProductView?action=product_info">
				<input type="hidden" name="productCode" value="TS-H02" />
				<a href="javascript:product2.submit()"><img src="img/sample.png" alt="sample">
				 <p class="name">影猫Tシャツ</p><p class="price">3,000円</p></a></form>
		</li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
		<li><a href="#"><img src="img/sample.png" alt="sample">
		<p class="name">商品名</p><p class="price">2,000円</p></a></li>
	</section>	
	<br/>
	<%
	}
%>

</body>
</html>