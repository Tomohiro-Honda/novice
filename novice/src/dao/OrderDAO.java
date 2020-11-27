package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import model.beans.CartItem;
import model.beans.OrderSheet;

public class OrderDAO {

		private Connection conn = null;
		private ResultSet  rs  = null;
		private PreparedStatement pStmt = null;

		/**--------------------在庫数チェック--------------------**/

		public ResultSet stockCheck(List<CartItem> cartItems) {

			//データベース接続
			try{
				conn = DBConnection.getConnection();

				StringBuilder sqlText = new StringBuilder();
				sqlText.append("SELECT * FROM Products JOIN Product_Variation ON "
						+ "Products.PRODUCTCODE = Product_Variation.P_CODE WHERE INDIVIDUAL_CODE IN(");
				for(int i = 1; i  <= cartItems.size( ); i++) {
					sqlText.append("?,");  //カートに入った商品の数だけ加える
				}
				int lastChar = sqlText.length();
				sqlText.deleteCharAt(lastChar - 1);
				sqlText.append(")");
				String sql = sqlText.toString();
				pStmt = conn.prepareStatement(sql);
				//sql文の?に値を代入
					for(int i = 0,n =1; i< cartItems.size(); i++, n++) {
						pStmt.setString(n, cartItems.get(i).getIndividualCode());
					}

					rs = pStmt.executeQuery();
			}catch (SQLException e) {
		// データベースとの接続に失敗した場合
				e.printStackTrace();
			}catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }return rs;
		}

			/**--------------------注文処理（注文情報登録、カート削除、在庫更新）--------------------**/
			//注文情報を書き込む。
		public Timestamp order(OrderSheet orderSheet) {
			Timestamp time = null;
			try {
					conn =DBConnection.getConnection();
					conn.setAutoCommit(false);
					int listSize = orderSheet.getOrderItems().size();

				//注文を登録（商品以外）
				String sql = "INSERT INTO Order_Sheet(CUS_ID, DEST_POSTAL, DEST_AD, TOTAL_PRICE, PAYMENT, TAX, SHIP_PRICE, COD_PRICE"
						+ ", OR_DATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? )";

				pStmt = conn.prepareStatement(sql);

				//INSERT文中の[？]に使用する値を設定しSQLを完成
				pStmt.setInt(1, orderSheet.getId());
				pStmt.setInt(2, Integer.parseInt(orderSheet.getDestinationPostal()));
				pStmt.setString(3, orderSheet.getDestinationAdress());
				pStmt.setInt(4, orderSheet.getTotalPrice());
				pStmt.setString(5, orderSheet.getPayment());
				pStmt.setInt(6, orderSheet.getTax());
				pStmt.setInt(7, orderSheet.getShipPrice());
				pStmt.setInt(8, orderSheet.getCodPrice());
				time = new Timestamp(System.currentTimeMillis());
				pStmt.setTimestamp(9, time);

				int resultS = pStmt.executeUpdate();

				//注文した商品を登録
				StringBuilder sqlA = new StringBuilder();
				sqlA.append("INSERT INTO Order_Items(OR_CUSID, OR_TIME, OR_INDIVCODE, OR_QUANTITY) VALUES");
				for(int i = 0; i < listSize ; i++ ) {
					sqlA.append("(?, ?, ?, ?)");
					if(i < listSize - 1 ) {
						sqlA.append(",");
						}
				}
				sql = sqlA.toString();
				pStmt = conn.prepareStatement(sql);
				for(int i = 0, n=1; i < listSize ; i++) {
					pStmt.setInt(n++, orderSheet.getId());
					pStmt.setTimestamp(n++, time);
					pStmt.setString(n++, orderSheet.getOrderItems().get(i).getIndividualCode());
					pStmt.setInt(n++, orderSheet.getOrderItems().get(i).getQuantity());
				}
				int resultI = pStmt.executeUpdate();

				//カートを空にする
				sql = "DELETE FROM Cart_Items WHERE CUSTOMER_ID = ? ";
				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, orderSheet.getId());
				int resultD = pStmt.executeUpdate();

				//購入した商品を個数分だけ在庫をマイナスする
				StringBuilder sqlB = new StringBuilder();
				sqlB.append("UPDATE Product_Variation SET STOCK = CASE INDIVIDUAL_CODE ");
				for(int i = 0; i < orderSheet.getOrderItems().size(); i++) {
					sqlB.append("WHEN ? THEN ? ");
				}
				sqlB.append("END ");
				sqlB.append("WHERE INDIVIDUAL_CODE IN (");
				for(int j = 0; j < listSize; j++) {
					sqlB.append("?");
					if(j < listSize - 1) {
						sqlB.append(",");
					}
					if(j == listSize - 1) {
						sqlB.append(")");
					}
				}
				sql = sqlB.toString();
				pStmt = conn.prepareStatement(sql);
				for(int i = 0, n = 1; i <  listSize; i++) {
					String individualCode = orderSheet.getOrderItems().get(i).getIndividualCode();
					int stock = orderSheet.getOrderItems().get(i).getStock();
					int quantity =  orderSheet.getOrderItems().get(i).getQuantity();
					pStmt.setString(n++, individualCode);
					pStmt.setInt(n++, stock - quantity);
				}
				for(int i = 0, n = listSize*2+1; i <  listSize ; i++) {
					pStmt.setString(n++, orderSheet.getOrderItems().get(i).getIndividualCode());
				}
				int resultP = pStmt.executeUpdate();

				conn.commit();

				if(resultS != 1|| resultI != listSize || resultD != listSize || resultP != listSize){
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
			return time;
		}

		/**--------------------注文確認メール用の注文IDを引き出す--------------------**/

		public ResultSet checkOrderID(int customerID, Timestamp time ) {

			try{
				conn = DBConnection.getConnection();
				String sql = "SELECT ID FROM Order_Sheet WHERE CUS_ID = ? AND OR_DATE = ?";
				pStmt = conn.prepareStatement(sql);

				//INSERT文中の[？]に使用する値を設定しSQLを完成
				pStmt.setInt(1, customerID);
				pStmt.setTimestamp(2, time);

				rs = pStmt.executeQuery();

			}catch (SQLException e) {
		// データベースとの接続に失敗した場合
				e.printStackTrace();
			}catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }return rs;
			}



		//データベースとの接続解除
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
