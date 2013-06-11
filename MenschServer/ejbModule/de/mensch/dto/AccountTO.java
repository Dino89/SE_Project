package de.mensch.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private BigDecimal balance;
	private int ownerId;
	
	
	public AccountTO() {
	}

	public AccountTO(int id, BigDecimal balance, int ownerId) {
		super();
		this.id = id;
		this.balance = balance;
		this.ownerId = ownerId;
	}


	public String toString() {
		return "Account " + this.id + " (Balance=" + this.balance + ", Owner=" + this.getOwnerId() + ")";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	public int getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
}
