package com.example.menschapp.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Dice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int diceNumber;
	private int diceId;
	
	public Dice() {
		super();
	}
	
	public String toString() {
		return "Dice: " + diceNumber;
	}

	/**
	 * @return the diceNumber
	 */
	public int getDiceNumber() {
		return diceNumber;
	}

	/**
	 * @return the diceId
	 */
	public int getDiceId() {
		return diceId;
	}

	/**
	 * @param diceNumber the diceNumber to set
	 */
	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}

	/**
	 * @param diceId the diceId to set
	 */
	public void setDiceId(int diceId) {
		this.diceId = diceId;
	}
}
