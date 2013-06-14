package com.example.menschapp.util;

import java.io.Serializable;
import java.math.BigDecimal;

public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String userName;
	
	public Customer() {
		super();
	}
	
	public Customer(int id, String userName) {
		this.id = id;
		this.userName = userName;
	}
	
	public Integer getId() {
		return id;
	}
		
	public String toString() {
		return "Username: " + userName;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
