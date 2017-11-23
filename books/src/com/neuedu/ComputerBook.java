package com.neuedu;
/**
 * 电脑书籍，继承book
 * @author admin
 *
 */
public class ComputerBook extends Book {
	private String lang;
	public ComputerBook(String bookName,String author,String pubCom,double price,String lang) {
		super(bookName,author,pubCom,price); 
		this.lang=lang;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

}
