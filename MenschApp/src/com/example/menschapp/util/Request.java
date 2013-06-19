package com.example.menschapp.util;

import org.ksoap2.serialization.SoapPrimitive;

public class Request {

	private boolean success;
	
	private String state="not answered";
	
	private int id;
	
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setSuccess(SoapPrimitive success) {
		// TODO Auto-generated method stub
		
	}
}
