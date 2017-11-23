package com.neuedu;

import com.neuedu.util.InputUtil;

public class BookMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputUtil.initData();
		welcomePage();
		
	}
	//欢迎页面
	public static void welcomePage() {
		System.out.println("***欢迎使用Java四班图书管理系统***");
		System.out.println("1>用户登录");
		System.out.println("2>退出系统");
		int cmd=InputUtil.getInt();//静态方法直接调用：类.方法名
		if(cmd==1) {
				loginPage();
			}
		else if(cmd==2) {
			System.exit(0);
		}else {
			System.out.println("请输入合法的命令!");
			InputUtil.printLine();
			welcomePage();
		}
	}
	//登陆页面
	public static void loginPage() {
		System.out.println("***登陆页面***");
		System.out.println("请输入您的登陆账号信息：");
		System.out.println("用户名：");
		String uName=InputUtil.getStr();
		System.out.println("密码：");
		String pwd=InputUtil.getStr();
		if(uName.equals("admin")&&pwd.equals("admin")) {
			indexPage();
	}else {
		System.out.println("请输入正确的账号信息!");
		InputUtil.printLine();
		loginPage();
	}

}
	//首页
	public static void indexPage() {
		System.out.println("***欢迎您admin，这是首页***");
		System.out.println("1>查询");
		System.out.println("2>新增");
		System.out.println("3>修改");
		System.out.println("4>删除");
		System.out.println("5>退出登录");
		int cmd=InputUtil.getInt();
		if(cmd==1) {
			queryPage();
		}else if(cmd==2) {
			addPage();
		}else if(cmd==3) {
			updatePage();
		}else if(cmd==4) {
			System.out.println("***图书删除***");
			System.out.println("请输入您要删除的图书编号");
			int bookNo=InputUtil.getInt();
			new BookMgrImp().delBook(bookNo);
			System.out.println("图书已删除");

		}else if(cmd==5) {
			welcomePage();
		}else {
			System.out.println("请输入合法的命令!");
			InputUtil.printLine();
			indexPage();
		}
}
	public static void queryPage() {
		System.out.println("***图书查询***");
		System.out.println("1>按照名称查询");
		System.out.println("2>按照书号查询");
		System.out.println("3>查询全部");
		System.out.println("4>返回");
		int cmd=InputUtil.getInt();
		if(cmd==1) {
			System.out.println("请输入要查询的书籍名称");
			String bookName=InputUtil.getStr();
			BookMgrImp mgr=new BookMgrImp();
			Book book=mgr.queryBook(bookName);
			mgr.printBook(new Book[] {book});
			InputUtil.printLine();
			queryPage();
		}else if(cmd==2) {
			System.out.println("请输入要查寻的书号");
			int bookNo=InputUtil.getInt();
			BookMgrImp mgr=new BookMgrImp();
			mgr.printBook(new Book[] {mgr.queryBook(bookNo)});
			InputUtil.printLine();
			queryPage();
		}else if(cmd==3) {
			BookMgrImp mgr=new BookMgrImp();
			mgr.printBook(mgr.queryBook());
			InputUtil.printLine();
			queryPage();
		}else if(cmd==4) {
			indexPage();
	
		}else {
			System.out.println("请输入合法的命令!");
			queryPage();
		}
	}
	public static void addPage() {
		System.out.println("***图书新增***");
		System.out.println("1>新增计算机类");
		System.out.println("2>新增考试类");
		System.out.println("3>新增其他类");
		System.out.println("4>返回");
		int cmd=InputUtil.getInt();
		if(cmd==1) {
			System.out.println("请输入要新增的书籍名称");
			String bookName=InputUtil.getStr();
			System.out.println("请输入要新增的书籍作者");
			String bookAuthor=InputUtil.getStr();
			System.out.println("请输入要新增的书籍出版社");
			String bookPubCom=InputUtil.getStr();
			System.out.println("请输入要新增的书籍价格");
			double bookPrice=InputUtil.getDouble();
			System.out.println("请输入要新增的书籍语言");
			String bookLang=InputUtil.getStr();
			BookMgrImp mgr=new BookMgrImp();
			mgr.addBook(new ComputerBook(bookName,bookAuthor,bookPubCom,bookPrice,bookLang));
			Book book=mgr.queryBook(bookName);
			mgr.printBook(new Book[] {book});
			
			InputUtil.printLine();
			addPage();
		}else if(cmd==2) {
			System.out.println("请输入要新增的书籍名称");
			String bookName=InputUtil.getStr();
			System.out.println("请输入要新增的书籍作者");
			String bookAuthor=InputUtil.getStr();
			System.out.println("请输入要新增的书籍出版社");
			String bookPubCom=InputUtil.getStr();
			System.out.println("请输入要新增的书籍价格");
			double bookPrice=InputUtil.getDouble();
			System.out.println("请输入要新增的书籍年份");
			int bookYear=InputUtil.getInt();
			BookMgrImp mgr=new BookMgrImp();
			mgr.addBook(new TestBook(bookName,bookAuthor,bookPubCom,bookPrice,bookYear));
			Book book=mgr.queryBook(bookName);
			mgr.printBook(new Book[] {book});
			
			InputUtil.printLine();
			addPage();
		}else if(cmd==3) {
			System.out.println("请输入要新增的书籍名称");
			String bookName=InputUtil.getStr();
			System.out.println("请输入要新增的书籍作者");
			String bookAuthor=InputUtil.getStr();
			System.out.println("请输入要新增的书籍出版社");
			String bookPubCom=InputUtil.getStr();
			System.out.println("请输入要新增的书籍价格");
			double bookPrice=InputUtil.getDouble();
			System.out.println("请输入要新增的用户群");
			String bookUsers=InputUtil.getStr();
	        BookMgrImp mgr=new BookMgrImp();
			mgr.addBook(new ComputerBook(bookName,bookAuthor,bookPubCom,bookPrice,bookUsers));
			Book book=mgr.queryBook(bookName);
			mgr.printBook(new Book[] {book});
			
			InputUtil.printLine();
			addPage();
		}else if(cmd==4) {
			indexPage();
	
		}else {
			System.out.println("请输入合法的命令!");
			addPage();
		}
	}
	public static void updatePage() {
		System.out.println("请输入要修改的书号");
		int bookNo=InputUtil.getInt();
		BookMgrImp mgr=new BookMgrImp();
		Book book=mgr.queryBook(bookNo);
		System.out.println("请确认您要修改的书籍信息");
		mgr.printBook(new Book[] {book});
		InputUtil.printLine();
		if(book instanceof ComputerBook) {
			System.out.println("请输入新的书名");
			String bookName=InputUtil.getStr();
			System.out.println("请输入新的作者");
			String author=InputUtil.getStr();
			System.out.println("请输入新的出版社");
			String pubCom=InputUtil.getStr();
			System.out.println("请输入新的价格");
			double price=InputUtil.getDouble();
			System.out.println("请输入新的编程语言");
			String lang=InputUtil.getStr();
			book.setBookName(bookName);
			book.setAuthor(author);
			book.setPubCom(pubCom);
			book.setPrice(price);
			((ComputerBook) book).setLang(lang);
		}else if(book instanceof TestBook) {
			System.out.println("请输入新的书名");
			String bookName=InputUtil.getStr();
			System.out.println("请输入新的作者");
			String author=InputUtil.getStr();
			System.out.println("请输入新的出版社");
			String pubCom=InputUtil.getStr();
			System.out.println("请输入新的价格");
			double price=InputUtil.getDouble();
			System.out.println("请输入新的年份");
			int year=InputUtil.getInt();
			book.setBookName(bookName);
			book.setAuthor(author);
			book.setPubCom(pubCom);
			book.setPrice(price);
			((TestBook) book).setYear(year);
		}else if(book instanceof OtherBook) {
			System.out.println("请输入新的书名");
			String bookName=InputUtil.getStr();
			System.out.println("请输入新的作者");
			String author=InputUtil.getStr();
			System.out.println("请输入新的出版社");
			String pubCom=InputUtil.getStr();
			System.out.println("请输入新的价格");
			double price=InputUtil.getDouble();
			System.out.println("请输入新的用户群体");
			String users=InputUtil.getStr();
			book.setBookName(bookName);
			book.setAuthor(author);
			book.setPubCom(pubCom);
			book.setPrice(price);
			((OtherBook) book).setUsers(users);
		}
		
System.out.println("修改完成");
InputUtil.printLine();
		queryPage();
	}
	
}
