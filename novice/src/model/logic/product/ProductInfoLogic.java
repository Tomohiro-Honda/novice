package model.logic.product;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ProductViewDAO;
import model.beans.Product;

public class ProductInfoLogic {

	ProductViewDAO dao = null;
	ResultSet rs = null;
	Product selectedProduct = null;

	public Product viewProductInfo(String productCode) {
		try {
			dao = new ProductViewDAO();
			rs  = dao.selectProduct(productCode);

			while(rs.next()) {

				// 検索結果が存在する場合viewProductに値をセット（結果が1件しか返らないことを想定）
				selectedProduct = new Product();
				selectedProduct.setProductCode(rs.getString("PRODUCTCODE"));
				selectedProduct.setProductName(rs.getString("PRODUCTNAME"));
				selectedProduct.setPrice(rs.getInt("PRICE"));
				selectedProduct.setStock(rs.getInt("STOCK"));
			}
		}catch (SQLException e) {

				e.printStackTrace();

			} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
		return selectedProduct;


	}


}
