package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.Customer;
import model.beans.OrderSheet;
import model.logic.order.HistoryViewLogic;

/**
 * Servlet implementation class History
 */
@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 注文履歴を表示するコントローラー
     */
    public History() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//サーブレットクラスの動作を決定する 「action」の値をリクエストパラメーターから取得
				String action = request.getParameter("action");
				HttpSession session = request.getSession();

				/**----------------------注文履歴のリストを閲覧----------------------**/
				if(action.equals("view_history")) {

					Customer loginCustomer = (Customer)session.getAttribute("login_customer");
					List<OrderSheet> orderList = null;
					RequestDispatcher rd = null;

					//ログインしていない場合はログインを促す（ログイン前のカート機能は今後実装）
					if(loginCustomer == null) {
						rd = request.getRequestDispatcher("Login?action=login");
					}else {
						//ログインしている場合の処理
						HistoryViewLogic  historyViewLogic = new HistoryViewLogic();
						//注文情報を注文リストで取得
						int customerId = loginCustomer.getId();
						orderList = historyViewLogic.viewHistory(customerId);
						request.setAttribute("orderList", orderList);
						rd = request.getRequestDispatcher("/WEB-INF/jsp/orderView.jsp");
					}
					rd.forward(request, response);
					}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



	}

}
