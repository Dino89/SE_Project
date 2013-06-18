package de.mensch.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Game implements Serializable {
	
	private static final long serialVersionUID = 11L;
	
	@Id @GeneratedValue
	private int id;
	
	private int slots = 3;
	boolean started = false;
	@OneToOne
	private Customer owner;
	@OneToOne
	private Customer spieler1;
	@OneToOne
	private Customer spieler2 = null;
	@OneToOne
	private Customer spieler3 = null;
	@OneToOne
	private Customer spieler4 = null;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="zuschauer") @MapKey
	private java.util.Map<String,Customer> zuschauer;
	
	@OneToOne
	private GameField gameField;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="request") @MapKey
	private java.util.Map<Integer,Request> requests;
	
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
	
	public Customer getSpieler1() {
		return spieler1;
	}
	
	public void setSpieler1(Customer spieler1) {
		this.spieler1 = spieler1;
	}
	
	public java.util.Map<String, Customer> getZuschauer() {
		return zuschauer;
	}

	public void setZuschauer(java.util.Map<String, Customer> zuschauer) {
		this.zuschauer = zuschauer;
	}

	public Customer getSpieler2() {
		return spieler2;
	}
	
	public void setSpieler2(Customer spieler2) {
		this.spieler2 = spieler2;
	}
	
	public Customer getSpieler3() {
		return spieler3;
	}
	
	public void setSpieler3(Customer spieler3) {
		this.spieler3 = spieler3;
	}
	
	public Customer getSpieler4() {
		return spieler4;
	}
	
	public void setSpieler4(Customer spieler4) {
		this.spieler4 = spieler4;
	}
	
	public int getId() {
		return id;
	}
		
	public int getSlots() {
		return slots;
	}
	
	public void setSlots(int slots) {
		this.slots = slots;
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
	
	public int getGameList() {
		return id;
	}
	public boolean isStarted() {
		return started;
	}
	public void setStarted(boolean started) {
		this.started = started;
	}

	/**
	 * @return the requests
	 */
	public java.util.Map<Integer, Request> getRequests() {
		return requests;
	}

	/**
	 * @param requests the requests to set
	 */
	public void setRequests(java.util.Map<Integer, Request> requests) {
		this.requests = requests;
	}

}
