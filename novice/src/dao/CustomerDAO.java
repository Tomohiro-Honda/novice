package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.beans.Customer;

public class CustomerDAO {
	private Connection conn = null;
	private PreparedStatement pStmt = null;
	private ResultSet rs = null;

	/**--------------------ユーザーが登録済かチェックする--------------------**/
	public ResultSet check(String mail) {
		//判定用変数
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT MAIL FROM Customers WHERE MAIL = ?";
			pStmt = conn.prepareStatement(sql);
			//INSERT文中の[？]に使用する値を設定しSQLを完成
			pStmt.setString(1, mail);
			rs = pStmt.executeQuery();

		}catch (SQLException e) {
			e.printStackTrace();
		}catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
		return rs;
	}

	/**--------------------ユーザーの情報をデータベースへ登録する--------------------**/
	public boolean create(Customer customer) {
		try {
				conn =DBConnection.getConnection();
			//idは自動連番なので指定しなくてよい
			String sql = "INSERT INTO Customers(LASTNAME, FIRSTNAME,  MAIL, PASS,  POSTAL, "
					+ "PREFECTURE, MUNICIPALITY, STREETADRESS, TELL, TIME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			pStmt = conn.prepareStatement(sql);

			//INSERT文中の[？]に使用する値を設定しSQLを完成
			pStmt.setString(1, customer.getLastName());
			pStmt.setString(2, customer.getFirstName());
			pStmt.setString(3, customer.getMail());
			pStmt.setString(4,  customer.getPass());
			pStmt.setInt(5, Integer.parseInt(customer.getPostal()));
			pStmt.setString(6, customer.getPref());
			pStmt.setString(7, customer.getMuni());
			pStmt.setString(8, customer.getStAd());
			pStmt.setString(9, customer.getTell());
			pStmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));

			//INSERT文を実行(resultには追加された行数が代入される。)
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
		return true;
	}

	/**--------------------アカウント情報の変更（メールアドレスとパスワード以外）--------------------**/
	public boolean changeInfo(Customer ciCustomer){
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE Customers SET LASTNAME = ?, FIRSTNAME = ?, POSTAL = ?, "
					+ "PREFECTURE = ?, MUNICIPALITY = ?, STREETADRESS = ?, TELL = ? WHERE MAIL = ?";
			pStmt = conn.prepareStatement(sql);

			//INSERT文中の[？]に使用する値を設定しSQLを完成
			pStmt.setString(1, ciCustomer.getLastName());
			pStmt.setString(2, ciCustomer.getFirstName());
			pStmt.setInt(3, Integer.parseInt(ciCustomer.getPostal()));
			pStmt.setString(4, ciCustomer.getPref());
			pStmt.setString(5, ciCustomer.getMuni());
			pStmt.setString(6, ciCustomer.getStAd());
			pStmt.setString(7, ciCustomer.getTell());
			pStmt.setString(8, ciCustomer.getMail());

			//UPDATE文を実行(resultには追加された行数が代入される。)
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
		return true;
	}

	/**---------------------メールアドレス変更---------------------**/
	public boolean changeMail(String oldMail, String newMail) {
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE Customers SET MAIL = ? WHERE MAIL = ?";
			pStmt = conn.prepareStatement(sql);

			//INSERT文中の[？]に使用する値を設定しSQLを完成
			pStmt.setString(1, newMail);
			pStmt.setString(2, oldMail);

			//UPDATE文を実行(resultには追加された行数が代入される。)
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
		return true;

	}

	/**--------------------パスワード変更--------------------**/
	public boolean changePass(String newPassA, String mail) {
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE Customers SET PASS = ? WHERE MAIL = ?";
			pStmt = conn.prepareStatement(sql);

			//INSERT文中の[？]に使用する値を設定しSQLを完成
			pStmt.setString(1, newPassA);
			pStmt.setString(2, mail);

			//UPDATE文を実行(resultには追加された行数が代入される。)
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
		return true;

	}

	/**--------------------データベースとの接続解除--------------------**/
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
