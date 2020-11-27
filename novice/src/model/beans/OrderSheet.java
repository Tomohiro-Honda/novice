package model.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderSheet implements Serializable{

	private int id; //注文者情報
	private int orderId; //注文ID
	private String destinationPostal; //届け先郵便番号
	private String destinationAdress ;//届け先住所
	private Timestamp date; //注文受け付け日
	private int totalPrice =0;//合計金額
	private String payment; //支払い方法
	private int tax =0; //内税
	private int shipPrice =0; //送料
	private int codPrice = 0; //代引き手数料
	private List<CartItem> orderItems = new ArrayList<CartItem>(); //購入商品リスト
	private List<OrderItem> itemsOrdered = new ArrayList<OrderItem>();//注文した商品リスト

	public OrderSheet(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getDestinationPostal() {
		return destinationPostal;
	}

	public void setDestinationPostal(String destinationPostal) {
		this.destinationPostal = destinationPostal;
	}

	public String getDestinationAdress() {
		return destinationAdress;
	}

	public void setDestinationAdress(String destinationAdress) {
		this.destinationAdress = destinationAdress;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public List<CartItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<CartItem> orderItems) {
		this.orderItems = orderItems;
	}

	public List<OrderItem> getItemsOrdered() {
		return itemsOrdered;
	}

	public void setItemsOrdered(List<OrderItem> itemsOrdered) {
		this.itemsOrdered = itemsOrdered;
	}

	public int getShipPrice() {
		return shipPrice;
	}

	public void setShipPrice(int shipPrice) {
		this.shipPrice = shipPrice;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public int getCodPrice() {
		return codPrice;
	}

	public void setCodPrice(int codPrice) {
		this.codPrice = codPrice;
	}

	//内消費税の計算
//	public int calcTax(int t1, int t2, int t3) {
//		int total = t1 + t2 + t3;
//
//		return totalPrice;
//	}



}
