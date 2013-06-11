package de.mensch.dto;

import java.io.Serializable;
import java.util.HashMap;


public class CustomerTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String userName;
	private String password;
	
	public CustomerTO() {
	}
	
	public CustomerTO(int id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
