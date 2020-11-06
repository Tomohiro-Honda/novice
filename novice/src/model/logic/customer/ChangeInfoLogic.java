package model.logic.customer;

import dao.CustomerDAO;
import model.beans.Customer;

public class ChangeInfoLogic {
	public void doChangeInfo(Customer ciCustomer) {
		CustomerDAO dao = null;
		try {
			dao = new CustomerDAO();
			dao.changeInfo(ciCustomer);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}
}
