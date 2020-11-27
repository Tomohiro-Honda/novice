package model.logic.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductViewDAO;
import model.beans.Product;

public class ProductSearchLogic {
	ProductViewDAO dao = null;
	ResultSet rs = null;
	List<Product> productList = null;
	Product pr = null;

	//キーワード検索のロジック
	public List<Product> search(List<String> wordList){
		try {
			productList = new ArrayList<Product>();
			dao = new ProductViewDAO();
			rs = dao.searchProduct(wordList);
			while(rs.next()) {
				pr = new Product();
				pr.setProductCode(rs.getString("PRODUCTCODE"));
				pr.setProductName(rs.getString("PRODUCTNAME"));
				pr.setPrice(rs.getInt("PRICE"));
				productList.add(pr);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		// 処理終了時に各接続を解除
		dao.close();
	}
	return productList;
	}

	//カテゴリー検索のロジック
		public List<Product> cateSearch(String category){
			try {
				productList = new ArrayList<Product>();
				dao = new ProductViewDAO();
				rs = dao.categoryProduct(category);
				while(rs.next()) {
					pr = new Product();
					pr.setProductCode(rs.getString("PRODUCTCODE"));
					pr.setProductName(rs.getString("PRODUCTNAME"));
					pr.setPrice(rs.getInt("PRICE"));
					productList.add(pr);
				}
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
		return productList;
		}

}
