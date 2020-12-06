package model.logic.cart;

import java.util.ArrayList;
import java.util.List;

import dao.CartDAO;
import model.beans.CartItem;

public class CartUpdateLogic {

	//ログイン時のカート内商品の個数変更
	public void  quantityUpdate(int customerId, List <String> codes,List<Integer> nums) {
		CartDAO dao = null;
		try {
			dao = new CartDAO();
			dao.cartUpdate(customerId, codes, nums);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}

	//ゲストカートの商品個数変更
	public List<CartItem>  quantityUpdate(List <String> codes,List<Integer> nums) {

		List<CartItem> guestCartItems = new ArrayList<CartItem>();

		for(int i = 0; i < codes.size(); i++ ) {
			CartItem item = new CartItem();
			item.setIndividualCode(codes.get(i));
			item.setQuantity(nums.get(i));
			guestCartItems.add(item);
			}
		return guestCartItems;
		}

}
