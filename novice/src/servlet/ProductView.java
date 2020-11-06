package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.Product;
import model.logic.product.ProductInfoLogic;

/**
 * Servlet implementation class Shopping
 */
@WebServlet("/ProductView")
public class ProductView extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 商品表示に関するコントローラー
     * TOP画面の表示、商品詳細画面の表示、検索画面の表示
     */
    public ProductView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		/**----------------------商品詳細ページの表示----------------------**/
		if(action.equals("product_info")) {

			RequestDispatcher rd ;
			String productCode = request.getParameter("productCode");
			ProductInfoLogic logic = new ProductInfoLogic();

			//viewProductInfoメソッドを使ってProductインスタンスを作成
			Product selectedProduct = logic.viewProductInfo(productCode);

			if(selectedProduct != null) {

				//商品情報をセット
				String lookItem = "Im looking items...";  //更新時に二重登録を防止するためのオブジェクト
				session.setAttribute("lookItem", lookItem); //カートに入れる時にこのセッションの有無を確認。カートに入れた時にremove
				request.setAttribute("selectedProduct", selectedProduct);
				rd = request.getRequestDispatcher("/WEB-INF/jsp/productInfo.jsp");
			}
			else {
					//商品情報が存在しない場合
					rd = request.getRequestDispatcher( "/WEB-INF/jsp/notFound.jsp");
				}
				rd.forward(request, response);
		}
	}
}
