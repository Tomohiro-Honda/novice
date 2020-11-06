package model.logic.cart;

import java.util.List;

import dao.CartDAO;

public class DeleteItemLogic {

	public void delete(String customerMail, List<String>delcodes) {
		CartDAO dao = null;
		try {
			dao = new CartDAO();
			dao.deleteItems(customerMail, delcodes);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}

}
