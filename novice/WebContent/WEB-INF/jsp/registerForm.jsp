<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String erMessage = (String)request.getAttribute("address_exist"); %>
<!DOCTYPE html>
<html lang=ja>
<head>
	<meta charset="UTF-8">
	<title>新規会員登録</title>
	<script type="text/javascript" src="/novice/js/registerScript.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="/novice/css/style.css">

</head>
<body>
	<div class="container">
		<div class="row justify-content-sm-center">
			<div class="col-sm-auto">
				<header>
					<h2>新規会員登録</h2>
				</header>
				<p class="text_normal">
				会員登録を行います。入力後に確認ボタンを押してください。
				</p>
			</div>
		</div>

		<div class="form">
			<form action="/novice/RegisterUser" method="post">

				<dl class="form_list">
					<div class="row">
						<div class="col-sm-3">
							<dt class="input_title">お名前</dt>
						</div>
						<div class="col-sm-9">
							<dd class="form_list_input">
						 			<label class="input_first_name" for="lastName">姓</label> <input type="text" name="lastName" required>
						 			<label for="firstName">名</label> <input type="text" name="firstName" required>
					 		</dd>
					 	</div>
						</div>
				</dl>

				<dl class="form_list">
					<div class="row">
						<div class="col-sm-3">
							<dt class="input_title">メールアドレス</dt>
						</div>
						<div class="col-sm-9">
							<dd class="form_list_input">
								<input class="input_adress" type="email" name="mail" required>
								<%if(erMessage != null){ %>
								<p><span class="alert_message"><%= erMessage %></span></p>
								<%} %>
							</dd>
						</div>
					</div>
				</dl>

				<dl class="form_list">
					<div class="row">
						<div class="col-sm-3">
							<dt class="input_title">パスワード</dt>
					</div>
					<div class="col-sm-9">
						<dd class="form_list_input">
									<input type="password" name="pass" required="required"
									pattern="^[a-zA-Z0-9#$%&amp;'*,!-`{}()=+\[\].^:;?@~]{6,30}$"
									title="6〜30桁の半角英数字・記号で入力してください。"
						 			autocomplete="new-password" id=password class="form_input">
						</dd>
						<dd class="form_list_input">
									<input type="password" placeholder="確認のため再度入力してください" name="confirm" required="required"
									pattern="^[a-zA-Z0-9#$%&amp;'*,!-`{}()=+\[\].^:;?@~]{6,30}$"
									title="6〜30桁の半角英数字・記号で入力してください。"
						 			autocomplete="new-password" id=confirm oninput="CheckPassword(this)" class="form_input">
						</dd>
					</div>
			</dl>

			<dl class="form_list">
				<div class="row">
					<div class="col-sm-3">
					<dt class="input_title">郵便番号</dt>
				</div>
				<div class="col-sm-9">
					<dd class="form_list_input">
						<input type="text" placeholder="例）1080078" name="postal" pattern="^[0-9]{7}$"
									title="ハイフンなし7桁の数字で入力してください"required>
					</dd>
				</div>
			</dl>

			<dl class="form_list">
				<div class="row">
					<div class="col-sm-3">
						<dt class="input_title">都道府県</dt>
					</div>
				<div class="col-sm-9">
					<dd class="form_list_input">
						<select name="pref" required>
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
					<div class="col-sm-3">
					<dt class="input_title">市町村</dt>
				</div>
				<div class="col-sm-9">
					<dd class="form_list_input">
						<input class="input_adress" type="text" name="muni" required>
					</dd>
				</div>
			</dl>

			<dl class="form_list">
				<div class="row">
					<div class="col-sm-3">
					<dt class="input_title">番地以降</dt>
				</div>
				<div class="col-sm-9">
					<dd class="form_list_input">
						<input class="input_adress" type="text" name="stAd" required>
					</dd>
				</div>
			</dl>

			<dl class="form_list">
				<div class="row">
					<div class="col-sm-3">
					<dt class="input_title">電話番号</dt>
				</div>
				<div class="col-sm-9">
					<dd class="form_list_input">
						<input type="text" placeholder="例）01201111111" name="tell" required>
					</dd>
				</div>
			</dl>

			<dl class="form_list">
				<div class="row justify-content-sm-center">
					<div class="col-sm-auto">
						<a href="/novice/index.jsp" class="btn btn-outline-dark">TOP</a>
						<input class="btn btn-dark" type="submit" value="確認">
					</div>
				</div>
			</dl>
</form>
		</div>
	</div>
</body>
</html>