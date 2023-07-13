package com.faith.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
	private int userId;
	private String userName;
	private String passWord;
	private int roleId;
	

	public User(int userId, String userName, String passWord, int roleId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.roleId = roleId;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", passWord=" + passWord + ", roleId=" + roleId + "]";
	}

}
