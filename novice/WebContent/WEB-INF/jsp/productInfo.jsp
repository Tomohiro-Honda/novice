<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.Product, model.beans.Customer, java.util.List" %>
<%Customer loginCustomer = (Customer)session.getAttribute("login_customer");%>
<%Product sp = (Product)request.getAttribute("selectedProduct"); %>
<%request.setAttribute("selectedProduct", sp); %>
<%String pName = sp.getProductName();String pCode = sp.getProductCode();%>
<% int price = sp.getPrice(); %><!-- int stock = sp.getStock(); -->
<%String message = (String)request.getAttribute("message"); %>
<%String text = sp.getText(); %>
<%String producer = (String)request.getAttribute("producer"); %>
<%List<Integer> stockList = (List<Integer>)request.getAttribute("stockList"); %>
<% int sStock = stockList.get(0);%>
<%  int mStock = stockList.get(1);%>
<%  int lStock =stockList.get(2); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/styleS.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="icon" type="image/png" href="../favicon.png" sizes="16x16 24x24 32x32 48x48 64x64" />
    <title>
      novice onlineshop
    </title>
  </head>
  <body bgcolor onLoad="functionName()">
    <header>
      <div class="header_box">
        <h1><a href="../index.jsp"><img src="img/logo.png" alt="novice"></a></h1>
      </div>
      <div id="shopping_icon2">
        <span class="fa-stack">
          <a href="../Cart?action=view_cart"><i class="fas fa-shopping-cart fa-stack-1x"></i></a>
      </span>
      </div>
       <!--sp用ハンバーガーメニュー-->
            <div id="nav-drawer">
              <input id="nav-input" type="checkbox" class="nav-unshown">
              <label id="nav-open" for="nav-input"><span></span></label>
              <label class="nav-unshown" id="nav-close" for="nav-input"></label>
              <div id="nav-content" class="nav_inner">
                  <li><a href="../index.jsp">HOME</a></li>
                  <li><a href="../pages/about.html">ABOUT</a></li>
                  <li>
                    <a href="#">CATEGORY</a></li>
                  <li><a href="../index.jsp#profile">PROFILE</a></li>
                  <div class="serch">
                    <form id="form3" action="../Search" method="get">
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
          <form id="form2" action="../Search" method="get">
            <input id="sbox2"  id="s" name="keyWord" type="text" placeholder="フリーワードを入力"
             pattern=".*\S+.*" title="スペースのみでは検索できません" maxlength="20" required form="form2"/>
            <button type="submit" id="sbtn2" form="form2"><i class="fas fa-search"></i></button>
          </form>
        </div>
        <div id="shopping_icon">
          <span class="fa-stack">
            <a href="#"><i class="fas fa-square-full fa-stack-2x icon_color"></i></a>
            <a href="../Cart?action=view_cart"><i class="fas fa-shopping-cart fa-stack-1x fa-inverse icon_color"></i></a>
        </span>
        </div>
        <div class="menu">
        <ul>
       	 <%if(loginCustomer==null){ %>
          <li><a href="../Login?action=login">Log In</a>
          <%}else{ %>
          <li><a href="../AccountDetails?action=mypage">マイページ</a>
          <%} %>
          <ul>
          	<%if(loginCustomer==null){ %>
            <li><a href="../RegisterUser">会員登録</a></li>
            <li><a href="../Login?action=login">ログイン</a></li>
            <%}else{ %>
            <li><a href="../Login?action=logout">ログアウト</a></li>
            <%} %>
          </ul>
        </ul>
      </div>
    </nav>
    <div class="list">
      <li><a href="../index.jsp">HOME</a></li>
      <li><a href="../pages/about.html">ABOUT</a></li>
    <ul>
      <li><a href="#">CATEGORY</a>
      <ul>
            <li>
            <form method="post" name="cate1" action="../Search?action=category">
            <input type="hidden" name="category" value="Tee" />
            <a href="javascript:cate1.submit()">Tee</a>
            </form>
            </li>
            <li>
            <form method="post" name="cate2" action="../Search?action=category">
            <input type="hidden" name="category" value="Case" />
            <a href="javascript:cate2.submit()">Case</a>
            </form>
            </li>
        	<li>
            <form method="post" name="cate3" action="../Search?action=category">
            <input type="hidden" name="category" value="PostCard" />
            <a href="javascript:cate3.submit()">Post Card</a>
            </form>
            </li>
          </ul>
    </ul>
      <li><a href="../index.jsp#profile">PROFILE</a></li>
    </ul>
  </div>
    </header>
     <main id="product" role="main">
     	<div class="sp_img">
        	<h4><a href="#"><img src="../img2/<%=pCode%>.png" alt="<%=pName%>"></a></h4>
      	</div>
      	<div class="product_menu">
         <%if(message!=null) {%>
	<p><%=message%></p>
	<%} %>
      <h3>
       <p><%=pName %><p>
        <p><%=price%>円(税込み)<p/>
      </h3>

      <form name="formName" action="../Cart?action=add_cart" method="post">
      <div class="selectbox_color">
        size :
        <select name = "size" onChange="functionName()">
<option value = "S">S</option>
<option value = "M">M</option>
<option value = "L">L</option>
</select>
      </div>
       <div class="selectbox_color">
        color :
        <select name="type">
          <option value="withe">white</option>
        </select>
        </div>
        <div class="selectbox_color">
        quantity :
		<select name="quantity" required>
</select>
      </div>
		<input type="hidden" name="productCode" value="<%=pCode %>" />
		<input type="hidden" name="productName" value="<%=pName %>" />
		<input type="hidden" name="price" value="<%=price %>" />
		<input type="hidden" name="sStock" value="<%=sStock%>" />
		<input type="hidden" name="mStock" value="<%=mStock%>"/>
		<input type="hidden" name="lStock" value="<%=lStock%>"/>
      	<div class="title_buy">
      	<input type= "Submit" value="ADD TO CART" class="buy_cart"/>
      </div>
      </form>
      <div class="size_text">
        【サイズ】<br>
         S size / 肩幅:40cm 身幅:49cm 着丈:63cm 袖丈:23cm<br>
         M size / 肩幅42cm 身幅51cm 着丈:65cm 袖丈:24cm<br>
         L size / 肩幅44cm 身幅53cm 着丈:67cm 袖丈:25cm
      </div>
      <div class="text">
        <p>
          <%=sp.getText() %><br>
          製作者 : <%=producer %>
        </p>
      </div>
    </div>
    <div class="pc_img">
      <h2><img src="../img2/<%=pCode%>.png" alt="<%=pName%>"></h2>
    </div>
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



<script type = "text/javascript">
function functionName()
    {
        var select1 = document.forms.formName.size; //変数select1を宣言
        var select2 = document.forms.formName.quantity; //変数select2を宣言

        select2.options.length = 0; // 選択肢の数がそれぞれに異なる場合、これが重要

        if (select1.options[select1.selectedIndex].value == "S")
            {
        		if(<%=sStock%>== 0){
        			select2.options[0] = new Option();
        			select2.options[0].value ="";
        			select2.options[0].text ="sold out";
        			select2.options[0].disabled = true;
        		}else{
        		for(var i =1; i <= <%=sStock%>; i++){
        			select2.options[i-1] = new Option(i);
        			select2.options[i-1].value = i;
        		}
        		}
            }

        else if (select1.options[select1.selectedIndex].value == "M")
            {
        	if(<%=mStock%>== 0){
    			select2.options[0] = new Option("sold out")
    		}else{
    		for(var i =1; i <= <%=mStock%>; i++){
    			select2.options[i-1] = new Option(i);
    			select2.options[i-1].value = i;
    		}
    		}
            }

        else if (select1.options[select1.selectedIndex].value == "L")
            {
        	if(<%=lStock%>== 0){
    			select2.options[0] = new Option("sold out")
    		}else{
    		for(var i =1; i <= <%=lStock%>; i++){
    			select2.options[i-1] = new Option(i);
    			select2.options[i-1].value = i;
    		}
    		}
            }
    }

//-->
</script>

  </body>