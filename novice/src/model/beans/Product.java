package model.beans;
import java.io.Serializable;


public class Product implements Serializable{
//	private int id;//商品ID
	private String productCode; //商品コード
	private String productName; //商品名
	private int price ; //価格
	private int stock; //在庫数

	public Product() {}

	public Product(String productCode, String productName, int price, int stock) {
//		this.id =id;
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}

//	public int getId() {
//		return id;
//	}

//	public void setId(int id) {
//		this.id = id;
//	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}



}
