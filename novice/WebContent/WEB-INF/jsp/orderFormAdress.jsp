<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html lang=ja>
<head>
<meta charset="UTF-8">
<title>OrderForm</title>
<meta charset="UTF-8">
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../css/orderstyle.css">
<link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
<title>OrderForm</title>
</head>
<body>
<% if(loginCustomer == null){response.sendRedirect("../Login?action=login");}else{%>
  <div class="container">
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8">
        <div class=header>
          <h1>オーダーフォーム</h1>
          <p class="order_form_message">送付先情報の確認</p>
          </div>

          <div class="order_form_content">
            <div class="title">
              <h2>お客様情報</h2>
            </div>

            <div>
              <form action="../Order?action=payment" method="post">
                <div class="form">

                  <dl class="form_list_name">
                    <div class="row">
                    <div class="col-5 text-nowrap">
                      <dt class="form_list_title">
                      お名前
                      </dt>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-6 text-nowrap">
                    <dd class="form_class_input">
                      <%=loginCustomer.getLastName() %>  <%=loginCustomer.getFirstName() %>
                    </dd>
                  </div>
                </div>
                  </dl>

                  <dl class="form_list_name">
                    <div class="row">
                      <div class="col-5 text-nowrap">
                        <dt class="form_list_title">
                        郵便番号
                        </dt>
                      </div>
                      <div class="col-1"></div>
                      <div class="col-6 text-nowrap">
                        <dd class="form_class_input">
                          <%=loginCustomer.getPostal()%>
                        </dd>
                      </div>
                    </div>
                  </dl>
                  <dl class="form_list_name">
                    <div class="row">
                      <div class="col-5 text-nowrap">
                        <dt class="form_list_title">
                        住所
                        </dt>
                      </div>
                      <div class="col-1"></div>
                      <div class="col-6 text-nowrap">
                        <dd class="form_class_input">
                          <%=loginCustomer.getPref()%> <%=loginCustomer.getMuni()%> <%=loginCustomer.getStAd()%>
                        </dd>
                      </div>
                    </div>
                  </dl>
                  <dl class="form_list_name">
                    <div class="row">
                      <div class="col-5 text-nowrap">
                      <dt class="form_list_title">
                      電話番号
                      </dt>
                    </div>
                      <div class="col-1"></div>
                      <div class="col-6 text-nowrap">
                        <dd class="form_class_input">
                          <%=loginCustomer.getTell()%>
                        </dd>
                      </div>
                    </div>
                 </dl>
                  <dl class="form_list_name">
                    <div class="row">
                      <div class="col-5 text-nowrap">
                        <dt class="form_list_title">
                        メールアドレス
                        </dt>
                      </div>
                      <div class="col-1"></div>
                      <div class="col-6 text-nowrap">
                        <dd class="form_class_input">
                          <%=loginCustomer.getMail()%>
                       </dd>
                      </div>
                    </div>
                  </dl>
                </div>

                  <input class="btn btn-dark" type="submit" value="お支払い情報の入力へ">

                <div class="checkbox">
                    <p class="not_to_customer">上記以外の宛先へ送付する場合、チェックを入れて下記のフォームに入力してください。</p>
                      <input type="checkbox" name="not_to_customer" id="not_to_customer">
                </div>
                </div>
                <div class="col-2"></div>
                </div>

                <div class="form">
                  <dl class="form_list">
                    <div class="row">
                      <div class="col-sm-3 text-nowrap">
                        <dt class="input_title">お名前</dt>
                      </div>
                      <div class="col-sm-9 text-nowrap">
                        <dd class="form_list_input">
                             <label class="input_first_name" for="lastName">姓</label> <input type="text" name="lastName">
                             <label for="firstName">名</label> <input type="text" name="firstName">
                         </dd>
                       </div>
                      </div>
                  </dl>
                  <dl class="form_list">
                    <div class="row">
                      <div class="col-sm-3 text-nowrap">
                      <dt class="input_title">郵便番号</dt>
                    </div>
                    <div class="col-sm-9 text-nowrap">
                      <dd class="form_list_input">
                        <input type="text" placeholder="例）1080078" name="postal" >
                      </dd>
                    </div>
                  </dl>
                  <dl class="form_list">
                    <div class="row">
                      <div class="col-sm-3 text-nowrap">
                        <dt class="input_title">都道府県</dt>
                      </div>
                    <div class="col-sm-9 text-nowrap">
                      <dd class="form_list_input">
                        <select  name="pref" >
                          <option value="" selected>都道府県</option>
                            <option value="北海道">北海道</option>
                            <option value="青森県">青森県</option>
                            <option value="岩手県">岩手県</option>
                            <option value="宮城県">宮城県</option>
                            <option value="秋田県">秋田県</option>
                            <option value="山形県">山形県</option>
                            <option value="福島県">福島県</option>
                            <option value="茨城県">茨城県</option>
                            <option value="栃木県">栃木県</option>
                            <option value="群馬県">群馬県</option>
                            <option value="埼玉県">埼玉県</option>
                            <option value="千葉県">千葉県</option>
                            <option value="東京都">東京都</option>
                            <option value="神奈川県">神奈川県</option>
                            <option value="新潟県">新潟県</option>
                            <option value="富山県">富山県</option>
                            <option value="石川県">石川県</option>
                            <option value="福井県">福井県</option>
                            <option value="山梨県">山梨県</option>
                            <option value="長野県">長野県</option>
                            <option value="岐阜県">岐阜県</option>
                            <option value="静岡県">静岡県</option>
                            <option value="愛知県">愛知県</option>
                            <option value="三重県">三重県</option>
                            <option value="滋賀県">滋賀県</option>
                            <option value="京都府">京都府</option>
                            <option value="大阪府">大阪府</option>
                            <option value="兵庫県">兵庫県</option>
                            <option value="奈良県">奈良県</option>
                            <option value="和歌山県">和歌山県</option>
                            <option value="鳥取県">鳥取県</option>
                            <option value="島根県">島根県</option>
                            <option value="岡山県">岡山県</option>
                            <option value="広島県">広島県</option>
                            <option value="山口県">山口県</option>
                            <option value="徳島県">徳島県</option>
                            <option value="香川県">香川県</option>
                            <option value="愛媛県">愛媛県</option>
                            <option value="高知県">高知県</option>
                            <option value="福岡県">福岡県</option>
                            <option value="佐賀県">佐賀県</option>
                            <option value="長崎県">長崎県</option>
                            <option value="熊本県">熊本県</option>
                            <option value="大分県">大分県</option>
                            <option value="宮崎県">宮崎県</option>
                            <option value="鹿児島県">鹿児島県</option>
                            <option value="沖縄県">沖縄県</option>
                          </select>
                        </dd>
                      </div>
                    </dl>
                    <dl class="form_list">
                      <div class="row">
                        <div class="col-sm-3 text-nowrap">
                        <dt class="input_title">市町村</dt>
                      </div>
                      <div class="col-sm-9 text-nowrap">
                        <dd class="form_list_input">
                          <input class="input_adress" type="text" name="muni">
                        </dd>
                      </div>
                    </dl>
                    <dl class="form_list">
                      <div class="row">
                        <div class="col-sm-3 text-nowrap">
                        <dt class="input_title">番地以降</dt>
                      </div>
                      <div class="col-sm-9 text-nowrap">
                        <dd class="form_list_input">
                          <input class="input_adress" type="text" name="stAd">
                        </dd>
                      </div>
                    </dl>
                    <dl class="form_list">
                      <div class="row">
                        <div class="col-sm-3 text-nowrap">
                        <dt class="input_title">電話番号</dt>
                      </div>
                      <div class="col-sm-9 text-nowrap">
                        <dd class="form_list_input">
                          <input type="text" placeholder="例）01201111111" name="tell">
                        </dd>
                      </div>
                    </dl>
                    <dl class="form_list">
                      <div class="row">
                        <div class="col-sm-3 text-nowrap">
                          <dt class="input_title">メールアドレス</dt>
                        </div>
                        <div class="col-sm-9 text-nowrap">
                          <dd class="form_list_input">
                            <input class="input_adress" type="email" name="mail">
                          </dd>
                        </div>
                      </div>
                    </dl>
                </div>

                <div class="button_box">
                <input class="btn btn-dark" type="submit" value="お支払い情報の入力へ">
                </div>
              </div>
              </form>
            </div>
          </div>


      </div>

</div>
<%} %>
</body>
</html>