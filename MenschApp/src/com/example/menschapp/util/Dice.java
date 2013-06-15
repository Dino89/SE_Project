package com.example.menschapp.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Dice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private double diceNumber;
	
	public Dice() {
		super();
	}
	
	public Dice(double diceNumber) {
		this.diceNumber = diceNumber;
	}
	
	public String toString() {
		return "Dice: " + diceNumber;
	}
	
//	public void setDiceNumber(String diceNumber) {
//		this.diceNumber = diceNumber;
//	}
//	
//	public String getDiceNumber() {
//		return diceNumber;
//	}
}
