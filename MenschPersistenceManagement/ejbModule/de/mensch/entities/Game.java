package de.mensch.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.xml.bind.annotation.XmlTransient;


@Entity
public class Game implements Serializable {
	
	private static final long serialVersionUID = 11L;
	
	@Id @GeneratedValue
	private int id;
	
	private int slots = 3;
	boolean started;
	
	private int diceNumber;
	
	@OneToOne
	private Customer owner;
	@OneToOne
	private Customer spieler1;
	@OneToOne
	private Customer spieler2;
	@OneToOne
	private Customer spieler3;
	@OneToOne
	private Customer spieler4;

	private String aktuellerSpieler;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="currentGame") @MapKey
	private Map<Integer, MenschSession> zuschauer;
	
	@OneToOne
	private GameField gameField;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="gameentity") @MapKey
	private java.util.Map<Integer,Request> requests;

	private int wuerfelCount;
	
	public Game() {
		super();
		this.started = false;
		aktuellerSpieler = "spieler1";
		spieler2 = null;
		spieler3 = null;
		spieler4 = null;
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
	
	public void setId(int id) {
		this.id = id;
	}

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

	/**
	 * @return the gameField
	 */
	public GameField getGameField() {
		return gameField;
	}

	/**
	 * @param gameField the gameField to set
	 */
	public void setGameField(GameField gameField) {
		this.gameField = gameField;
	}

	public Map<Integer, MenschSession> getZuschauer() {
		return zuschauer;
	}

	public void setZuschauer(Map<Integer, MenschSession> zuschauer) {
		this.zuschauer = zuschauer;
	}

	/**
	 * @return the aktuellerSpieler
	 */
	public String getAktuellerSpieler() {
		return aktuellerSpieler;
	}

	/**
	 * @param aktuellerSpieler the aktuellerSpieler to set
	 */
	public void setAktuellerSpieler(String aktuellerSpieler) {
		this.aktuellerSpieler = aktuellerSpieler;
	}

	/**
	 * @return the diceNumber
	 */
	public int getDiceNumber() {
		return diceNumber;
	}

	/**
	 * @param diceNumber the diceNumber to set
	 */
	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}

	/**
	 * @return the wuerfelCount
	 */
	public int getWuerfelCount() {
		return wuerfelCount;
	}

	/**
	 * @param wuerfelCount the wuerfelCount to set
	 */
	public void setWuerfelCount(int wuerfelCount) {
		this.wuerfelCount = wuerfelCount;
	}

}
