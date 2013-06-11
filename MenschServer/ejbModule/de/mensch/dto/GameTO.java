package de.mensch.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class GameTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private BigDecimal slots;
	private int ownerId;
	
	
	public GameTO() {
	}

	public GameTO(int id, BigDecimal slots, int ownerId) {
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


	public BigDecimal getSlots() {
		return slots;
	}


	public void setSlots(BigDecimal slots) {
		this.slots = slots;
	}


	public int getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
}
