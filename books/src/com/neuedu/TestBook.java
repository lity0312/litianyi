package com.neuedu;

public class TestBook extends Book{
	private int year;
	public TestBook(String bookName,String author,String pubCom,double price,int year) {
		super(bookName,author,pubCom,price);
		this.year=year;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

}
