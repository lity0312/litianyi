package com.neuedu.domain;
/**
 * 用户类型实体
 * @author admin
 *
 */
public class UserType {
	
	private int id;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "UserType [id=" + id + ", content=" + content + "]";
	}
	public UserType(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	public UserType() {
		
	}
	

}
