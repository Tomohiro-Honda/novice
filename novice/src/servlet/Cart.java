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

			//ログインしていない場合はログインを促す（ログイン前のカート機能は今後実装）
			if(loginCustomer == null) {
				message = "カートを見るにはログインしてください。";
				rd = request.getRequestDispatcher("/Login?action=login");
			}else {
				//ログインしている場合の処理
				CartViewLogic cartViewLogic = new CartViewLogic();
				String customerMail = loginCustomer.getMail();
				cartItems = cartViewLogic.viewCart(customerMail);
				request.setAttribute("cartItems", cartItems);
				rd = request.getRequestDispatcher("/WEB-INF/jsp/cartView.jsp");
				message = (String)session.getAttribute("updateMessage");
				request.setAttribute("message", message);
				session.removeAttribute("updateMessage");
			}
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
				rd = request.getRequestDispatcher("index.jsp");
//				rd.forward(request, response);
				} else {

			//ログインしてない場合
			if (loginCustomer==null){
				request.setAttribute("message", "ログインしてください");
				rd = request.getRequestDispatcher("index.jsp");//ログインページに直す
			}
			//ログイン確認できた場合
			else{
				String customerMail = loginCustomer.getMail();
				String productCode = request.getParameter("productCode");
				String productName = request.getParameter("productName");
				int price = Integer.parseInt(request.getParameter("price"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				int stock = Integer.parseInt(request.getParameter("stock"));

				//カートに同じ商品がないかチェック。
				CartCheckLogic cartCheckLogic = new CartCheckLogic();
				int quantityInCart = cartCheckLogic.addCart(customerMail, productCode);
					//カートに同じ商品が無い場合
					if(quantityInCart == 0) {
						AddCartLogic addCartLogic = new AddCartLogic();
						addCartLogic.addCart(customerMail, productCode, quantity);
						rd = request.getRequestDispatcher("/WEB-INF/jsp/itemAdded.jsp");
						session.removeAttribute("lookItem");//二重リクエスト防止用のオブジェクトを削除
					}
					//カートに同じ商品があり、カートに入れた個数の合計が在庫数をオーバーしない場合
					else if(quantityInCart!=0 && quantityInCart + quantity <= stock){
						AddCartLogic addCartLogic = new AddCartLogic();
						quantity += quantityInCart; //数量の値を更新
						addCartLogic.addCartPlus(customerMail, productCode, quantity);
						rd = request.getRequestDispatcher("/WEB-INF/jsp/itemAdded.jsp");
						session.removeAttribute("lookItem");
					}else if (quantityInCart!=0 && quantityInCart + quantity > stock ){
						//カートに同じ商品があるが、カートに入れた個数の合計が在庫数をオーバーする場合
						request.setAttribute("message", "カートに入れられる量をオーバーしています");
						Product viewedProduct = new Product(productCode, productName, price, stock);
						request.setAttribute("selectedProduct", viewedProduct);
						rd = request.getRequestDispatcher("/WEB-INF/jsp/productInfo.jsp");
					}
//					rd.forward(request, response);
			}
			}rd.forward(request, response);
		}

		/**----------------------カートの商品の個数を変更----------------------**/
		if(action.equals("quantity_update")) {

			Customer loginCustomer = (Customer)session.getAttribute("login_customer");
			RequestDispatcher rd = null;

			//ログインしてない場合
			if (loginCustomer==null){
				request.setAttribute("message", "ログインしてください");
				rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			//ログイン確認できた場合
			else {
			int numOfCodes = Integer.parseInt((String)request.getParameter("numOfCodes"));//変更する商品がいくつあるか。

			List<String> codeList = new ArrayList<String>();
			List<Integer> quantityList = new ArrayList<Integer>();
			String customerMail = loginCustomer.getMail();

			//商品コードと数量の値をそれぞれ項目ごとにリストに入れる。
			for(int i =1; i != numOfCodes; i++ ) {
				String productCode =(String)request.getParameter("productCode" + i);
				Integer quantity = Integer.parseInt((String)request.getParameter("quantity" + i));
				codeList.add(productCode);
				quantityList.add(quantity);
			}
			//リストを渡して商品の個数を変更（変更していないものも全て更新される）
			CartUpdateLogic cartUpdateLogic = new CartUpdateLogic();
			cartUpdateLogic.quantityUpdate(customerMail, codeList, quantityList);
			String message = "個数を変更しました";
			session.setAttribute("updateMessage", message);
			response.sendRedirect("./Cart?action=view_cart");
			}
		}

		/**-------------------------選択した商品をカートから削除する----------------------------**/
		if(action.equals("delete_item")) {
			Customer loginCustomer = (Customer)session.getAttribute("login_customer");
			RequestDispatcher rd = null;

			//ログインしてない場合
			if (loginCustomer==null){
				request.setAttribute("message", "ログインしてください");
				rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			//ログイン確認できた場合
			else {
				int numOfItems = Integer.parseInt((String)request.getParameter("numOfItems"));//カートに商品が何種類あるか
				String productCode = null;
				String deleteCheck = null;
				String customerMail = loginCustomer.getMail();
				List<String> delcodes = new ArrayList<String>(); //削除する商品を入れるためのリスト チェックボックス入れた商品

				for(int i =1; i <= numOfItems; i++ ) {
						productCode =(String)request.getParameter("productCode" + i);
						deleteCheck = (String)request.getParameter("check" +i);
						if(deleteCheck != null) {
							delcodes.add(productCode);
						}
				}
				if(delcodes.isEmpty()) {//どのチェックボックスもnullの場合
					rd = request.getRequestDispatcher("/Cart?action=view_cart");
					}else {
					DeleteItemLogic deleteItemLogic = new DeleteItemLogic();
					deleteItemLogic.delete(customerMail, delcodes);
					String message = "選択した商品を削除しました";
					session.setAttribute("updateMessage", message);
					rd = request.getRequestDispatcher("/Cart?action=view_cart");
					System.out.println(message);
					}
				rd.forward(request, response);
				}

		}
	}
}








