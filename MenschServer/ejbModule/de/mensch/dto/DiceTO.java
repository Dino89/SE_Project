package de.mensch.dto;

import java.io.Serializable;
import java.util.HashMap;

import de.mensch.entities.Customer;


public class DiceTO implements Serializable {
	
	private static final long serialVersionUID = 123L;
	
	public int diceNumber;
	
	public int diceId;

	public DiceTO() {
	}

	public DiceTO(int diceNumber, int diceId) {
		super();
		this.diceNumber = diceNumber;
		this.diceId = diceId;
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
