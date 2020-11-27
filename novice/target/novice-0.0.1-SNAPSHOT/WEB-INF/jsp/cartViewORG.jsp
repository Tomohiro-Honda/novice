<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.beans.Customer"%>
<%@page import="model.beans.CartItem"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%List<CartItem> cartItems = (ArrayList<CartItem>)request.getAttribute("cartItems");  %>
<%int sumAll = 0; %>
<%int codeNum = 1; %>
<%int codeNumdl = 1; %>
<%String message = (String)request.getAttribute("message"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script type="text/javascript" src="../js/cartScript.js"></script> -->
<title>ShopingCart</title>
</head>
<body>
<h1>買い物カゴ</h1>
	<%if(cartItems.isEmpty()){ %>
		<p>カートに商品がありません</p>
	<%}else{%>
		<%if(message != null){%>
			<p><%=message%></p>
		<%} %>
		<form id="update" name="update" action="../Cart?action=quantity_update" method="post">
		</form>
		<form id="delete"name="delete" action="../Cart?action=delete_item" method="post">
		</form>

		<%for(CartItem items: cartItems){ %>
		<p>在庫数：<%=items.getStock() %></p>
		<p>商品名：<%=items.getProductName()%></p>
			<input type="hidden" name="individualCode<%=codeNum%>"value="<%=items.getIndividualCode() %>" form="update">
			<select name="quantity<%=codeNum++%>" required form="update">
				<%if(items.getQuantity() > items.getStock() ){ %><!-- 購入個数が在庫数を超える場合 -->
				<option value="<%=items.getQuantity()%>" selected><%=items.getStock()%></option>
				<% }else{%>
				<option value="<%=items.getQuantity() %>" selected><%=items.getQuantity()%></option>
				<%} %>
				<%for (int i =1; i <= items.getStock() ; i++ ){ %>
				<option value="<%= i %>"><%= i %></option>
				<%} %>
			</select>
			<input type= "submit" value="数量変更" form="update"><br>
			<%if(items.getQuantity() > items.getStock() ){ %><!-- 購入個数が在庫数を超える場合 -->
			<p style="color: red;">数量が在庫を超えています。数量変更か削除してください。</p>
			<% }%>

				<input type="hidden" name="individualCode<%=codeNumdl%>" value="<%=items.getIndividualCode() %>" form="delete">
				<input type="checkbox" name="check<%=codeNumdl++%>"  form="delete">
				<input type="hidden" name="numOfItems" value="<%=cartItems.size()%>" form="delete">
				<input type="submit" value="削除" form="delete">

	<p>価格：<%=items.getPrice() %></p>
	<p>小計：<%=items.getSum() %></p>
	<hr>
	<%sumAll += items.getSum();%>
	<%} %>
	<input type="hidden" name="numOfCodes" value="<%=codeNum%>" form="update">

	<p>合計金額：<%=sumAll%></p>
		<a href="../Order?action=order_form">注文画面へ</a>
<%} %>
<a href="../index.jsp">Topページ</a>
<a href="../AccountDetails?action=mypage">アカウント</a>
</body>
</html>