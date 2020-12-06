package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO {

			private Connection conn = null;
			private PreparedStatement pStmt = null;
			private ResultSet rs = null;

			/**カートに入れるようとしている商品と同じ商品がカートにあるかチェックする**/
			public ResultSet cartCheck(int customerId, String indivCode) {
				try {
					conn = DBConnection.getConnection();
					String sql = "SELECT QUANTITY FROM Cart_Items WHERE CUSTOMER_ID = ? AND PRODUCT_INDV= ?";
					pStmt = conn.prepareStatement(sql);

					//INSERT文中の[？]に使用する値を設定しSQLを完成
					pStmt.setInt(1, customerId);
					pStmt.setString(2, indivCode);
					rs = pStmt.executeQuery();
				}catch (SQLException se) {
					// データベースとの接続に失敗した場合
					se.printStackTrace();
				}catch (URISyntaxException e) {
	                e.printStackTrace();
	                return null;
	            } return rs;
			}

			/**--------------------------新しく商品をカートに入れる-----------------------**/
			public boolean addCartItem(int customerId, String productCode, String indivCode,String size, String type, int quantity) {
				try {
					conn = DBConnection.getConnection();
					//idは自動連番なので指定しなくてよい
					String sql = "INSERT INTO Cart_Items(CUSTOMER_ID, PRODUCT_C, PRODUCT_INDV, "
							+ "P_SIZE, P_TYPE, QUANTITY) VALUES(?, ?, ?, ?, ?, ?)";
					pStmt = conn.prepareStatement(sql);

					//INSERT文中の[？]に使用する値を設定しSQLを完成
					pStmt.setInt(1, customerId);
					pStmt.setString(2, productCode);
					pStmt.setString(3, indivCode);
					pStmt.setString(4, size);
					pStmt.setString(5, type);
					pStmt.setInt(6, quantity);

					//INSERT文を実行(resultには追加された行数が代入される。)
					int result = pStmt.executeUpdate();
					if(result != 1) {
						return false;
					}
				} catch (SQLException e) {
					// データベースとの接続に失敗した場合
					e.printStackTrace();
					return false;
				} catch (URISyntaxException e) {
	                e.printStackTrace();
	                return false;
	            }return true;
			}

			/**-----------------------------カートにある商品と同じ商品を追加する------------------------------------**/
			public boolean cartItemPlus(int customerId, String indivCode, int quantity) {
				try {
					conn = DBConnection.getConnection();
					String sql = "UPDATE Cart_Items SET QUANTITY = ? WHERE CUSTOMER_ID = ? "
							+ "AND PRODUCT_INDV = ?";
					pStmt = conn.prepareStatement(sql);

					//INSERT文中の[？]に使用する値を設定しSQLを完成
					pStmt.setInt(1, quantity);
					pStmt.setInt(2, customerId);
					pStmt.setString(3, indivCode);

					//UPDATE文を実行(resultには変更された行数が代入される。)
					int result = pStmt.executeUpdate();
					if(result != 1) {
						return false;
					}
				}catch (SQLException e) {
					// データベースとの接続に失敗した場合
					e.printStackTrace();
					return false;
				}catch (URISyntaxException e) {
	                e.printStackTrace();
	                return false;
	            }
				return true;
				}

			/**-----------------------------カートに入っている商品の情報を引き出す　（ログイン時）-----------------------------------**/
			public ResultSet viewCartItem(int customerId) {
				try {
					conn = DBConnection.getConnection();

					String sql = "SELECT * FROM Cart_Items JOIN Product_Variation ON "
							+ "Cart_Items.PRODUCT_INDV = Product_Variation.INDIVIDUAL_CODE "
							+ "JOIN Products ON Product_Variation.P_CODE = Products.PRODUCTCODE  WHERE CUSTOMER_ID = ?";
					pStmt = conn.prepareStatement(sql);

					//INSERT文中の[？]に使用する値を設定しSQLを完成
					pStmt.setInt(1, customerId);
					rs = pStmt.executeQuery();
				}catch (SQLException e) {
					// データベースとの接続に失敗した場合
					e.printStackTrace();
					return null;
				}catch (URISyntaxException e) {
	                e.printStackTrace();
	                return null;
	            }
				return rs;
			}

			/**-----------------------------ゲストカートの情報を引き出す-----------------------------------**/
			public ResultSet viewCartItem(List<String> ivcList) {
				try {
					conn = DBConnection.getConnection();
					StringBuilder sqlsb = new StringBuilder();
					sqlsb.append("SELECT * FROM Products JOIN Product_Variation  "
							+ "ON Products.PRODUCTCODE = Product_Variation.P_CODE WHERE INDIVIDUAL_CODE IN(");

						for(int i = 0; i < ivcList.size(); i++) {
							sqlsb.append("?");
							if(i != ivcList.size() - 1) {
								sqlsb.append(", ");
							}else {
								sqlsb.append(")");
							}
						String sql = sqlsb.toString();
						pStmt = conn.prepareStatement(sql);
						}
						for(int i =0, n = 1; i <  ivcList.size(); i++) {
							pStmt.setString(n++, ivcList.get(i));
						}
						rs = pStmt.executeQuery();

				}catch (SQLException e) {
					// データベースとの接続に失敗した場合
					e.printStackTrace();
					return null;
				}catch (URISyntaxException e) {
	                e.printStackTrace();
	                return null;
	            }
				return rs;
			}


			/**---------------------------カートに入っている商品の数量を変更する-------------------------------**/
			public boolean cartUpdate(int customerId, List<String> codes , List<Integer>nums) {
				try {
					conn = DBConnection.getConnection();

					if(codes.size() == 1) {//カートの中が1種類の場合
						String sql = "UPDATE Cart_Items SET QUANTITY = ?  WHERE CUSTOMER_ID = ? AND PRODUCT_INDV = ?";
						pStmt = conn.prepareStatement(sql);
						pStmt.setInt(1 , nums.get(0));
						pStmt.setInt(2, customerId);
						pStmt.setString(3, codes.get(0));
					}else {//カートの中に複数の商品がある場合
					StringBuilder sqlText = new StringBuilder("UPDATE Cart_Items SET QUANTITY = case PRODUCT_INDV ");
					for(int i = 1; i  <= codes.size( ); i++) {
					sqlText.append("WHEN ? THEN ? "); //カートに入った商品の数だけ加える
					}
					sqlText.append("END WHERE CUSTOMER_ID = ?");
					String sql = sqlText.toString();
					pStmt = conn.prepareStatement(sql);

					//UPDATE文中の[？]に使用する値を設定しSQLを完成
					int last = 0;
					for(int i = 0, n = 1; i < codes.size() ; i++ ) {
					pStmt.setString(n++, codes.get(i));
					pStmt.setInt(n++, nums.get(i));
					last = n;
					}
					pStmt.setInt(last, customerId);
					}
					int result = pStmt.executeUpdate();
					if(result != codes.size()) {
						return false;
					}
				} catch (SQLException e) {
					// データベースとの接続に失敗した場合
					e.printStackTrace();
					return false;
				}catch (URISyntaxException e) {
	                e.printStackTrace();
	                return false;
	            }
				return true;
			}

			/**---------------------------カートに入っている商品を選択して削除-------------------------------**/
			public boolean deleteItems(int customerId, List<String> delcodes) {
				try {
					conn = DBConnection.getConnection();

					StringBuilder sqlText = new StringBuilder("DELETE FROM Cart_Items WHERE CUSTOMER_ID = ? AND PRODUCT_INDV IN(" );
					for(int i = 1; i  <= delcodes.size( ); i++) {
					sqlText.append("?,");  //カートに入った商品の数だけ加える
					}
					int lastChar = sqlText.length();
					sqlText.deleteCharAt(lastChar - 1);
					sqlText.append(")");
					String sql = sqlText.toString();
					pStmt = conn.prepareStatement(sql);

					//UPDATE文中の[？]に使用する値を設定しSQLを完成
					pStmt.setInt(1, customerId);
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
				}catch (URISyntaxException e) {
	                e.printStackTrace();
	                return false;
	            } return true;
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
