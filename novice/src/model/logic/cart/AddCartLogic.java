package model.logic.cart;

import java.util.List;

import dao.CartDAO;
import model.beans.CartItem;

public class AddCartLogic {

	//ログイン時の買い物かごへ追加
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

	//未ログイン時の買い物かごへ追加
	public List<CartItem> addCart(List<CartItem> guestCartItems, String indivCode, int quantity) {
		CartItem item = new CartItem();
		item.setIndividualCode(indivCode);
		item.setQuantity(quantity);
		guestCartItems.add(item);
		return guestCartItems;
	}

	//ログイン時の買い物かごへ追加　同じ商品がある場合
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

	//未ログイン時の買い物かごへ追加　同じ商品がある場合
	public List<CartItem> guestAddCartPlus(List<CartItem> guestCartItems, String indivCode, int quantity){
		for (CartItem item:guestCartItems) {
			if(item.getIndividualCode().equals(indivCode)) {
				item.setQuantity(quantity);
			}
		}
		return guestCartItems;
	}

	//商品固有コードを生成するためのメソッド
	public String generateIndivCode(String productCode, String size, String type) {
		String indivCode = null;

		if(size!=null) { //Tシャツの場合
			type = type.toUpperCase();
			type = String.valueOf(type.charAt(0));
			indivCode = productCode +("-")+ size +("-")+ type;
		} else {//スマホケースの場合
			type = type.toUpperCase();
			type = String.valueOf(type.charAt(0));
			indivCode = productCode + ("-") + type;
		}
		return indivCode;
	}

	//カート商品ストックを設定するメソッド
	public int selectStockValue(String size, int sStock, int mStock, int lStock, int scStock) {

		int stock = 0;

		if(size!=null) { //Tシャツが選ばれている場合は値が上書きされる
			switch(size) {
			case "S":
				stock = sStock;
				break;
			case "M":
				stock = mStock;
				break;
			case "L":
				stock = lStock;
				break;
			}
		}else {
			stock = scStock; // スマホケースの値を入れる
		}
		return stock;
	}

}
