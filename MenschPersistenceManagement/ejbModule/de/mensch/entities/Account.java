package de.mensch.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	private BigDecimal balance;
	@ManyToOne
	private Customer owner;

	public Account() {
		super();
	}
//	
//	public Account(Customer owner) {
//		this.balance = BigDecimal.ZERO;
//		this.owner = owner;
//		this.owner.addNewAccount(this);
//	}
//	
//	public int getId() {
//		return id;
//	}
//	
//	public BigDecimal getBalance() {
//		return balance;
//	}
//	
//	public Customer getOwner() {
//		return owner;
//	}
//	
//	public void increase(BigDecimal amount) {
//		this.balance = this.balance.add(amount);
//	}
//	
//	public void decrease(BigDecimal amount) {
//		this.balance = this.balance.subtract(amount);
//	}
//	
//	public String toString() {
//		return "Account " + this.id + " (Balance=" + this.balance + ", Owner=" + this.getOwner().getUserName() + ")";
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public void setBalance(BigDecimal balance) {
//		this.balance = balance;
//	}
//
//	public void setOwner(Customer owner) {
//		this.owner = owner;
//	}
	
}
