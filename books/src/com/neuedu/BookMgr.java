package com.neuedu;

public interface BookMgr {
	//新增书籍
	public void addBook(Book book);
	//删除书籍
	public void delBook(int bookNo);
	//修改书籍
	public void updateBook(Book book);
	//书籍查询，sangechongzaifangf
	public Book queryBook(String bookName);
	public Book queryBook(int bookNo);
	public Book[] queryBook();
	public void printBook(Book[] book);

}
