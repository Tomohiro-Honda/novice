package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.Customer;
import model.logic.login.LoginLogic;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	/**
	 * ログインに関する処理を行うコントローラー
	 */


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//サーブレットクラスの動作を決定する 「action」の値をリクエストパラメーターから取得
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;

		 //Topページなどからログインが押された場合
		if(action.equals("login")) {
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp");
		dispatcher.forward(request, response);
		}
		//ログアウトが押された場合
		if(action .equals("logout")) {
			session.removeAttribute("login_db");
			session.removeAttribute("login_customer");
			response.sendRedirect("/novice/index.jsp");
		}
	}


	//ログインフォームからデータを受け取った場合
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメーター取得
		request.setCharacterEncoding("UTF-8");
//		String btn = request.getParameter("submit");

		//セッションを用意して画面移動の準備
		HttpSession session = request.getSession();
		RequestDispatcher rd ;

		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");

		LoginLogic loginLogic = new LoginLogic();

		//loginメソッドを使ってCustomerインスタンスを作成
		Customer loginCustomer = loginLogic.login(mail, pass);

		//ログイン判定
		if(loginCustomer != null) {

			//ユーザー情報をセット
			session.setAttribute("login_customer", loginCustomer);
			//ログイン状態
			session.setAttribute("login_db", "login");

			rd = request.getRequestDispatcher("/index.jsp");
		}
		else {
				//モデルの情報が存在しない（IDに紐づくユーザ情報がない）場合
				rd = request.getRequestDispatcher( "/WEB-INF/jsp/loginNG.jsp");
			}
			rd.forward(request, response);
	}




}


