package com.example.menschapp.util;

import java.io.Serializable;
import java.math.BigDecimal;

public class Games implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int slots;
	private int ownerId;
	private boolean started;
	
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

//	public void setGameId(Integer valueOf) {
//		// TODO Auto-generated method stub
//		
//	}
}
