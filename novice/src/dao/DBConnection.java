package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//データベース接続の情報を持ったクラス
public class DBConnection {

	public static Connection getConnection() throws URISyntaxException, SQLException{

		URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath() + "?useUnicode=true&characterEncoding=utf8";



        Connection conn = null;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);
            return conn;
        } catch (SQLException e) {
//            throw new IllegalMonitorStateException();
        	e.printStackTrace();
        	return conn;
        }
	}
}

