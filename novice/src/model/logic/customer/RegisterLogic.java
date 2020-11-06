package model.logic.customer;

import dao.CustomerDAO;
import model.beans.Customer;

public class RegisterLogic {
	public void register(Customer customer) {
		CustomerDAO dao = null;

		try {
			dao = new CustomerDAO();
			dao.create(customer);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}

	}

}
