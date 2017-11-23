package com.neuedu.dao;

import java.util.*;

import com.neuedu.pojo.User;

public interface UserDao {
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public boolean removeUser(int id);
	public User getUserbyId(int id);
	public List<User> queryUser(User user);
	

}
