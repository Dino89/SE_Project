package de.mensch.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import de.mensch.entities.Customer;

public class RequestTO implements Serializable {
	
	private static final long serialVersionUID = 9871L;
	/*
	 * Request id
	 */
	private int id;
	private String userName;

	public RequestTO() {
	}

	public RequestTO(int id, String userName, String hallo) {
		super();
		this.id = id;
		this.userName = userName;
	}

	public void setUserName(String user) {
		this.userName = user;		
	}

	public void setId(int id) {
		this.id = id;		
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
		
	}

}
