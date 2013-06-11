package de.mensch.dto;

import java.io.Serializable;
import java.util.HashMap;


public class DiceTO implements Serializable {
	
	private static final long serialVersionUID = 123L;
	
	private String diceNumber = "";

	public DiceTO() {
	}
	
	public DiceTO(String diceNumber) {
		super();
		this.diceNumber = diceNumber;
	}


	public String getDiceNumber() {
		return diceNumber;
	}

	public void setDiceNumber(String diceNumber) {
		this.diceNumber = diceNumber;
	}
}
