package model.logic.customer;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CustomerDAO;

public class AddressCheckLogic {
	public int adressCeck(String mail) {
		CustomerDAO dao = null;
		ResultSet rs = null;
		int checkResult = 0;

		try {
			dao =new CustomerDAO();
			rs = dao.check(mail);
			if(rs.next()) {
				checkResult ++; //メールアドレスがDBにある
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 処理終了時に各接続を解除
			dao.close();
		}return checkResult;

	}

}
