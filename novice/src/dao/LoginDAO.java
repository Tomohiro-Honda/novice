package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
		private Connection conn = null;
		private ResultSet  rs  = null;
		private PreparedStatement pStmt = null;

	/**--------------------ログイン処理。指定されたメールアドレスとパスワードを使って検索--------------------**/
	public ResultSet selectCustomer(String mail, String pass )  {

		//データベース接続
				try{
					conn = DBConnection.getConnection();
					String sql = "SELECT * FROM Customers WHERE MAIL = ? AND PASS = ?";
					pStmt = conn.prepareStatement(sql);

					//INSERT文中の[？]に使用する値を設定しSQLを完成
					pStmt.setString(1, mail);
					pStmt.setString(2, pass);

					rs = pStmt.executeQuery();

				}catch (SQLException se) {
					// データベースとの接続解除に失敗した場合
					se.printStackTrace();
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







