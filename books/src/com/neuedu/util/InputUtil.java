package com.neuedu.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.neuedu.BookMgrImp;
import com.neuedu.ComputerBook;
import com.neuedu.OtherBook;
import com.neuedu.TestBook;

public class InputUtil {
	public static void main(String[] args) {
		getInt();
		getStr();
		getDouble();
		
	}
	public static void initData() {
		//初始化一部分数据
		BookMgrImp mgr=new BookMgrImp();
		mgr.addBook(new ComputerBook("Java编程思想","Tom","人民邮电",88.5,"Java"));
		mgr.addBook(new ComputerBook("C#编程","Jack","机械出版",100,"C#"));
		
		mgr.addBook(new TestBook("自考英语","张三","人民邮电",89.5,2015));
		mgr.addBook(new TestBook("计算机","李四","机械工业",109.5,2018));
		
		mgr.addBook(new OtherBook("中华五千年","王五","人民邮电",189.5,"历史"));
		mgr.addBook(new OtherBook("大秦帝国","青海","清华大学",89.5,"政治"));
	}

	public static int getInt() {
		int i=-1;
		while(true) {
		Scanner sc=new Scanner(System.in);
		try {
			i=sc.nextInt();
			if(i<0) {
				System.out.println("不能输入负数!");
				continue;
			}
		}catch(InputMismatchException e) {
			System.out.println("请输入正确的数字!");
			continue;
		}
		break; 
	}
		return i;
		
	}	
	
	public static double getDouble() {
		
		double i;
		while(true) {
			Scanner sc=new Scanner(System.in);
			try {
				i=sc.nextDouble();
				if(i<0) {
					System.out.println("不能输入负数!");
					continue;
				}
			}catch(InputMismatchException e) {
				System.out.println("请输入正确的浮点数!");
				continue;
			}
			break;
		}
		return i;
		
	}
	public static String getStr() {
		String str=null;
		while(true) {
		Scanner sc=new Scanner(System.in);
		str=sc.next();
		if(null==str||str.equals("")) {
			System.out.println("输入不可为空!");
			continue;
		}
		break;
		}
		return str;
	}
	public static void printLine() {
		System.out.println("------------------------------");
	}

}
