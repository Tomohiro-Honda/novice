package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryDAO {

	private Connection conn = null;
	private PreparedStatement pStmt = null;
	private ResultSet rs = null;

	/**-----------------------------注文情報を引き出す(商品以外）-----------------------------------**/
	public ResultSet getOrderInfo(int customerId) {
		try {
			conn = DBConnection.getConnection();

			String sql = "SELECT * FROM Order_Sheet WHERE CUS_ID = ?";

			pStmt = conn.prepareStatement(sql);

			//INSERT文中の[？]に使用する値を設定しSQLを完成
			pStmt.setInt(1, customerId);
			rs = pStmt.executeQuery();
		}catch (SQLException e) {
			// データベースとの接続に失敗した場合
			e.printStackTrace();
		}catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }return rs;
	}


	/**-----------------------------注文した商品の情報を引き出す(商品以外）-----------------------------------**/
	public ResultSet getOrderItems(int customerId) {
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT * FROM Order_Items JOIN Product_Variation ON "
					+ "Order_Items.OR_INDIVCODE = Product_Variation.INDIVIDUAL_CODE JOIN Products ON "
					+ "Product_Variation.P_CODE = Products.PRODUCTCODE "
					+ "WHERE OR_CUSID = ?";
			pStmt = conn.prepareStatement(sql);

			//INSERT文中の[？]に使用する値を設定しSQLを完成
			pStmt.setInt(1, customerId);
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
