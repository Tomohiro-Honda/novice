package model.logic.cart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.CartDAO;
import model.beans.CartItem;

public class CartCheckLogic {
	//ログインユーザーのカートに同じ商品があるかチェック
	public int addCart(int customerId, String indivCode ) {
		CartDAO dao = null;
		ResultSet rs = null;
		int quantityInCart = 0;

		try {
			dao = new CartDAO();
			rs = dao.cartCheck(customerId, indivCode);
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

	//ゲストカートに同じ商品があるかチェック
	public int addCart(List<CartItem> guestCartItems, String indivCode ) {
		int quantityInCart = 0;
		for(CartItem item: guestCartItems) {
			if(item.getIndividualCode().equals(indivCode)) {
				quantityInCart = item.getQuantity();
			}
		}
		return quantityInCart;
	}
}
