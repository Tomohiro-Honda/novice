<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer, model.beans.CartItem, model.beans.OrderSheet" %>
<%@page import="java.math.BigDecimal, java.math.RoundingMode"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%Customer sendCustomer = (Customer)session.getAttribute("send_customer");%>
<%OrderSheet orderSheet = (OrderSheet)session.getAttribute("order_sheet"); %>
<%List<CartItem> orderItems = orderSheet.getOrderItems();  %>
<%int codeNum = 1; %>
<%int sumAll = 0; int total = 0; %>
<%int shippingValue = 700; int codValue = 0;%>
<%double num1 = 0.1; double num2 = 1.1; %>
<%BigDecimal t1 = new BigDecimal(String.valueOf(num1));BigDecimal t2 = new BigDecimal(String.valueOf(num2));  %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>OrderConfirm</title>
</head>
<body>
	<div class=header>
  		<h1>オーダー確認</h1>
	</div>
	<div class="payment_form_content">
		<form action="../Order?action=ordered" method="post" class="order_confirm">
			<div class="orderform_title">
				<h2>購入商品</h2>
			</div>

			<div class=cart_items>
				<ul class="cart_items_title">
					<li class="item_name_bar">商品名</li>
					<li class="item_price_bar">価格</li>
					<li class="item_quantity_bar">個数</li>
					<li class="item_sum_bar">小計</li>
				</ul>
				<ul>
				<%for(CartItem items: orderItems){ %>
					<li>
						<div><p>商品画像</p></div>
						<%if(items.getProductCode().contains("TS")){ %>
						<p><%=items.getProductName() + "　" + items.getSize() + "サイズ" +"　" + items.getType()%> </p>
						<%}else{%>
						<p><%=items.getProductName() + "　"+ items.getType()%> </p>
						<%} %>
						<p><%=items.getPrice() %></p>
						<p><%=items.getQuantity() %></p>
						<p>￥<%=items.getSum() %></p>
				</li>
				<%sumAll += items.getSum();%>
			<%} %>
			</ul>
			</div>

			<div class="price_result">
				<dl>
					<dt class="shipping">送料</dt>
					<dd class="shipping_value">￥<%=shippingValue %></dd>
					<dt class="cod">手数料</dt>
					<%if(orderSheet.getPayment().equals("cod")){%>
					<%codValue = 500; %>
					<dd class="cod_value">￥<%=codValue%></dd>
					<%} else{%>
					<dd class="cod_value">￥<%=codValue%></dd>
					<%} %>
					<dt class="total">合計金額</dt>
					<%total =sumAll+shippingValue+codValue; %>
					<dd class="total_value"><%=total%></dd>
					<dt class="tax">内消費税</dt>
					<%BigDecimal totalbd = new BigDecimal(String.valueOf(total));%>
					<%BigDecimal tax = (totalbd.multiply(t1)); %>
					<%BigDecimal taxvalue = (tax.divide(t2,0,RoundingMode.DOWN)); %>
					<dd class="tax_value"><%=taxvalue.intValue()%></dd>
					<input type="hidden" name="shipping_value" value=<%=shippingValue%>>
					<input type="hidden" name="cod_value" value=<%=codValue%>>
					<input type="hidden" name="total_value" value=<%=total%>>
					<input type="hidden" name="tax" value="<%=taxvalue.intValue()%>">
				</dl>
				</div>
				<input type="submit" value="注文完了する">
			</form>
			</div>


</body>
</html>