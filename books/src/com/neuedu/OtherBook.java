package com.neuedu;

public class OtherBook extends Book {
	private String users;
	public OtherBook(String bookName,String author,String pubCom,double price,String users) {
		super(bookName,author,pubCom,price);
		this.users=users;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}

}
