package de.mensch.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import de.mensch.entities.Customer;

import de.mensch.entities.GameField;

public class GameTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int slots;
	boolean started;
	private int diceNumber;
	
	private Customer owner;
	private Customer spieler1;
	private Customer spieler2;
	private Customer spieler3;
	private Customer spieler4;
	SpectatorListTO zuschauerListe;

	String stateMessage;
	
	private GameFieldTO gameField;
	
	public GameTO() {
	}

	public GameTO(int id, int slots, Customer owner) {
		super();
		this.id = id;
		this.slots = slots;
		this.owner = owner;
	}


	public String toString() {
		return "Game " + this.id + " Slots=" + this.slots + ", Owner=" + this.getOwner().getUserName();
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
	
	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	
		/**
	 * @return the started
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * @param started the started to set
	 */
	public void setStarted(boolean started) {
		this.started = started;
	}

	public SpectatorListTO getSpectatorListTO() {
		return zuschauerListe;
	}

	public void setSpectatorListTO(SpectatorListTO SpectatorListTO) {
		this.zuschauerListe = SpectatorListTO;
	}

	public int getDiceNumber() {
		return diceNumber;
	}

	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}

	public GameFieldTO getGameField() {
		return gameField;
	}

	public void setGameField(GameFieldTO gameField) {
		this.gameField = gameField;
	}

	public String getStateMessage() {
		return stateMessage;
	}

	public void setStateMessage(String stateMessage) {
		this.stateMessage = stateMessage;
	}
	
	
}
