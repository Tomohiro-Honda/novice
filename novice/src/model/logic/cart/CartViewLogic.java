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

	//ログイン時のカート閲覧
	public List<CartItem> viewCart(int customerId){
		try {
		rs = dao.viewCartItem(customerId);

		// 検索結果が存在する場合cartItemsにCartItemインスタンスをセットしていく
		while(rs.next()) {
			CartItem item = new CartItem();
			item.setProductCode(rs.getString("PRODUCT_C"));
			item.setProductName(rs.getString("PRODUCTNAME"));
			item.setIndividualCode(rs.getString("PRODUCT_INDV"));
			item.setSize(rs.getString("SIZE"));
			item.setType(rs.getString("TYPE"));
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

	//ゲストカート閲覧
	public List<CartItem> viewCart(List<CartItem> guestCartItems){
		try {
		List<String> ivcList = new ArrayList<String>();
		for(CartItem item: guestCartItems) {
			String ivc = item.getIndividualCode();
			ivcList.add(ivc);
		}

		rs = dao.viewCartItem(ivcList);

		while(rs.next()) {
			CartItem item = new CartItem();
			item.setProductCode(rs.getString("PRODUCTCODE"));
			item.setProductName(rs.getString("PRODUCTNAME"));
			item.setIndividualCode(rs.getString("INDIVIDUAL_CODE"));
			item.setSize(rs.getString("SIZE"));
			item.setType(rs.getString("TYPE"));
			item.setPrice(rs.getInt("PRICE"));
			item.setStock(rs.getInt("STOCK"));
			for(CartItem gcItems: guestCartItems) {
				String indvCode = gcItems.getIndividualCode();
				String itemIndvCode = item.getIndividualCode();
				if(indvCode.equals(itemIndvCode)) {
					item.setQuantity(gcItems.getQuantity());
				}
				item.setSum(item.getPrice() * item.getQuantity());
			}
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
