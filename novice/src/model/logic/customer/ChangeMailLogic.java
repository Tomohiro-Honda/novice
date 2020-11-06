package model.logic.customer;

import dao.CustomerDAO;

public class ChangeMailLogic {

	public void doChangeMail(String oldMail, String newMail) {
		CustomerDAO dao = null;
		try {
			dao = new CustomerDAO();
			dao.changeMail(oldMail, newMail);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}

}
