package com.neuedu.pojo;
import java.util.*;
/**
 * pojo(简单Java对象)，主要用于承载数据库表中的数据
 * 一般一个pojo类对应一张表
 * 有的项目可能使用的是entity这个目录
 * @author admin
 *
 */
public class User {
	private int id;
	private String name;
	private int age;
	private Date birthday;
	public User(int id,String name,int age,Date birthday) {
		this.id=id;
		this.name=name;
		this.age=age;
		this.birthday=birthday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String toString() {
		return"{id="+id+",name="+name+",age="+age+",birthday="+birthday+"}";
	}

}
