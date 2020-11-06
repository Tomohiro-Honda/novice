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
import model.logic.customer.ChangeInfoLogic;
import model.logic.customer.ChangeMailLogic;
import model.logic.customer.ChangePassLogic;

/**
 * Servlet implementation class AccountDetails
 */
@WebServlet("/AccountDetails")
public class AccountDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     *アカウントに関する処理をするコントローラー（情報閲覧、情報変更）
     */
    public AccountDetails() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//サーブレットクラスの動作を決定する 「action」の値をリクエストパラメーターから取得
		String action = request.getParameter("action");
//		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;

		//マイページへフォワード
		if(action.equals("mypage")) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp");
			dispatcher.forward(request, response);
		}
		//アカウント情報閲覧ページへフォワード
		if(action.equals("info")) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountInfo.jsp");
			dispatcher.forward(request, response);
		}
		//アカウント情報変更ページへフォワード
		if(action.equals("change_info")) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/changeInfo.jsp");
			dispatcher.forward(request, response);
		}
		//メールアドレス変更ページへフォワード
				if(action.equals("change_mail")) {
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/changeMail.jsp");
					dispatcher.forward(request, response);
				}
		//パスワード変更ページへフォワード
				if(action.equals("change_pass")) {
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/changePass.jsp");
					dispatcher.forward(request, response);
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		/**----------------------アカウント情報の変更----------------------**/
		if(action.equals("change_info")) {

			//変更したアカウント情報をリクエストパラメータから取得してCustomeインスタンスへ
			Customer ciCustomer = (Customer)session.getAttribute("login_customer");
			ciCustomer.setLastName(request.getParameter("lastName"));
			ciCustomer.setFirstName(request.getParameter("firstName"));
			ciCustomer.setPostal(request.getParameter("postal"));
			ciCustomer.setPref(request.getParameter("pref"));
			ciCustomer.setMuni(request.getParameter("muni"));
			ciCustomer.setStAd(request.getParameter("stAd"));
			ciCustomer.setTell(request.getParameter("tell"));

			//登録処理の呼び出し （DAO呼び出し）
			ChangeInfoLogic logic = new ChangeInfoLogic();
			logic.doChangeInfo(ciCustomer);

			//セッションスコープへを保持しているlogin_customerを更新
			session.setAttribute("login_customer", ciCustomer);

			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountInfo.jsp");
			dispatcher.forward(request, response);
		}

		/**-----------------メールアドレス変更---------------------**/
		if(action.equals("change_mail")) {
			//変更したアカウント情報をリクエストパラメータから取得してCustomeインスタンスへ
			Customer ciCustomer = (Customer)session.getAttribute("login_customer");
			String oldMail = ciCustomer.getMail();
			String newMail = request.getParameter("newMail");

			//登録処理の呼び出し （DAO呼び出し）
			ChangeMailLogic logic = new ChangeMailLogic();
			logic.doChangeMail(oldMail, newMail);

			//メールアドレスを変更してセッションのlogin_customerを更新
			ciCustomer.setMail(newMail);
			session.setAttribute("login_customer", ciCustomer);

			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountInfo.jsp");
			dispatcher.forward(request, response);
		}

		/**---------------パスワード変更---------------**/
		if(action.equals("change_pass")) {
			//変更したアカウント情報をリクエストパラメータから取得してCustomeインスタンスへ
			Customer ciCustomer = (Customer)session.getAttribute("login_customer");
			String oldPassA = ciCustomer.getPass();
			String oldPassB = request.getParameter("oldPass");
			String newPassA = request.getParameter("newPassA");
			String newPassB = request.getParameter("newPassB");
			String mail = ciCustomer.getMail();
			String message = null;

			if(newPassA.equals(newPassB) && oldPassA.equals(oldPassB)) {
				//登録処理の呼び出し （DAO呼び出し）
				ChangePassLogic logic = new ChangePassLogic();
				logic.doChangeMail(newPassA, mail);
				//パスワードを変更してセッションのlogin_customerを更新
				ciCustomer.setPass(newPassA);
				session.setAttribute("login_customer", ciCustomer);
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountInfo.jsp");
				dispatcher.forward(request, response);
			}else if(oldPassA.equals(oldPassB)==false) {
				//changePass.jspで失敗メッセージを出す
				message = "現在のパスワードが間違っています！";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/changePass.jsp");
				dispatcher.forward(request, response);
			}else if(newPassA.equals(newPassB)==false){
				//changePass.jspで失敗メッセージを出す
				message = "新しいパスワードが一致しません";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/changePass.jsp");
				dispatcher.forward(request, response);
			}
		}

		if(action.equals("account_delete") ) {

		}


	}

}
