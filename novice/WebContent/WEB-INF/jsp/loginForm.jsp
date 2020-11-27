<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/loginstyle.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
    <title>
      novice onlineshop Login
    </title>
</head>
<body>
<header>
      <div class="header_box">
        <h1><a href="../index.jsp"><img src="../img/logo.png" alt="novice"></a></h1>
        <div class="title">
          <h2>Log In</h2>
        </div>
    </header>
    <div id="text_box">
    <div class="email_box">
    <form action="../Login" method="post">
      <label class="ef">
       <input type="text" name = "mail"  placeholder="Email">
      </label>
    </div>
    <div class="password_box">
      <label class="ef">
      <input type="password" name = "pass" placeholder="Password">
      </label>
    </div>
    </div>
    <div class="login_box">
      <input type="submit" value="ログイン" class="login">
      <p><a href="../RegisterUser">新規登録はこちら</a></p>
    </form>
    </div>
  </div>



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