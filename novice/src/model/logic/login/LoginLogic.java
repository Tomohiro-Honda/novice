package model.logic.login;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.LoginDAO;
import model.beans.Customer;

public class LoginLogic {

	LoginDAO dao = null;
	ResultSet rs = null;
	Customer loginCustomer = null;

	public Customer login(String mail, String pass)  {
		try {
			dao = new LoginDAO();
			rs  = dao.selectCustomer(mail, pass);

			while(rs.next()) {

				// 検索結果が存在する場合loginCustomerに値をセット（結果が1件しか返らないことを想定）
				loginCustomer = new Customer();

				loginCustomer.setId(rs.getInt("ID"));
				loginCustomer.setLastName(rs.getString("LASTNAME"));
				loginCustomer.setFirstName(rs.getString("FIRSTNAME"));
				loginCustomer.setMail(rs.getString("MAIL"));
				loginCustomer.setPass(rs.getString("PASS"));
				loginCustomer.setPostal(rs.getString("POSTAL"));
				loginCustomer.setPref(rs.getString("PREFECTURE"));
				loginCustomer.setMuni(rs.getString("MUNICIPALITY"));
				loginCustomer.setStAd(rs.getString("STREETADRESS"));
				loginCustomer.setTell(rs.getString("TELL"));
			}
		}catch (SQLException e) {

				e.printStackTrace();

			} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
		return loginCustomer;
	}

}
