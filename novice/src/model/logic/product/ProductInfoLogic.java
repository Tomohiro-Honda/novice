package model.logic.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.ProductViewDAO;
import model.beans.Product;

public class ProductInfoLogic {

	ProductViewDAO dao = null;
	ResultSet rs = null;
	Product selectedProduct = null;
	List<Integer> stockList = null;
	Map<Product, List<Integer>> productInfoMap = null;

	public Product getProductInfo(String productCode) {
		try {
			dao = new ProductViewDAO();
			rs = dao.selectProduct(productCode); //rsのリストを受け取る。
			while(rs.next()) {
				// 検索結果が存在する場合viewProductに値をセット（結果が1件しか返らないことを想定）
				selectedProduct = new Product();
				selectedProduct.setProductCode(rs.getString("PRODUCTCODE"));
				selectedProduct.setProductName(rs.getString("PRODUCTNAME"));
				selectedProduct.setPrice(rs.getInt("PRICE"));
				selectedProduct.setText(rs.getString("TEXT"));
			}
		}catch (SQLException e) {
				e.printStackTrace();
			} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
		return selectedProduct;
	}

	public List<Integer> getStockList(String productCode) {
		try {
			dao = new ProductViewDAO();
			rs = dao.selectStockList(productCode); //rsのリストを受け取る。
			stockList = new ArrayList<Integer>();
			while(rs.next()) {
				// 検索結果が存在する場合viewProductに値をセット（結果が1件しか返らないことを想定）
				int num = rs.getInt("STOCK");
				stockList.add(num);
			}
		}catch (SQLException e) {
				e.printStackTrace();
			} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
		return stockList;
	}



	//javascripの在庫の配列に入れるコードを作るためのメソッド
	public String stockPreview(int stock) {
		StringBuilder stockPrev = new StringBuilder();
		for(int i = 0; i <= stock ; i++) {
			stockPrev.append("'"+i+ "'");
			if(i != stock) {
			stockPrev.append(", ");
			}
		}
		String stockPreview = stockPrev.toString();
		return stockPreview;
	}
}
