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
<%String payment = orderSheet.getPayment();%>
<%String payOrder = null;%>
<% switch(payment) {
case  "cod":payOrder = "代金引換"; break;
case "bty":	payOrder = "銀行振り込み（ゆうちょ銀行）"; break;
case "btj":	payOrder = "銀行振り込み（ジャパンネット銀行）";
} %>
<!DOCTYPE html>
<html lang=ja>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../css/orderstyle.css">
<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
<title>OrderConfirm</title>
</head>
<body>
<% if(sendCustomer == null){response.sendRedirect("../index.jsp");}else{%>
  <div class="row">
    <div class="col-2"></div>
    <div class="col-8">
      <div class=header>
        <h1>注文の確認</h1>
    </div>
    <div class="payment_form_content">
      <form action="../Order?action=ordered" method="post" class="order_confirm">
        <div class="orderform_title">
          <h2>購入商品</h2>
        </div>

        <div class=cart_items>
          <ul class="cart_items_title">
            <div class="row">
              <div class="col-2"></div>
              <div class="col-4 text-nowrap">
                <li class="item_name_bar">商品名</li>
              </div>
              <div class="col-2 text-nowrap text-center">
               <li class="item_bar">価格</li>
              </div>
              <div class="col-2 text-nowrap text-center">
                <li class="item_bar">個数</li>
              </div>
              <div class="col-2 text-nowrap text-center">
                <li class="item_bar">小計</li>
              </div>
            </div>
          </ul>
          <ul class="cart_item_list">
            <%for(CartItem items: orderItems){ %>
            <li class="order_item_list">
              <div class="row">
                <div class="col-2 text-nowrap">
                  <div class="item_thumb">
                    <img src="../img2/<%=items.getProductCode()%>.png" class="item_image">
                  </div>
                </div>
                <div class="col-4 text-wrap">
                  <div class="item_name_field">
                  <%if(items.getProductCode().contains("TS")){ %>
                  <p class="item_info"><%=items.getProductName() + "　" + items.getSize() + "サイズ" +"　" + items.getType()%></p>
                  <%}else{%>
                  <p class="item_info"><%=items.getProductName() + "　"+ items.getType()%> </p>
                  <%} %>
                  </div>
                </div>
                <div class="col-2 text-nowrap">
                  <p class="item_info">¥<%=items.getPrice() %></p>
                </div>
                <div class="col-2 text-nowrap">
                  <p class="item_info"><%=items.getQuantity() %></p>
                </div>
                <div class="col-2 text-nowrap">
                  <p class="item_info">¥<%=items.getSum()%> </p>
                </div>
              </div>
          </li>
          <%sumAll += items.getSum();%>
          <%} %>
        </ul>
        </div>

        <div class="row">
          <div class="col-8"></div>
          <div class="col-4">
          <div class="price_result">
            <dl class="order_values">
              <div class="row">
                <div class="col-6 text-nowrap">
                  <dt class="shipping">送料</dt>
                </div>
                <div class="col=6">
                  <dd class="shipping_value"><%=shippingValue %></dd>
                </div>
              </div>
              <div class="row">
                <div class="col-6 text-nowrap">
                  <dt class="cod">手数料</dt>
                </div>
                <div class="col=6">
                  <%if(orderSheet.getPayment().equals("cod")){%>
                    <%codValue = 500; %>
                  <dd class="cod_value">¥<%=codValue%></dd>
                  <%} else{%>
                  <dd class="cod_value">¥<%=codValue%></dd>
                  <%} %>
                </div>
              </div>
              <div class="row">
                <div class="col-6 text-nowrap">
                  <dt class="total">合計金額</dt>
                </div>
                <div class="col=6">
                  <%total =sumAll+shippingValue+codValue; %>
                  <dd class="total_value">¥<%=total%></dd>
                </div>
              </div>
              <div class="row">
                <div class="col-6 text-nowrap">
                  <dt class="tax">内消費税</dt>
                </div>
                <div class="col=6">
                  <%BigDecimal totalbd = new BigDecimal(String.valueOf(total));%>
					<%BigDecimal tax = (totalbd.multiply(t1)); %>
					<%BigDecimal taxvalue = (tax.divide(t2,0,RoundingMode.DOWN)); %>
                  <dd class="tax_value">¥<%=taxvalue.intValue()%></dd>
                </div>
              </div>
              <input type="hidden" name="shipping_value" value=<%=shippingValue%>>
              <input type="hidden" name="cod_value" value=<%=codValue%>>
              <input type="hidden" name="total_value" value=<%=total%>>
              <input type="hidden" name="tax" value="<%=taxvalue.intValue()%>">
          </dl>
          </div>
          </div>
        </div>
      </div>

      <div class="order_form_content">
        <div class="orderform_title">
          <h2>お客様情報</h2>
        </div>

        <div >
            <div class="form">
              <dl class="order_list_name">
                <div class="row">
                <div class="col-4 text-nowrap">
                  <dt class="order_list_title">
                  お名前
                  </dt>
                </div>
                <div class="col-8 text-nowrap">
                <dd class="order_class_input">
                  <%=sendCustomer.getLastName() %>  <%=sendCustomer.getFirstName() %>
                </dd>
              </div>
            </div>
              </dl>

              <dl class="order_list_name">
                <div class="row">
                  <div class="col-4 text-nowrap">
                    <dt class="order_list_title">
                    郵便番号
                    </dt>
                  </div>
                  <div class="col-8 text-nowrap">
                    <dd class="order_class_input">
                      <%=sendCustomer.getPostal()%>
                    </dd>
                  </div>
                </div>
              </dl>
              <dl class="order_list_name">
                <div class="row">
                  <div class="col-4 text-nowrap">
                    <dt class="order_list_title">
                    住所
                    </dt>
                  </div>
                  <div class="col-8 text-nowrap">
                    <dd class="order_class_input">
                      <%=sendCustomer.getPref()%> <%=sendCustomer.getMuni()%> <%=sendCustomer.getStAd()%>
                    </dd>
                  </div>
                </div>
              </dl>
              <dl class="order_list_name">
                <div class="row">
                  <div class="col-4 text-nowrap">
                  <dt class="order_list_title">
                  電話番号
                  </dt>
                </div>
                  <div class="col-8 text-nowrap">
                    <dd class="order_class_input">
                      <%=sendCustomer.getTell()%>
                    </dd>
                  </div>
                </div>
             </dl>
              <dl class="order_list_name">
                <div class="row">
                  <div class="col-4 text-nowrap">
                    <dt class="order_list_title">
                    メールアドレス
                    </dt>
                  </div>
                  <div class="col-8 text-nowrap">
                    <dd class="order_class_input">
                      <%=sendCustomer.getMail()%>
                   </dd>
                  </div>
                </div>
              </dl>
            </div>

            <div></div>

            <div class="order_form_content">
              <div class="orderform_title">
                <h2>お支払い方法</h2>
              </div>
              <div >
                  <div class="form">
                    <dl class="order_list_name">
                      <div class="row">
                        <div class="col-4 text-nowrap">
                          <dt class="order_list_title">
                          お支払い方法
                          </dt>
                        </div>
                        <div class="col-8 text-nowrap">
                          <dd class="order_class_input">
                          <%=payOrder%>
                          </dd>
                        </div>
                      </div>
                    </dl>
                 </div>
                </div>
            </div>
            <div class="order_button">
              <input type="submit" value="注文完了する">
            </div>
          </form>

    </div>
    <div class="col-2"></div>
  </div>
  <%} %>
</body>
</html>