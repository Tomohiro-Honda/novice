package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductViewDAO {
		private Connection conn = null;
		private PreparedStatement pStmt = null;
		private ResultSet rs = null;

		/**--------------------productNameで指定された商品の情報を入手する--------------------**/
		public ResultSet selectProduct(String productCode)  {
			//データベース接続
			try{
				conn = DBConnection.getConnection();
				String sql = "SELECT * FROM Products  WHERE PRODUCTCODE = ?";
				pStmt = conn.prepareStatement(sql);

				//INSERT文中の[？]に使用する値を設定しSQLを完成
				pStmt.setString(1, productCode);
				rs = pStmt.executeQuery();

			}catch (SQLException se) {
				// データベースとの接続解除に失敗した場合
				se.printStackTrace();
				return null;
			}catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }return rs;
		}

		/**--------------------productNameで指定された商品の在庫を入手する--------------------**/
		public ResultSet selectStockList(String productCode)  {
			//データベース接続
			try{
				conn = DBConnection.getConnection();
				String sql = "SELECT STOCK FROM Product_Variation  WHERE P_CODE = ?";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, productCode);
				rs = pStmt.executeQuery();

				//INSERT文中の[？]に使用する値を設定しSQLを完成
				pStmt.setString(1, productCode);
				rs = pStmt.executeQuery();

			}catch (SQLException se) {
				// データベースとの接続解除に失敗した場合
				se.printStackTrace();
				return null;
			}catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }return rs;
		}

		/**--------------------フリーワード検索された商品情報を入手する--------------------**/
		public ResultSet searchProduct(List<String> wordList)  {
			//データベース接続
			try{
				conn = DBConnection.getConnection();
				StringBuilder sqlsb = new StringBuilder();
				String sql = null;
				if(wordList.size()==1) {
					sql = "SELECT * FROM Products WHERE CONCAT(PRODUCTNAME, TEXT) LIKE ?";

					pStmt = conn.prepareStatement(sql);
					pStmt.setString(1, "%" + wordList.get(0)+ "%");
				}else {
					sqlsb.append("SELECT * FROM Products WHERE CONCAT(PRODUCTNAME, TEXT) LIKE ? ");

					for(int i = 1; i < wordList.size(); i++) {
						sqlsb.append("AND CONCAT(PRODUCTNAME, TEXT) LIKE ?");
					}
					sql = sqlsb.toString();
					pStmt = conn.prepareStatement(sql);
					for(int i = 0, n=1; i < wordList.size(); i++, n++) {
						pStmt.setString(n, "%" + wordList.get(i) + "%");
					}
				}
				rs = pStmt.executeQuery();
			}catch (SQLException se) {
				// データベースとの接続解除に失敗した場合
				se.printStackTrace();
				return null;
			}catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }return rs;
		}

		/**--------------------カテゴリー検索で得られた商品情報を入手する--------------------**/
		public ResultSet categoryProduct(String category)  {
			//データベース接続
			try{
				conn = DBConnection.getConnection();
				String sql = "SELECT * FROM Products WHERE PRODUCTCODE LIKE ?";
					pStmt = conn.prepareStatement(sql);
					pStmt.setString(1, category + "%");
					rs = pStmt.executeQuery();
				}catch (SQLException se) {
					// データベースとの接続解除に失敗した場合
					se.printStackTrace();
					return null;
				}catch (URISyntaxException e) {
	                e.printStackTrace();
	                return null;
	            }return rs;
			}


public void close() {
	try {
		// データベースとの接続を解除する
		if(conn != null) {
			conn.close();
		}
		if(pStmt != null) {
			pStmt.close();
		}
		if(rs != null) {
			rs.close();
		}
	} catch (SQLException se) {
		// データベースとの接続解除に失敗した場合
		se.printStackTrace();
	}
}


}
