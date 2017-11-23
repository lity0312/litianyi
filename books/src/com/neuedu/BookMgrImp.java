package com.neuedu;

public class BookMgrImp implements BookMgr{
	//比喻为存储书的仓库
	private static Book[] books=new Book[100];
	public void addBook() {
		
	}
	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		books[book.getBookNo()-1]=book; 
	}
	@Override
	public void delBook(int bookNo) {
		// TODO Auto-generated method stub
		books[bookNo-1]=null;
	}
	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		books[book.getBookNo()-1]=book;
	}
	@Override
	public Book queryBook(String bookName) {
		Book book=null;
		for(int i=0;i<books.length;i++) {
			if(null!=books[i]&&books[i].getBookName().equals(bookName)) {
				book=books[i];
				break;
			}
		}
		return book;
	}
	@Override
	public Book queryBook(int bookNo) {
		Book book=null;
		for(int i=0;i<books.length;i++) {
			if(null!=books[i]&&books[i].getBookNo()==bookNo) {
				book=books[i];
				break;
			//方法一(用户的输入必须是>=1)
				//return books[bookNo-1];
			}
		}
		
		return book;
	}
	@Override
	public Book[] queryBook() {
		int cnt=0;
		for(int i=0;i<books.length;i++) {
			if(null!=books[i]) {
				cnt++;
			}
		}
		Book[] newBook=new Book[cnt];
		int j=0;
		for(int i=0;i<books.length;i++) {
			if(null!=books[i]){
				newBook[j]=books[i];
				j++;
			}
		}
		return newBook;
	}
	@Override
	public void printBook(Book[] book) {
		// TODO Auto-generated method stub
		System.out.println("编号\t书名\t作者\t出版社\t价格\t其他属性");
		System.out.println("***\t***\t***\t***\t***");
		if(null!=book) { 
			
			for(int i=0;i<book.length;i++) {
				if(null!=book[i]) {
					String  otherStr=null;
					if(book[i] instanceof ComputerBook){
						otherStr=((ComputerBook)book[i]).getLang();
					}else if(book[i] instanceof TestBook) {
						otherStr=((TestBook)book[i]).getYear()+"";
					}
					else if(book[i] instanceof OtherBook) {
						otherStr=((OtherBook)book[i]).getUsers();
					}
					System.out.println(""+book[i].getBookNo()+"\t"+
				book[i].getBookName()+"\t"+
							book[i].getAuthor()+"\t"+
				book[i].getPubCom()+"\t"+
						book[i].getPrice()+"\t"+otherStr+"");
				}
			}
		}
	}

}
