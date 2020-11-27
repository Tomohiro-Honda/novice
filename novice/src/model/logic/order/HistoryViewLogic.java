package model.logic.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.HistoryDAO;
import model.beans.OrderItem;
import model.beans.OrderSheet;

public class HistoryViewLogic {

	HistoryDAO dao = null;
	ResultSet rs = null;
	List<OrderSheet> orderList = null;
	List<OrderItem> orderItems = null;
	OrderSheet os = null;
	OrderItem oi = null;


	public List<OrderSheet> viewHistory(int customerId){
		try {
		dao = new HistoryDAO();
		rs = dao.getOrderInfo(customerId);
		orderList = new ArrayList<OrderSheet>();
		orderItems = new ArrayList<OrderItem>();

		while(rs.next()) {
			os = new OrderSheet();
			os.setDestinationPostal(Integer.toString(rs.getInt("DEST_POSTAL"))); //送付先郵便番号
			os.setDestinationAdress(rs.getString("DEST_AD")); //送付先住所
			os.setDate(rs.getTimestamp("OR_DATE")); //注文受付日
			os.setTotalPrice(rs.getInt("TOTAL_PRICE")); //合計金額
			os.setPayment(rs.getString("PAYMENT")); //支払い方法
			os.setTax(rs.getInt("TAX")); //内税
			os.setShipPrice(rs.getInt("SHIP_PRICE")); //送料
			os.setCodPrice(rs.getInt("COD_PRICE")); //手数料
			orderList.add(os);
		}

		//お客さんの全ての注文商品を一つのリストに入れる
		rs = dao.getOrderItems(customerId);
		while(rs.next()) {
			oi = new OrderItem();
			oi.setProductCode(rs.getString("PRODUCTCODE"));
			oi.setProductName(rs.getString("PRODUCTNAME"));
			oi.setIndividualCode(rs.getString("INDIVIDUAL_CODE"));
			oi.setSize(rs.getString("SIZE"));
			oi.setType(rs.getString("TYPE"));
			oi.setPrice(rs.getInt("PRICE"));
			oi.setQuantity(rs.getInt("OR_QUANTITY"));
			oi.setOrderDate(rs.getTimestamp("OR_TIME"));
			orderItems.add(oi);
		}

		//全注文商品を入れたリストの中から注文時間が注文情報と同じものを注文情報の中の注文商品リストに入れる。
		//これで注文情報が完成し、orderListは必要な情報を持った状態になる。
		for(int i =0; i < orderList.size(); i++) {
			for(int j =0; j < orderItems.size();  j++) {
				if(orderList.get(i).getDate().equals(orderItems.get(j).getOrderDate())) {
					orderList.get(i).getItemsOrdered().add(orderItems.get(j));
					}
				}
		}

		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}return orderList;
	}
}




