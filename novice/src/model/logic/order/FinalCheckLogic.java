package model.logic.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import model.beans.CartItem;
import model.beans.Product;

public class FinalCheckLogic {
	OrderDAO dao =null;
	ResultSet rs = null;
	int quantityInCart = 0;
	List<Product> stockCheckList = new ArrayList<Product>();
	List<CartItem> alertList = null;
	int i = 0;//rsのwhileカウント用

	public List<Product> getStockList(List<CartItem> orderItems) {
		try {
			dao = new OrderDAO();
			rs = dao.stockCheck(orderItems);
//			stockCheckList = new ArrayList<Product>();

			// 検索結果が存在する場合cartItemsにCartItemインスタンスをセットしていく
			while(rs.next()) {
				Product checkProduct = new Product();
				checkProduct.setProductCode(orderItems.get(i).getProductCode());
				checkProduct. setIndividualCode(rs.getString("INDIVIDUAL_CODE"));
				checkProduct.setStock(rs.getInt("STOCK"));
				checkProduct.setProductName(rs.getString("PRODUCTNAME"));
				checkProduct.setSize(rs.getString("SIZE"));
				checkProduct.setText(rs.getString("TYPE"));
				stockCheckList.add(checkProduct);
				i++;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
		// 処理終了時に各接続を解除
		dao.close();
			}return stockCheckList;
		}

	public List<CartItem> finalCheck(List<Product> stockList, List<CartItem> orderItems){

		alertList = new ArrayList<CartItem>();

		for(int j = 0; j < orderItems.size(); j++) {
			if(orderItems.get(j).getQuantity() > stockCheckList.get(j).getStock()){
				alertList.add(orderItems.get(j));
			}
		}return alertList;
	}
}
