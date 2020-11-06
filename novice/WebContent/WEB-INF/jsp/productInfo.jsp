<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Product" %>
<%Product sp = (Product)request.getAttribute("selectedProduct"); %>
<%String pName = sp.getProductName();String pCode = sp.getProductCode();%>
<% int price = sp.getPrice();int stock = sp.getStock();  %>
<%String message = (String)request.getAttribute("message"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報</title>
</head>
<body>
	<a href="/novice/Cart?action=view_cart">カート</a>
	<p>商品情報</p>
	<% if(message!=null{%>
		<p><%=message%></p>
	<%}%>
	<p><%=pName %></p>

	<img src="./img/<%=pCode%>.jpg" alt="<%=pName%>">

	<form action="/novice/Cart?action=add_cart" method="post">
	<p>価格：<%=price%>円</p>
	<select name="quantity" required>
		<%if(stock!=0){ %><option value="" selected>個数</option>
		<%}else{%><option value="" selected>完売</option><%} %>
			<%for (int i =1; i <= stock ; i++ ){ %>
			<option value="<%= i %>"><%= i %></option>
			<%} %>
			</select>
	<input type="hidden" name="productCode"
		value="<%=pCode %>" />
	<input type="hidden" name="productName"
		value="<%=pName %>" />
	<input type="hidden" name="price"
		value="<%=price %>" />
	<input type="hidden" name="stock"
		value="<%=stock %>" />
			<%if(stock!=0){ %><input type= "Submit" value="カートへ入れる"/>
			<%}else{%><input type= "Submit" value="カートへ入れる" disabled/><%} %>
		</form><br/>

</body>
</html>