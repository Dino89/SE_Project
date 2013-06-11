package com.example.menschapp.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Dice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String diceNumber;
	
	public Dice() {
		super();
	}
	
	public Dice(String diceNumber) {
		this.diceNumber = diceNumber;
	}
	
	public String toString() {
		return "Kunde: ";
	}
	
//	public void setDiceNumber(String diceNumber) {
//		this.diceNumber = diceNumber;
//	}
//	
//	public String getDiceNumber() {
//		return diceNumber;
//	}
}
