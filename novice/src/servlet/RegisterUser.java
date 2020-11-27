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
import model.logic.customer.AddressCheckLogic;
import model.logic.customer.RegisterLogic;
import model.logic.login.LoginLogic;


/**
 * ユーザー登録に関するリクエストを処理する
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// フォワード先

		//サーブレットクラスの動作を決定する 「action」の値をリクエストパラメーターから取得
		String action = request.getParameter("action");

		//「登録の開始」をリクエストされたときの処理
		if(action == null) {
			//フォワード先を設定
			RequestDispatcher dispatcher = request.getRequestDispatcher( "/WEB-INF/jsp/registerForm.jsp");
			dispatcher.forward(request, response);
		}

		//登録確認画面から「登録実行」をリクエストされたときの処理
		else if(action.equals("done")) {

			//セッションスコープに保存された登録ユーザーを取得
			HttpSession session = request.getSession();
			Customer registerCustomer = (Customer)session.getAttribute("registerCustomer");
			if(registerCustomer!=null) {
			//登録処理の呼び出し
			RegisterLogic logic = new RegisterLogic();
			logic.register(registerCustomer);
			String mail = registerCustomer.getMail();
			String pass = registerCustomer.getPass();
			LoginLogic loginLogic = new LoginLogic();
			Customer loginCustomer = loginLogic.login(mail, pass);

			//登録したユーザーでログインし、不要となったセッションスコープ内のインスタンスを削除

			session.setAttribute("login_customer", loginCustomer);
			session.removeAttribute("registerCustomer");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerDone.jsp");
			dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("../index.jsp");
				dispatcher.forward(request, response);
				}
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String postal = request.getParameter("zip01");
		String pref = request.getParameter("pref01");
		String muni = request.getParameter("addr01");
		String stAd = request.getParameter("stAd");
		String tell = request.getParameter("tell");

		//メールアドレスが既に登録してあればメッセージを表示する
		AddressCheckLogic checklogic = new AddressCheckLogic();
		if(checklogic.adressCeck(mail) == 1) {
			String address_exist = "入力したメールアドレスは既に登録されています。";
			request.setAttribute("address_exist", address_exist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerForm.jsp");
			dispatcher.forward(request, response);
		} else{
			//登録するユーザーの情報を設定

		Customer registerCustomer = new Customer(lastName, firstName, mail, pass, postal, pref, muni, stAd, tell);

		//セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerCustomer", registerCustomer);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request, response);
		}
		}
}
