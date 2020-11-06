package model.logic.customer;

import dao.CustomerDAO;

public class ChangePassLogic {
	public void doChangeMail(String newPassA, String mail) {
		CustomerDAO dao = null;
		try {
			dao = new CustomerDAO();
			dao.changePass(newPassA, mail);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}

}
