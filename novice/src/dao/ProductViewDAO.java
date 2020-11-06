package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductViewDAO {
	//データベース接続に使用する情報
		private final String JDBC_URL = "jdbc:mysql://localhost:3306/novice_db";
		private final String USER_NAME = "test";
		private final String PASSWORD = "test";

		private Connection conn = null;
		private PreparedStatement pStmt = null;
		private ResultSet rs = null;

		/**--------------------productNameで指定された商品の情報を入手する--------------------**/
		public ResultSet selectProduct(String productCode)  {
			//データベース接続
			try{
				conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
				String sql = "SELECT * FROM Products WHERE PRODUCTCODE = ?";
				pStmt = conn.prepareStatement(sql);

				//INSERT文中の[？]に使用する値を設定しSQLを完成
				pStmt.setString(1, productCode);

				rs = pStmt.executeQuery();

			}catch (SQLException se) {
				// データベースとの接続解除に失敗した場合
				se.printStackTrace();
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
