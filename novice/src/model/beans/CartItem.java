package model.beans;
import java.io.Serializable;

public class CartItem implements Serializable{
	private String productCode; //商品コード
	private String productName; //商品名
	private String individualCode; //個別コード　　サイズや色の情報を含む
	private String size; // サイズ
	private String type; //色など
	private int price ; //価格
	private int quantity; //個数
	private int sum;//小計
	private int stock; //在庫数

	public CartItem() {}

	public CartItem(String productCode, String productName, int price, int quantity) {
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

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

	public String getIndividualCode() {
		return individualCode;
	}

	public void setIndividualCode(String individualCode) {
		this.individualCode = individualCode;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}




}
