package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO {
	//データベース接続に使用する情報
			private final String JDBC_URL = "jdbc:mysql://localhost:3306/novice_db";
			private final String USER_NAME = "test";
			private final String PASSWORD = "test";

			private Connection conn = null;
			private PreparedStatement pStmt = null;
			private ResultSet rs = null;

			/**カートに入れるようとしている商品と同じ商品がカートにあるかチェックする**/
			public ResultSet cartCheck(String customerMail, String productCode) {
				try {
					conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
					String sql = "SELECT * FROM Cart_Items WHERE CUSTOMER_M = ? AND PRODUCT_C = ?";
					pStmt = conn.prepareStatement(sql);

					//INSERT文中の[？]に使用する値を設定しSQLを完成
					pStmt.setString(1, customerMail);
					pStmt.setString(2, productCode);
					rs = pStmt.executeQuery();
				}catch (SQLException se) {
					// データベースとの接続に失敗した場合
					se.printStackTrace();
				}return rs;
			}

			/**--------------------------新しく商品をカートに入れる-----------------------**/
			public boolean addCartItem(String customerMail, String productCode, int quantity) {
				try {
					conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
					//idは自動連番なので指定しなくてよい
					String sql = "INSERT INTO Cart_Items(CUSTOMER_M, PRODUCT_C, QUANTITY) VALUES(?, ?, ?)";
					pStmt = conn.prepareStatement(sql);

					//INSERT文中の[？]に使用する値を設定しSQLを完成
					pStmt.setString(1, customerMail);
					pStmt.setString(2, productCode);
					pStmt.setInt(3, quantity);

					//INSERT文を実行(resultには追加された行数が代入される。)
					int result = pStmt.executeUpdate();
					if(result != 1) {
						return false;
					}
				} catch (SQLException e) {
					// データベースとの接続に失敗した場合
					e.printStackTrace();
					return false;
				}
				return true;
			}

			/**-----------------------------カートにある商品と同じ商品を追加する------------------------------------**/
			public boolean cartItemPlus(String customerMail, String productCode, int quantity) {
				try {
					conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
					String sql = "UPDATE Cart_Items SET QUANTITY = ? WHERE CUSTOMER_M = ? AND PRODUCT_C = ?";
					pStmt = conn.prepareStatement(sql);

					//INSERT文中の[？]に使用する値を設定しSQLを完成
					pStmt.setInt(1, quantity);
					pStmt.setString(2, customerMail);
					pStmt.setString(3, productCode);

					//UPDATE文を実行(resultには変更された行数が代入される。)
					int result = pStmt.executeUpdate();
					if(result != 1) {
						return false;
					}
				}catch (SQLException e) {
					// データベースとの接続に失敗した場合
					e.printStackTrace();
					return false;
				}
				return true;
				}

			/**-----------------------------カートに入っている商品を見る-----------------------------------**/
			public ResultSet viewCartItem(String customerMail) {
				try {
					conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
					String sql = "SELECT * FROM Cart_Items JOIN Products ON "
							+ "Cart_Items.PRODUCT_C = Products.PRODUCTCODE WHERE CUSTOMER_M = ?";
					pStmt = conn.prepareStatement(sql);

					//INSERT文中の[？]に使用する値を設定しSQLを完成
					pStmt.setString(1, customerMail);
					rs = pStmt.executeQuery();
				}catch (SQLException e) {
					// データベースとの接続に失敗した場合
					e.printStackTrace();
				}return rs;
			}


			/**---------------------------カートに入っている商品の数量を変更する-------------------------------**/
			public boolean cartUpdate(String customerMail, List<String> codes , List<Integer>nums) {
				try {
					conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);

					if(codes.size() == 1) {//カートの中が1種類の場合
						String sql = "UPDATE Cart_Items SET QUANTITY = ?  WHERE CUSTOMER_M = ? AND PRODUCT_C= ?";
						pStmt = conn.prepareStatement(sql);
						pStmt.setInt(1 , nums.get(0));
						pStmt.setString(2, codes.get(0));
						pStmt.setString(3, customerMail);
					}else {//カートの中に複数の商品がある場合
					StringBuilder sqlText = new StringBuilder("UPDATE Cart_Items SET QUANTITY = case PRODUCT_C ");
					for(int i = 1; i  <= codes.size( ); i++) {
					sqlText.append("WHEN ? THEN ? "); //カートに入った商品の数だけ加える
					}
					sqlText.append("END WHERE CUSTOMER_M = ?");
					String sql = sqlText.toString();
					pStmt = conn.prepareStatement(sql);

					//UPDATE文中の[？]に使用する値を設定しSQLを完成
					int last = 0;
					for(int i = 0, n = 1; i < codes.size() ; i++ ) {
					pStmt.setString(n++, codes.get(i));
					pStmt.setInt(n++, nums.get(i));
					last = n;
					}
					pStmt.setString(last, customerMail);
					}
					int result = pStmt.executeUpdate();
					if(result != codes.size()) {
						return false;
					}
				} catch (SQLException e) {
					// データベースとの接続に失敗した場合
					e.printStackTrace();
					return false;
				}return true;
			}

			/**---------------------------カートに入っている商品を選択して削除-------------------------------**/
			public boolean deleteItems(String customerMail, List<String> delcodes) {
				try {
					conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);

					StringBuilder sqlText = new StringBuilder("DELETE FROM Cart_Items WHERE CUSTOMER_M = ? AND PRODUCT_C IN(" );
					for(int i = 1; i  <= delcodes.size( ); i++) {
					sqlText.append("?,");  //カートに入った商品の数だけ加える
					}
					int lastChar = sqlText.length();
					sqlText.deleteCharAt(lastChar - 1);
					sqlText.append(")");
					String sql = sqlText.toString();
					System.out.println(sql);
					pStmt = conn.prepareStatement(sql);

					//UPDATE文中の[？]に使用する値を設定しSQLを完成
					pStmt.setString(1, customerMail);
					for(int i = 0, n = 2; i < delcodes.size() ; i++) {
					pStmt.setString(n++, delcodes.get(i));
					}
					int result = pStmt.executeUpdate();
					if(result != delcodes.size()) {
						return false;
					}
				}catch (SQLException e) {
					// データベースとの接続に失敗した場合
					e.printStackTrace();
					return false;
				}return true;
			}



			/**データベースとの接続解除**/
			public void close() {
				try {
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
