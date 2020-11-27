package model.logic.cart;

import dao.CartDAO;

public class AddCartLogic {
	public void addCart(int customerId, String productCode, String indivCode, String size, String type, int quantity) {
		CartDAO dao = null;
		try {
			dao = new CartDAO();
			dao.addCartItem(customerId, productCode, indivCode, size, type, quantity);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}

	public void addCartPlus(int customerId, String indivCode, int quantity) {
		CartDAO dao = null;
		try {
			dao = new CartDAO();
			dao.cartItemPlus(customerId, indivCode, quantity);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}

}
