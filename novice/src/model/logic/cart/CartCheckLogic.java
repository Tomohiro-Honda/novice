package model.logic.cart;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CartDAO;

public class CartCheckLogic {
	public int addCart(String customerMail, String productCode) {
		CartDAO dao = null;
		ResultSet rs = null;
		int quantityInCart = 0;

		try {
			dao = new CartDAO();
			rs = dao.cartCheck(customerMail, productCode);
				if(rs.next() == true) {
					quantityInCart = rs.getInt("QUANTITY");
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
			// 処理終了時に各接続を解除
			dao.close();
		}return quantityInCart;
	}
}
