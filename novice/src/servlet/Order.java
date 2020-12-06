package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.CartItem;
import model.beans.Customer;
import model.beans.OrderSheet;
import model.beans.Product;
import model.logic.cart.CartViewLogic;
import model.logic.order.FinalCheckLogic;
import model.logic.order.MailLogic;
import model.logic.order.OrderAcceptedLogic;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 注文受付、処理するクラス
     */
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		//「注文」をリクエストされたときの処理。送り先、支払いなどのフォーム記入画面へ
				if(action.equals("order_form")) {
					Customer loginCustomer = (Customer)session.getAttribute("login_customer");
					//ログインしていない場合
					if(loginCustomer==null) {
						List<CartItem> guestCartItems = (List<CartItem>)session.getAttribute("guestCartItems");
						if(guestCartItems == null) {//セッション切れの場合
							rd =request.getRequestDispatcher("index.jsp");
						}else {
						rd =request.getRequestDispatcher("/WEB-INF/jsp/orderFormAdressGuest.jsp");
						}
					}else {
					rd =request.getRequestDispatcher("/WEB-INF/jsp/orderFormAdress.jsp");
					}
					rd.forward(request, response);
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		RequestDispatcher rd = null;

		/**------------------------支払い情報入力画面のリクエスト-------------------------**/
		if(action.equals("payment")) {
			Customer loginCustomer = (Customer)session.getAttribute("login_customer");
			Customer sendCustomer = new Customer();
			//ログイン確認
			if(loginCustomer==null) {
				List<CartItem> guestCartItems = (List<CartItem>)session.getAttribute("guestCartItems");
				if(guestCartItems == null) {//セッション切れの場合
					rd =request.getRequestDispatcher("index.jsp");
				}else {
					sendCustomer.setFirstName(request.getParameter("firstName"));
					sendCustomer.setLastName(request.getParameter("lastName"));
					sendCustomer.setPostal(request.getParameter("zip01"));
					sendCustomer.setPref(request.getParameter("pref01"));
					sendCustomer.setMuni(request.getParameter("addr01"));
					sendCustomer.setStAd(request.getParameter("stAd"));
					sendCustomer.setTell(request.getParameter("tell"));
					sendCustomer.setMail(request.getParameter("mail"));
					session.setAttribute("send_customer", sendCustomer);
					rd = request.getRequestDispatcher("/WEB-INF/jsp/orderFormPayment.jsp");
				}

			}else {
				//会員の住所とは異なる住所へ送る場合
				if(request.getParameter("not_to_customer")!=null) {
					sendCustomer.setFirstName(request.getParameter("firstName"));
					sendCustomer.setLastName(request.getParameter("lastName"));
					sendCustomer.setPostal(request.getParameter("zip01"));
					sendCustomer.setPref(request.getParameter("pref01"));
					sendCustomer.setMuni(request.getParameter("addr01"));
					sendCustomer.setStAd(request.getParameter("stAd"));
					sendCustomer.setTell(request.getParameter("tell"));
					sendCustomer.setMail(request.getParameter("mail"));
				}else{
					sendCustomer = loginCustomer;
				}
				session.setAttribute("send_customer", sendCustomer);
				rd = request.getRequestDispatcher("/WEB-INF/jsp/orderFormPayment.jsp");
			}
			rd.forward(request, response);
		}

		/**------------------------注文確認画面へ進む------------------------**/
		if(action.equals("confirm")) {
			Customer loginCustomer = (Customer)session.getAttribute("login_customer");
			CartViewLogic cartViewLogic = new CartViewLogic(); //注文確認画面で表示するカートの商品リストを用意
			List<CartItem> cartItems = null;
			//ログイン確認
			if(loginCustomer==null) {
				List<CartItem> guestCartItems = (List<CartItem>)session.getAttribute("guestCartItems");
				if(guestCartItems == null) {//セッション切れの場合
					rd =request.getRequestDispatcher("index.jsp");
				}else {
					cartItems = cartViewLogic.viewCart(guestCartItems);
					OrderSheet orderSheet = new OrderSheet();
					orderSheet.setOrderItems(cartItems); //購入する商品の情報をセット
					String payment = (String)request.getParameter("payment");
					orderSheet.setPayment(payment);//支払い方法の情報をセット
					session.setAttribute("order_sheet", orderSheet);
					rd = request.getRequestDispatcher("/WEB-INF/jsp/orderConfirm.jsp");
				}
			}else {
				int customerId = loginCustomer.getId();
				cartItems = cartViewLogic.viewCart(customerId);
				OrderSheet orderSheet = new OrderSheet();
				orderSheet.setOrderItems(cartItems); //購入する商品の情報をセット
				String payment = (String)request.getParameter("payment");
				orderSheet.setPayment(payment);//支払い方法の情報をセット
				session.setAttribute("order_sheet", orderSheet);
				rd = request.getRequestDispatcher("/WEB-INF/jsp/orderConfirm.jsp");
			}
			rd.forward(request, response);
		}

		/**------------------------注文完了処理------------------------**/
		if(action.equals("ordered")) {
			Customer loginCustomer = (Customer)session.getAttribute("login_customer");
			Customer sendCustomer = (Customer)session.getAttribute("send_customer");
			OrderSheet orderSheet = (OrderSheet)session.getAttribute("order_sheet");
			FinalCheckLogic finalCheckLogic = new FinalCheckLogic();
			List<CartItem> guestCartItems = (List<CartItem>)session.getAttribute("guestCartItems");


			if(orderSheet==null) {
				//オーダーシートセッション切れの場合はトップページへ返す
				rd =request.getRequestDispatcher("index.jsp");
			}else {
				//注文する商品の在庫数を引き出す。
				List<Product> stockList = finalCheckLogic.getStockList(orderSheet.getOrderItems());
				//在庫数を超えている商品をリストに入れる。
				List<CartItem> alertList = finalCheckLogic.finalCheck(stockList, orderSheet.getOrderItems());
				if(alertList.isEmpty()) {//在庫数を超えている商品がなければ注文完了画面を出す。
					//orderSheetインスタンスに情報を格納する
					if(loginCustomer==null) {
						orderSheet.setId(1);//ゲストの場合は1
					}else {
					orderSheet.setId(loginCustomer.getId());//ログインユーザーのID
					}
					orderSheet.setDestinationPostal(sendCustomer.getPostal()); //届け先郵便番号

					StringBuilder destination = new StringBuilder();///////////////
					destination.append(sendCustomer.getPref());//                          //
					destination.append(sendCustomer.getMuni());//   届け先住所   //
					destination.append(sendCustomer.getStAd());//                      //
					orderSheet.setDestinationAdress(destination.toString());////

					orderSheet.setShipPrice(Integer.parseInt((String)request.getParameter("shipping_value"))); //送料
					orderSheet.setCodPrice(Integer.parseInt((String)request.getParameter("cod_value"))); //手数料
					orderSheet.setTotalPrice(Integer.parseInt((String)request.getParameter("total_value"))); //合計金額

					//データベースへ注文情報を書き込む、カートの中身を空にする。
					OrderAcceptedLogic orderAcceptedLogic = new OrderAcceptedLogic();
					Timestamp time = orderAcceptedLogic.orderDone(orderSheet);
					if(time != null) {
						//注文ありがとうございましたページへ移動、注文確認メールを送信
						MailLogic mailLogic = new MailLogic();
						if(loginCustomer==null) {
							int id = 1;
							int orderID = orderAcceptedLogic.getOrderID(id, time);
							mailLogic.thanksMail(sendCustomer, orderID);
						}else {
							int id = loginCustomer.getId();
							int orderID = orderAcceptedLogic.getOrderID(id, time);
							mailLogic.thanksMail(loginCustomer, orderID);
						}
						session.removeAttribute("order_sheet");
						session.removeAttribute("send_customer");
						session.removeAttribute("guestCartItems");
						rd = request.getRequestDispatcher("/WEB-INF/jsp/orderDone.jsp");
						rd.forward(request, response);
						}else {
							rd = request.getRequestDispatcher("/index.jsp");//注文失敗ページを作る
							session.removeAttribute("order_sheet");
							session.removeAttribute("send_customer");
						}

					}else {//買い物カゴへ戻して個数変更を促す
						CartViewLogic cartViewLogic = new CartViewLogic();
						int customerId = loginCustomer.getId();
						List<CartItem> cartItems = cartViewLogic.viewCart(customerId);
						request.setAttribute("cartItems", cartItems);
						rd = request.getRequestDispatcher("/WEB-INF/jsp/cartView.jsp");

						//メッセージの作成
						StringBuilder message = new StringBuilder("商品");
						for(int i=0; i < alertList.size() ; i++) {
							String code = alertList.get(i).getProductCode();
							String  name = alertList.get(i).getProductName();
							String size = alertList.get(i).getSize();
							String type = alertList.get(i).getType();
							message.append("『");
							message.append(name);
							if(code.contains("TS")) {
								message.append("　" + size + "サイズ");
							}
							message.append("　" + type);
							message.append("』");
							if(alertList.get(i) != alertList.get(alertList.size() -1)) {
								message.append("、");
							}
						}
						message.append("の個数が在庫数を超えています。");
						String alertMessage = message.toString();
						request.setAttribute("message", alertMessage);
						session.removeAttribute("order_sheet");
						session.removeAttribute("send_customer");
						rd.forward(request, response);
					}
				}
			}
		}
	}



