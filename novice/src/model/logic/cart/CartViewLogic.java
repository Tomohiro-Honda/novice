package model.logic.cart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CartDAO;
import model.beans.CartItem;

public class CartViewLogic {

	CartDAO dao = new CartDAO();
	ResultSet rs = null;
	List<CartItem> cartItems = new ArrayList<>();

	public List<CartItem> viewCart(String customerMail){
		try {
		rs = dao.viewCartItem(customerMail);

		// 検索結果が存在する場合cartItemsにCartItemインスタンスをセットしていく
		while(rs.next()) {
			CartItem item = new CartItem();
			item.setProductCode(rs.getString("PRODUCT_C"));
			item.setProductName(rs.getString("PRODUCTNAME"));
			item.setPrice(rs.getInt("PRICE"));
			item.setQuantity(rs.getInt("QUANTITY"));
			item.setSum(item.getPrice() * item.getQuantity());
			item.setStock(rs.getInt("STOCK"));
			cartItems.add(item);
		}
	}catch (SQLException e) {
		e.printStackTrace();
	} finally {
	// 処理終了時に各接続を解除
	dao.close();
		}return cartItems;
	}
}
