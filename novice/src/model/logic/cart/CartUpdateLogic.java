package model.logic.cart;

import java.util.List;

import dao.CartDAO;

public class CartUpdateLogic {

	public void  quantityUpdate(String customerMail, List <String> codes,List<Integer> nums) {
		CartDAO dao = null;
		try {
			dao = new CartDAO();
			dao.cartUpdate(customerMail, codes, nums);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}


}
