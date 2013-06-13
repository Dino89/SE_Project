package de.mensch.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class GameTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int slots;
	private int ownerId;
	
	
	public GameTO() {
	}

	public GameTO(int id, int slots, int ownerId) {
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


	public int getSlots() {
		return slots;
	}


	public void setSlots(int i) {
		this.slots = i;
	}


	public int getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
}
