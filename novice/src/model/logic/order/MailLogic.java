package model.logic.order;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.beans.Customer;

public class MailLogic {

	private String  title = null;
    private  String message = null;
    private String myAdress = null;
    private String myPass = null;
    private String mailAdress = null;
    private String mailTo = null;


    public void thanksMail(Customer loginCustomer, int orderID) {
    	try {

    		myAdress = "********@gmail.com";
    		myPass = "********";
    		mailAdress = loginCustomer.getMail();
    		mailTo = loginCustomer.getLastName() + loginCustomer.getFirstName() + "様";

    		title = "ご注文ありがとうございます!　注文番号：" + orderID;
    		StringBuilder sbMessage = new StringBuilder();
    		sbMessage.append(mailTo + "\n");
    		sbMessage.append("この度はnovice online shopへご注文いただき、誠にありがとうございます。\n");
    		sbMessage.append("ご注文いただいた商品は存在しません。\n");
    		sbMessage.append("いつか気が向いて作ったらご送付いたします。\n");
    		sbMessage.append("それまでどうか気長にお待ちいただければ幸いです。\n");
    		sbMessage.append("ご注文内容はマイページの『注文履歴』から確認できます。\n");
    		sbMessage.append("\n");
    		sbMessage.append("今後ともnovice online shopをご愛顧賜りますよう、\n");
    		sbMessage.append("よろしくお願いいたします。\n");
    		sbMessage.append("\n");
    		sbMessage.append("novice online shop　一同");
    		message = sbMessage.toString();

    		 Properties property = new Properties();

    		 String host = "smtp.gmail.com";
    		 String port = "587";
    		 String starttls = "true";

    		 property.put("mail.smtp.auth", "true");
             property.put("mail.smtp.debug", "true");
             property.put("mail.smtp.starttls.enable", starttls);
             property.put("mail.smtp.host", host);
             property.put("mail.smtp.port", port);

             Session session = Session.getInstance(property, new javax.mail.Authenticator(){
                 protected PasswordAuthentication getPasswordAuthentication(){
                     return new PasswordAuthentication(myAdress, myPass);
                 }
             });

             MimeMessage mimeMessage = new MimeMessage(session);

             InternetAddress toAddress = new InternetAddress(mailAdress, mailTo);

             mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);

             InternetAddress fromAddress =
                     new InternetAddress(myAdress,"novice online shop");

             mimeMessage.setFrom(fromAddress);

             mimeMessage.setSubject(title, "ISO-2022-JP");

             mimeMessage.setText(message, "ISO-2022-JP");

             Transport.send(mimeMessage);

    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }



}
