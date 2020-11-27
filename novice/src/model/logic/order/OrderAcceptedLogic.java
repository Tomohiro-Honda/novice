package model.logic.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import dao.OrderDAO;
import model.beans.OrderSheet;

public class OrderAcceptedLogic {

	int orderID = 0;
	ResultSet rs = null;
	OrderDAO dao = null;

	public Timestamp orderDone(OrderSheet orderSheet) {
		Timestamp time = null;
		try {
			dao = new OrderDAO();
			time = dao.order(orderSheet);
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}return time;

	}
	public int getOrderID(int customerID, Timestamp time) {
		try {
			dao = new OrderDAO();
			rs = dao.checkOrderID(customerID, time);
			if(rs.next()) {
				orderID = rs.getInt("ID");
			}

		}catch (SQLException e) {

			e.printStackTrace();

		}finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
		return orderID;
	}
}
