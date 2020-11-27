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
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/cartstyle.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="icon" type="image/png" href="../img/favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
    <title>
      novice onlineshop cart
    </title>
</head>
<body>
<header>
      <div class="header_box">
        <h1><a href="../index.jsp"><img src="img/logo.png" alt="novice"></a></h1>
        <div class="title">
          <h3>Cart Info</h3>
        </div>
    </header>
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
    <form id="product" name="product" action="ProductView?action=product_info" method="post">
    	</form>

    <%for(CartItem items: cartItems){ %>
    <main id="cart1">
    <div class="cart_menu">
      <p>
        <%=items.getProductName()%><br>
        ¥<%=items.getPrice() %>
      </p>
      <input type="hidden" name="individualCode<%=codeNum%>"value="<%=items.getIndividualCode() %>" form="update">
      <div class="sub">
        <p>
          <%=items.getType() %><br>
          <%if(items.getProductCode().contains("TS")){%>
        	  <%=items.getSize() %> size
        	  <%} %>
        </p>
      </div>
    </div>
    <div class="img">
    	<h2><input type="hidden" name="productCode" value="<%=items.getProductCode()%>" form="product"/>
      	<a href="javascript:product.submit()">
      <img src="../img2/<%=items.getProductCode()%>.png" alt="<%=items.getProductName()%>"></a></h2>
    </div>
    <div class="price">
      <div class="selectbox_quantity">
      <%if(items.getQuantity() > items.getStock() ){ %><!-- 購入個数が在庫数を超える場合 -->
			<p style="color: red;">数量が在庫を超えています。数量変更か削除してください。</p>
			<% }%>
        数量 :
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
      </div>
      <div class="delete_box">
		<input type="checkbox" name="check<%=codeNumdl++%>"  form="delete">
		<input type="hidden" name="numOfItems" value="<%=cartItems.size()%>" form="delete">
		<input type="submit" value="削除" form="delete">
      </div>
      <div class="subtotal_price">
        <p>小計 : ¥<%=items.getSum()%></p>
      </div>
    </div>
		<%sumAll += items.getSum(); %>
		<input type="hidden" name="individualCode<%=codeNumdl%>" value="<%=items.getIndividualCode() %>" form="delete">
    </main>
    <%} %>
	<input type="hidden" name="numOfCodes" value="<%=codeNum%>" form="update">
    <div class="total_price">
      <p>合計 : ¥<%=sumAll%></p>
    </div>
    <div class="title_buy">
      <a href="../index.jsp" class="continue">買い物を続ける</a>
      <a href="../Order?action=order_form" class="advance">レジに進む</a>
    </div>
    <%} %>
    <div class="link">
      <div class="social_icons">
        <a href="https://www.instagram.com/novice6699/"><i class="fab fa-instagram fa-2x icon_color"></i></a>
      </div>
      <div class="contact">
        <a href="mailto:novice6699@gmail.com">お問い合わせ</a>
      </div>
    </div>
    <footer>
      <p>Copyright &#169; 2020 Novice Project. All Rights Reserved. </p>
    </footer>
</body>
</html>