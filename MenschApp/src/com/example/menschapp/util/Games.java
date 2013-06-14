package com.example.menschapp.util;

import java.io.Serializable;
import java.math.BigDecimal;

public class Games implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int slots;
	private int ownerId;
	private boolean started;
	
	private String spieler1;
	private String spieler2;
	private String spieler3;
	private String spieler4;
	
	public Games() {
		super();
	}
	
	public Games(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
		
	public String toString() {
		return "game id " + id + "; slots " + slots + "; owner " + ownerId + "; started " + started;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getSpieler1() {
		return spieler1;
	}

	public void setSpieler1(String spieler1) {
		this.spieler1 = spieler1;
	}

	public String getSpieler2() {
		return spieler2;
	}

	public void setSpieler2(String spieler2) {
		this.spieler2 = spieler2;
	}

	public String getSpieler3() {
		return spieler3;
	}

	public void setSpieler3(String spieler3) {
		this.spieler3 = spieler3;
	}

	public String getSpieler4() {
		return spieler4;
	}

	public void setSpieler4(String spieler4) {
		this.spieler4 = spieler4;
	}

}
