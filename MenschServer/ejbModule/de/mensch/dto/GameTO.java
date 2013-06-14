package de.mensch.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import de.mensch.entities.Customer;

public class GameTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int slots;
	private int ownerId;
	
	private Customer spieler1;
	private Customer spieler2;
	private Customer spieler3;
	private Customer spieler4;
	
	public GameTO() {
	}

	public GameTO(int id, int slots, int ownerId) {
		super();
		this.id = id;
		this.slots = slots;
		this.ownerId = ownerId;
	}


	public String toString() {
		return "Game " + this.id + " (Slots=" + this.slots + ", Owner=" + this.getOwnerId() + ")";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getSlots() {
		return slots;
	}


	public void setSlots(int i) {
		this.slots = i;
	}


	public int getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public Customer getSpieler1() {
		return spieler1;
	}

	public void setSpieler1(Customer spieler1) {
		this.spieler1 = spieler1;
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
}
