<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.beans.Customer, model.beans.Product"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%List<Product> productList = (List<Product>)request.getAttribute("productList"); %>
<%String searchWord = (String)request.getAttribute("searchWord"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/topstyle.css">
<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
<title>Search Result</title>
</head>
<body>
<%if(productList.isEmpty()){ %>
<section class="item">
      <div class="text_border">
        <h2>Search Result Nothing(<%=searchWord%>)</h2>
		</div>
</section>
<%} else{%>
<section class="item">
      <div class="text_border">
        <h2>Search Result (<%=searchWord%>)</h2>
      </div>
    <ul class="itemlist">
    <%for(int i=0, n=1; i < productList.size(); i++, n++) {%>
      <li>
      	<form method="post" name="product<%=n%>" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="<%=productList.get(i).getProductCode()%>" />
      	<a href="javascript:product<%=n%>.submit()">
      	<img src="img2/<%=productList.get(i).getProductCode()%>.png" alt="sample">
      	<p class="name"><%=productList.get(i).getProductName()%></p><p class="price"><%=productList.get(i).getPrice()%>円</p></a>
      	</form>
      	<%} %>
	</li>
	</ul>
<%} %>
</body>
</html>