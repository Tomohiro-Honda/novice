package model.logic.cart;

import java.util.ArrayList;
import java.util.List;

import dao.CartDAO;
import model.beans.CartItem;

public class DeleteItemLogic {

	//ログイン時のアイテム削除
	public void delete(int customerId, List<String>delcodes) {
		CartDAO dao = null;
		try {
			dao = new CartDAO();
			dao.deleteItems(customerId, delcodes);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
	}

	//ゲストカートのアイテム削除
	public List<CartItem> delete(List<CartItem>guestCartItems, List<String> delcodes) {

		List<CartItem> updateCartItems = new ArrayList<CartItem>();//リターンするリスト

		//削除リストに入っていないアイテムを新たなリストに入れる
		for(int i=0; i < guestCartItems.size(); i++) {
			CartItem item = guestCartItems.get(i);
			String indivCode = guestCartItems.get(i).getIndividualCode();
			int n = 0;//判定用のカウンター
			for(String delcode: delcodes) {
 				if(indivCode.equals(delcode)) {
 					n++;//削除リストに入っている個別コードに一致していたらn++
				}
 				}
			if(n==0) {//n++されていなければ新しいリストに入れる
				updateCartItems.add(item);
			}
		}
		return updateCartItems;
	}

}
