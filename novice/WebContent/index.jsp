<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Customer" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/styleS.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="icon" type="image/png" href="favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
    <title>
      novice onlineshop
    </title>
  </head>
  <body>
    <header>
      <div class="header_box">
        <h1><a href="index.jsp"><img src="img/logo.png" alt="novice"></a></h1>
      </div>
      <div id="shopping_icon2">
        <span class="fa-stack">
          <a href="Cart?action=view_cart"><i class="fas fa-shopping-cart fa-stack-1x"></i></a>
      </span>
      </div>
      <!--sp用ハンバーガーメニュー-->
            <div id="nav-drawer">
              <input id="nav-input" type="checkbox" class="nav-unshown">
              <label id="nav-open" for="nav-input"><span></span></label>
              <label class="nav-unshown" id="nav-close" for="nav-input"></label>
              <div id="nav-content" class="nav_inner">
                  <li><a href="../index.jsp">HOME</a></li>
                  <li><a href="/pages/about.html">ABOUT</a></li>
                  <li>
                    <a href="#">CATEGORY</a></li>
                  <li><a href="#profile">PROFILE</a></li>
                  <div class="serch">
                    <form id="form3" action="Search" method="get">
            <input id="sbox2"  id="s" name="keyWord" type="text" placeholder="フリーワードを入力"
             pattern=".*\S+.*" title="スペースのみでは検索できません" maxlength="20" required form="form3"/>
            <button type="submit" id="sbtn2" form="form3"><i class="fas fa-search"></i></button>
          </form>
                  </div>
				 <%if(loginCustomer==null){ %>
					<li class="small"><a href="../Login?action=login">ログイン</a><a href="../RegisterUser">会員登録</a></li>
				<%}else{ %>
					<li class="small"><a href="../AccountDetails?action=mypage">マイページ</a><a href="../Login?action=logout">ログアウト</a></li>
				<%} %>
              </div>
          </div>
     <!---->
      <nav class="navi_top">
        <div class="serch">
          <form id="form2" action="Search" method="get">
            <input id="sbox2"  id="s" name="keyWord" type="text" placeholder="フリーワードを入力"
             pattern=".*\S+.*" title="スペースのみでは検索できません" maxlength="20" required form="form2"/>
            <button type="submit" id="sbtn2" form="form2"><i class="fas fa-search"></i></button>
          </form>
        </div>
        <div id="shopping_icon">
          <span class="fa-stack">
            <a href="#"><i class="fas fa-square-full fa-stack-2x icon_color"></i></a>
            <a href="Cart?action=view_cart"><i class="fas fa-shopping-cart fa-stack-1x fa-inverse icon_color"></i></a>
        </span>
        </div>
        <div class="menu">
        <ul>
       	 <%if(loginCustomer==null){ %>
          <li><a href="Login?action=login">Log In</a>
          <%}else{ %>
          <li><a href="AccountDetails?action=mypage">マイページ</a>
          <%} %>
          <ul>
          	<%if(loginCustomer==null){ %>
            <li><a href="RegisterUser">会員登録</a></li>
            <li><a href="Login?action=login">ログイン</a></li>
            <%}else{ %>
            <li><a href="Login?action=logout">ログアウト</a></li>
            <%} %>
          </ul>
        </ul>
      </div>
    </nav>
        <div class="list">
          <li><a href="index.jsp">HOME</a></li>
          <li><a href="pages/about.html">ABOUT</a></li>
        <ul>
          <li><a href="#">CATEGORY</a>
          <ul>
            <li>
            <form method="post" name="cate1" action="Search?action=category">
            <input type="hidden" name="category" value="Tee" />
            <a href="javascript:cate1.submit()">Tee</a>
            </form>
            </li>
            <li>
            <form method="post" name="cate2" action="Search?action=category">
            <input type="hidden" name="category" value="Case" />
            <a href="javascript:cate2.submit()">Case</a>
            </form>
            </li>
        	<li>
            <form method="post" name="cate3" action="Search?action=category">
            <input type="hidden" name="category" value="PostCard" />
            <a href="javascript:cate3.submit()">Post Card</a>
            </form>
            </li>
          </ul>
        </ul>
          <li><a href="#profile">PROFILE</a></li>
        </ul>
      </div>
        <div class="top_img">
          <video height="450" controls><source src="/mov/novice_PR.MP4"></video>
        </div>
      </div>
    </header>
    <main role="main">
    <section class="item">
      <div class="text_border">
        <h2>ITEM</h2>
      </div>
    <ul class="itemlist">
      <li>
      	<form method="post" name="product1" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="TS-H01" />
      	<a href="javascript:product1.submit()">
      	<img src="img2/TS-H01.png" alt="sample">
      	<p class="name">plants tee</p><p class="price">2,500円</p></a>
      	</form>
	</li>
	<li>
      	<form method="post" name="product2" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="SC-H01" />
      	<a href="javascript:product2.submit()">
      	<img src="img2/SC-H01.png" alt="sample">
        <p class="name">quiet tears/iPhone11</p><p class="price">1,500円</p></a>
        </form>
	</li>
	<li>
      	<form method="post" name="product3" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="TS-H02" />
      	<a href="javascript:product3.submit()">
      	<img src="img2/TS-H02.png" alt="sample">
        <p class="name">cat tee</p><p class="price">2,500円</p></a>
        </form>
	</li>
	<li>
      	<form method="post" name="product4" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="SC-H02" />
      	<a href="javascript:product4.submit()">
      	<img src="img2/SC-H02.png" alt="sample">
        <p class="name">caddly/iPhone11</p><p class="price">1,500円</p></a>
        </form>
	</li>
      <li>
      	<form method="post" name="product5" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="TS-S01" />
      	<a href="javascript:product5.submit()">
      	<img src="img2/TS-S01.png" alt="sample">
        <p class="name">cat tee2</p><p class="price">2,500円</p></a>
        </form>
      </li>
      <li>
      	<form method="post" name="product6" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="SC-S01" />
      	<a href="javascript:product6.submit()">
      	<img src="img2/SC-S01.png" alt="sample">
        <p class="name">chill out/iPhone11</p><p class="price">1,500円</p></a>
        </form>
        </li>
      <li>
      	<form method="post" name="product7" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="TS-S02" />
      	<a href="javascript:product7.submit()">
      	<img src="img2/TS-S02.png" alt="sample">
        <p class="name">sleeping tee</p><p class="price">2,500円</p></a>
        </form>
      </li>
      <li>
      <form method="post" name="product8" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="SC-S02" />
      	<a href="javascript:product8.submit()">
      	<img src="img2/SC-S02.png" alt="sample">
        <p class="name">train/iPhone11</p><p class="price">1,500円</p></a>
        </form>
      </li>
      <li>
      <form method="post" name="product9" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="TS-N01" />
      	<a href="javascript:product9.submit()">
      	<img src="img2/TS-N01.png" alt="sample">
        <p class="name">relax tee</p><p class="price">2,500円</p></a>
        </form>
      </li>
      <li>
       <form method="post" name="product10" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="SC-N01" />
      	<a href="javascript:product10.submit()">
      	<img src="img2/SC-N01.png" alt="sample">
        <p class="name">face/iPhone11</p><p class="price">1,500円</p></a>
        </form>
      </li>
      <li>
       <form method="post" name="product11" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="TS-N02" />
      	<a href="javascript:product11.submit()">
      	<img src="img2/TS-N02.png" alt="sample">
        <p class="name">parks people tee</p><p class="price">2,500円</p></a>
        </form>
      </li>
      <li>
      <form method="post" name="product12" action="ProductView?action=product_info">
      	<input type="hidden" name="productCode" value="SC-N02" />
      	<a href="javascript:product12.submit()">
      	<img src="img2/SC-N02.png" alt="sample">
        <p class="name">paddy/iPhone11</p><p class="price">2,500円</p></a>
        </form>
      </li>
    </section>
    <section class="profile" id="profile">
      <div class="title">
        <h3>PROFILE</h3>
      </div>
      <div class="profile_icon">
        <li><a href="./pages/profile_honda.html"><img src="img/honda.png" alt="本田">
          <p class="name">Honda.</p></a></li>
        <li><a href="./pages/profile_segawa.html"><img src="img/segawa.png" alt="瀬川">
          <p class="./name">Segawa.</p></a></li>
        <li><a href="./pages/profile_nakamura.html"><img src="img/nakamura.png" alt="中村">
          <p class="name">Nakamura.</p></a></li>
      </div>
    </section>
    </main>
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