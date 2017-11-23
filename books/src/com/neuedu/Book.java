package com.neuedu;

/**
 * 一个通用的book信息类
 * @author admin
 *
 */

public class Book {
	private static int bookNoSeq=0;
	//属性，成员变量
	private int bookNo;
	private String bookName;//书名
	private String author;//作者
	private String pubCom;//出版社
	private double price;//价格
	public Book(String bookName,String author,String pubCom,double price) {
		this.bookName=bookName;
		this.author=author;
		this.pubCom=pubCom;
		this.price=price;
		bookNo=++bookNoSeq;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPubCom() {
		return pubCom;
	}
	public void setPubCom(String pubCom) {
		this.pubCom = pubCom;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getBookNo() {
		return bookNo;
	}

}
