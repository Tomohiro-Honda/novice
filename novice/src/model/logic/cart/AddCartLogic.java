package model.logic.cart;

import dao.CartDAO;

public class AddCartLogic {
	public void addCart(String customerMail, String productCode, int quantity) {
		CartDAO dao = null;
		try {
			dao = new CartDAO();
			dao.addCartItem(customerMail, productCode, quantity);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}

	public void addCartPlus(String customerMail, String productCode, int quantity) {
		CartDAO dao = null;
		try {
			dao = new CartDAO();
			dao.cartItemPlus(customerMail, productCode, quantity);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}

}
