<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.beans.Customer"%>
<%@page import="model.beans.OrderItem"%>
<%@page import="model.beans.OrderSheet"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%@page import="java.sql.Timestamp, java.text.SimpleDateFormat" %>
<%List<OrderSheet> orderList = (List<OrderSheet>)request.getAttribute("orderList"); %>
<%OrderItem item = null; %>
<%SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>order history</title>
<link rel="stylesheet" type="text/css" href="../css/history.css">
<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
</head>
<body>
<div class="page_wrap">
<h1>注文履歴</h1>
	<div class="order_container">
	<hr>
	<%for(int i = 0; i < orderList.size(); i++){ %>
	<div class="gloup_oder">
		<%String str = sdf.format(orderList.get(i).getDate()); %>
		<p>注文日時: <%=str%> </p>
		<p>合計金額： ¥<%=orderList.get(i).getTotalPrice() %></p>
		<p>送り先： 〒<%=orderList.get(i).getDestinationPostal()%> <%=orderList.get(i).getDestinationAdress() %>
		 </p>
		 	<%for(int j =0; j < orderList.get(i).getItemsOrdered().size(); j++){ %>
		 	<% item = orderList.get(i).getItemsOrdered().get(j);%>
		 <div class="item_info_box">
		 <img  class="item_image" src="../img2/<%=item.getProductCode()%>.png" alt="<%=item.getProductName()%>">
		 <ul class="item_info">
		 <%if(item.getProductCode().contains("TS")){ %>
		 <li>商品名： <%=item.getProductName() + "　" + item.getSize()+"サイズ" +"　"+ item.getType() %> </li>
		 <%}else{ %>
		 <li>商品名： <%=item.getProductName() + "　"+ item.getType() %> </li>
		 <%} %>
		 <li>商品単価： ¥<%=item.getPrice() %></li>
		 <li>個数： <%=item.getQuantity() %></li>
		 </ul>
		 </div>
		 <%} %>
		 <hr>
		 <%} %>
	</div>
	</div>
	<a href="javascript:history.back()">戻る</a>
</div>
</body>
</html>