package model.beans;

import java.io.Serializable;

public class Customer implements Serializable{
	private  int id; //ユーザー固有ID
	private String lastName; //ユーザー名（姓）
	private String firstName; //ユーザー名（名）
	private String mail; //メアド
	private String pass;//パスワード

	private String postal; //郵便番号 ハイフンなし
	private String pref; //住所（都道府県）
	private String muni; //住所（市区町村）municipality略
	private String stAd; //住所（丁目番地以降）street,adressから
	private String tell; //電話番号


	public Customer() {}

//	新規登録で使うコンストラクタ
	public Customer(String lastName, String firstName, String mail, String pass, String postal, String pref, String muni, String stAd, String tell) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.mail = mail;
		this.pass = pass;
		this.postal = postal;
		this.pref = pref;
		this.muni = muni;
		this.stAd = stAd;
		this.tell = tell;
	}


	//getter
	public int getId() {return id;}
	public String getLastName() {return lastName;}
	public String getFirstName() {return firstName;}
	public String getMail() {return mail;}
	public String getPass() {return pass;}
	public String getPostal() {return postal;}
	public String getPref() {return pref;}
	public String getMuni() {return muni;}
	public String getStAd() {return stAd;}
	public String getTell() {return tell;}

	//setter
	public void setId(int id) {
		this.id = id;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public void setPref(String pref) {
		this.pref = pref;
	}
	public void setMuni(String muni) {
		this.muni = muni;
	}
	public void setStAd(String stAd) {
		this.stAd = stAd;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}


}
