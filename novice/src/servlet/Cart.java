package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import model.beans.Product;
import model.logic.cart.AddCartLogic;
import model.logic.cart.CartCheckLogic;
import model.logic.cart.CartUpdateLogic;
import model.logic.cart.CartViewLogic;
import model.logic.cart.DeleteItemLogic;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Cart() {
        super();
    }

	/**
	 * カート処理のコントローラー
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//サーブレットクラスの動作を決定する 「action」の値をリクエストパラメーターから取得
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		/**----------------------カートに入った商品を見る----------------------**/
		if(action.equals("view_cart")) {

			Customer loginCustomer = (Customer)session.getAttribute("login_customer");
			List<CartItem> cartItems = null;
			RequestDispatcher rd = null;
			String message ;
			CartViewLogic cartViewLogic = new CartViewLogic();

			//ログインしていない場合はゲスト用カートを見る
			if(loginCustomer == null) {
				List<CartItem> guestCartItems = (List<CartItem>)session.getAttribute("guestCartItems");
				if(guestCartItems==null || guestCartItems.isEmpty()){
					cartItems = new ArrayList<CartItem>();
				}else {
				cartItems = cartViewLogic.viewCart(guestCartItems);
				}
				session.setAttribute("cartItems", cartItems);
			}else {
				//ログインしている場合の処理
				int customerId = loginCustomer.getId();
				cartItems = cartViewLogic.viewCart(customerId);
				session.setAttribute("cartItems", cartItems);
			}
			rd = request.getRequestDispatcher("/WEB-INF/jsp/cartView.jsp");
			message = (String)session.getAttribute("updateMessage");
			request.setAttribute("message", message);
			session.removeAttribute("updateMessage");
			rd.forward(request, response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();


		/**---------------------------------カートに商品を追加----------------------------------------**/
		if(action.equals("add_cart")) {

			Customer loginCustomer = (Customer)session.getAttribute("login_customer");
			RequestDispatcher rd = null;

			//更新による二重リクエスト防止
			if(session.getAttribute("lookItem") == null) {
				rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				} else {

					AddCartLogic addCartLogic = new AddCartLogic();
					CartCheckLogic cartCheckLogic = new CartCheckLogic();

					String productCode = request.getParameter("productCode");
					String size = request.getParameter("size");
					String type = request.getParameter("type");
					String indivCode = addCartLogic.generateIndivCode(productCode, size, type);
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					int sStock = 0;
					int mStock = 0;
					int lStock = 0;
					int scStock = 0;
					if(size!=null) {
					sStock = Integer.parseInt(request.getParameter("sStock"));
					mStock = Integer.parseInt(request.getParameter("mStock"));
					lStock = Integer.parseInt(request.getParameter("lStock"));
					}else {
					scStock = Integer.parseInt(request.getParameter("scStock"));
					}
					int stock = addCartLogic.selectStockValue(size, sStock, mStock, lStock, scStock);

					//ログインしてない場合はゲストカートを使う
					if (loginCustomer==null){
						List<CartItem> guestCartItems = (List<CartItem>)session.getAttribute("guestCartItems");

						//ゲスト用カートを初めて使うかカートが空の場合、もしくはセッション切れの場合
						if(guestCartItems == null || guestCartItems.isEmpty()) {
							guestCartItems = new ArrayList<CartItem>();
							guestCartItems =  addCartLogic.addCart(guestCartItems, indivCode, quantity);
							session.setAttribute("guestCartItems", guestCartItems);//カート情報を更新
							session.removeAttribute("lookItem");//二重リクエスト防止用のオブジェクトを削除
							rd = request.getRequestDispatcher("/WEB-INF/jsp/itemAdded.jsp");
						}else { //ゲスト用カートのインスタンスがある場合
							//カートに同じ商品がないかチェック。
							int quantityInCart = cartCheckLogic.addCart(guestCartItems, indivCode);
								//カートに同じ商品が無い場合
								if(quantityInCart == 0) {
									guestCartItems = addCartLogic.addCart(guestCartItems, indivCode, quantity);
									session.setAttribute("guestCartItems", guestCartItems);//カート情報を更新
									session.removeAttribute("lookItem");//二重リクエスト防止用のオブジェクトを削除
									rd = request.getRequestDispatcher("/WEB-INF/jsp/itemAdded.jsp");
								}
								//カートに同じ商品があり、カートに入れた個数の合計が在庫数をオーバーしない場合
								else if(quantityInCart!=0 && quantityInCart + quantity <= stock){
									quantity += quantityInCart; //数量の値を更新
									guestCartItems = addCartLogic.guestAddCartPlus(guestCartItems, indivCode, quantity);
									session.setAttribute("guestCartItems", guestCartItems);//カート情報を更新
									session.removeAttribute("lookItem");
									rd = request.getRequestDispatcher("/WEB-INF/jsp/itemAdded.jsp");
								}else if (quantityInCart!=0 && quantityInCart + quantity > stock ){
									//カートに同じ商品があるが、カートに入れた個数の合計が在庫数をオーバーする場合
									request.setAttribute("message", "カートに入れられる量をオーバーしています");
									Product viewedProduct = (Product)request.getAttribute("selectedProduct");
									request.setAttribute("selectedProduct", viewedProduct);
									rd = request.getRequestDispatcher("/WEB-INF/jsp/productInfo.jsp");
								}
						}
						rd.forward(request, response);
					}
					//	ログイン確認できた場合
					else{
						int customerId = loginCustomer.getId();
				//カートに同じ商品がないかチェック。
				int quantityInCart = cartCheckLogic.addCart(customerId, indivCode);
					//カートに同じ商品が無い場合
					if(quantityInCart == 0) {
						addCartLogic.addCart(customerId, productCode, indivCode, size, type, quantity);
						rd = request.getRequestDispatcher("/WEB-INF/jsp/itemAdded.jsp");
						session.removeAttribute("lookItem");//二重リクエスト防止用のオブジェクトを削除
					}
					//カートに同じ商品があり、カートに入れた個数の合計が在庫数をオーバーしない場合
					else if(quantityInCart!=0 && quantityInCart + quantity <= stock){
						quantity += quantityInCart; //数量の値を更新
						addCartLogic.addCartPlus(customerId, indivCode, quantity);
						rd = request.getRequestDispatcher("/WEB-INF/jsp/itemAdded.jsp");
						session.removeAttribute("lookItem");
					}else if (quantityInCart!=0 && quantityInCart + quantity > stock ){
						//カートに同じ商品があるが、カートに入れた個数の合計が在庫数をオーバーする場合
						request.setAttribute("message", "カートに入れられる量をオーバーしています");
						Product viewedProduct = (Product)request.getAttribute("selectedProduct");
						request.setAttribute("selectedProduct", viewedProduct);
						rd = request.getRequestDispatcher("/WEB-INF/jsp/productInfo.jsp");
					}
					rd.forward(request, response);
			}
			}
		}

		/**----------------------カートの商品の個数を変更----------------------**/
		if(action.equals("quantity_update")) {

			Customer loginCustomer = (Customer)session.getAttribute("login_customer");
			RequestDispatcher rd = null;

			int numOfCodes = Integer.parseInt((String)request.getParameter("numOfCodes"));//変更する商品がいくつあるか。
			List<String> codeList = new ArrayList<String>();
			List<Integer> quantityList = new ArrayList<Integer>();

			//商品コードと数量の値をそれぞれ項目ごとにリストに入れる。
			for(int i =1; i != numOfCodes; i++ ) {
				String individualCode =(String)request.getParameter("individualCode" + i);
				Integer quantity = Integer.parseInt((String)request.getParameter("quantity" + i));
				codeList.add(individualCode);
				quantityList.add(quantity);
			}
			CartUpdateLogic cartUpdateLogic = new CartUpdateLogic();

			//ログインしてない場合
			if (loginCustomer==null){
				List<CartItem> cartItems = (List<CartItem>)session.getAttribute("cartItems");
				if(cartItems==null) {//セッションタイムアウトの場合
					rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}else {
					List<CartItem>guestCartItems = cartUpdateLogic.quantityUpdate(codeList, quantityList); //カートの個数を変更
					session.setAttribute("guestCartItems", guestCartItems);
					response.sendRedirect("Cart?action=view_cart");
				}
			}else {//ログイン確認できた場合
			//リストを渡して商品の個数を変更（変更していないものも全て更新される）
				int id = loginCustomer.getId();
			cartUpdateLogic.quantityUpdate(id, codeList, quantityList);
			String message = "個数を変更しました";
			session.setAttribute("updateMessage", message);
			response.sendRedirect("Cart?action=view_cart");
			}
		}

		/**-------------------------選択した商品をカートから削除する----------------------------**/
		if(action.equals("delete_item")) {
			Customer loginCustomer = (Customer)session.getAttribute("login_customer");
			RequestDispatcher rd = null;

			int numOfItems = Integer.parseInt((String)request.getParameter("numOfItems"));//カートに商品が何種類あるか
			String individualCode = null;
			String deleteCheck = null;
			List<String> delcodes = new ArrayList<String>(); //削除する商品を入れるためのリスト チェックボックス入れた商品

			for(int i =1; i <= numOfItems; i++ ) {
				individualCode =(String)request.getParameter("individualCode" + i);
				deleteCheck = (String)request.getParameter("check" +i);
				if(deleteCheck != null) { //チェックした商品をリストに入れる
					delcodes.add(individualCode);
				}
			}
			if(delcodes.isEmpty()) {//どのチェックボックスもnullの場合
				response.sendRedirect("Cart?action=view_cart");
				}else {

					DeleteItemLogic deleteItemLogic = new DeleteItemLogic();

					//---------ログインしてない場合---------
					if (loginCustomer==null){
						List<CartItem> guestCartItems = (List<CartItem>)session.getAttribute("guestCartItems");
						if(guestCartItems==null) {//セッションタイムアウトの場合
							rd = request.getRequestDispatcher("index.jsp");
							rd.forward(request, response);
						}else {
							guestCartItems = deleteItemLogic.delete(guestCartItems, delcodes);
							session.setAttribute("guestCartItems", guestCartItems);
						}
					}
					//---------ログイン確認できた場合----------
					else {
						int customerId = loginCustomer.getId();
						deleteItemLogic.delete(customerId, delcodes);
					}
					String message = "選択した商品を削除しました";
					session.setAttribute("updateMessage", message);
					response.sendRedirect("Cart?action=view_cart");
				}
		}
	}
}
