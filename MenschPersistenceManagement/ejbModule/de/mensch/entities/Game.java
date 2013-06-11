package de.mensch.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	@OneToOne
	private Customer owner;

	public Game() {
		super();
	}
//	
//	public Account(Customer owner) {
//		this.balance = BigDecimal.ZERO;
//		this.owner = owner;
//		this.owner.addNewAccount(this);
//	}
//	
	public int getId() {
		return id;
	}
		
	public Customer getOwner() {
		return owner;
	}
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
	public void setId(int id) {
		this.id = id;
	}
//
//	public void setBalance(BigDecimal balance) {
//		this.balance = balance;
//	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	
}
