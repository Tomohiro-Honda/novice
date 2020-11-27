<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OrderForm</title>
</head>
<body>

  <div class=header>
  <h1>オーダーフォーム</h1>
  <p>送付先情報の確認</p>
  </div>

  <div class="order_form_content">
    <div class="title">
      <h2>お客様情報</h2>
    </div>

    <div>
      <form action="../Order?action=payment" method="post">
        <div class="form">
          <dl class="form_list_name">
            <dt class="form_list_title">
            お名前
            </dt>
            <dd class="form_class_input">
             <%=loginCustomer.getLastName() %>  <%=loginCustomer.getFirstName() %>
            </dd>
          </dl>
          <dl class="form_list_name">
            <dt class="form_class_title">
            郵便番号
            </dt>
            <dd class="form_class_input">
              <%=loginCustomer.getPostal()%>
            </dd>
          </dl>
          <dl class="form_list_name">
            <dt class="form_class_title">
            住所
            </dt>
            <dd class="form_class_input">
              <%=loginCustomer.getPref()%> <%=loginCustomer.getMuni()%> <%=loginCustomer.getStAd()%>
            </dd>
          </dl>
          <dl class="form_list_name">
            <dt class="form_class_title">
            電話番号
            </dt>
            <dd class="form_class_input">
              <%=loginCustomer.getTell()%>
            </dd>
          </dl>
          <dl class="form_list_name">
            <dt class="form_class_title">
            メールアドレス
            </dt>
            <dd class="form_class_input">
              <%=loginCustomer.getMail()%>
            </dd>
          </dl>
        </div>

          <input class="btn" type="submit" value="お支払い情報の入力へ">

        <div class="checkbox">
          <input type="checkbox" name="not_to_customer" id="not_to_customer">
          <label for="not_to_customer">上記以外の宛先へ送付する場合、チェックを入れて下記のフォームに入力してください。</label>
        </div>

        <div class="form">
          <dl class="form_list_name">
            <dt class="form_list_title">
            お名前
            </dt>
            <dd class="form_class_input">
              <label>姓</label>
              <input type="text" name="lastName" class="form_input_short">
            </dd>
            <dd class="form_class_input">
              <label>名</label>
              <input type="text" name="firstName" class="form_input_short">
            </dd>
          </dl>
          <dl class="form_list_name">
            <dt class="form_class_title">
            郵便番号
            </dt>
            <dd class="form_class_input">
              <input type="text" name="postal" class="form_input_short">
            </dd>
          </dl>
          <dl class="form_list_name">
            <dt class="form_class_title">
            都道府県
            </dt>
            <dd class="form_class_input">
              <input type="text" name="pref" class="form_input_short">
            </dd>
          </dl>
          <dl class="form_list_name">
            <dt class="form_class_title">
            市区町村
            </dt>
            <dd class="form_class_input">
              <input type="text" name="muni" class="form_input_short">
            </dd>
          </dl>
          <dl class="form_list_name">
          <dt class="form_class_title">
          番地以降
          </dt>
          <dd class="form_class_input">
          <input type="text" name="stAd" class="form_input_short">
          </dd>
          </dl>
          <dl class="form_list_name">
            <dt class="form_class_title">
            電話番号
            </dt>
            <dd class="form_class_input">
              <input type="text" name="tell" class="form_input_short">
            </dd>
          </dl>
          <dl class="form_list_name">
            <dt class="form_class_title">
            メールアドレス
            </dt>
            <dd class="form_class_input">
              <input type="text" name="tell" class="form_input_short">
            </dd>
          </dl>
        </div>

        <div class="button_box">
          <input class="btn" type="submit" value="お支払い情報の入力へ">
        </div>
      </form>
    </div>
  </div>
</body>
</html>