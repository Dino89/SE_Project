package com.example.menschapp.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Games implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int slots;
	private int success;
	private boolean started;
	private int diceNumber;
	private String spieler1;
	private String spieler2;
	private String spieler3;
	private String spieler4;
	private ArrayList<String> zuschauer;
	private ArrayList<Integer> spielfeld;
	
	private String owner;
	
	private String stateMessage;
	
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
		return owner+"'s Spiel. Slots: "+slots+"/4";
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSlots() {
		return slots;
	}

	
	public String getStateMessage() {
		return stateMessage;
	}

	public void setStateMessage(String stateMessage) {
		this.stateMessage = stateMessage;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public String getSpieler1() {
		return spieler1;
	}

	public void setSpieler1(String spieler1) {
		this.spieler1 = spieler1;
	}

	public String getSpieler2() {
		return spieler2;
	}

	public void setSpieler2(String spieler2) {
		this.spieler2 = spieler2;
	}

	public String getSpieler3() {
		return spieler3;
	}

	public void setSpieler3(String spieler3) {
		this.spieler3 = spieler3;
	}

	public String getSpieler4() {
		return spieler4;
	}

	public void setSpieler4(String spieler4) {
		this.spieler4 = spieler4;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public ArrayList<String> getZuschauer() {
		return zuschauer;
	}

	public void setZuschauer(ArrayList<String> zuschauer) {
		this.zuschauer = zuschauer;
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

	public int getDiceNumber() {
		return diceNumber;
	}

	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}

	public ArrayList<Integer> getSpielfeld() {
		return spielfeld;
	}

	public void setSpielfeld(ArrayList<Integer> spielfeld) {
		this.spielfeld = spielfeld;
	}


	
	
	private int field_blue_1;
	private int field_blue_2;
	private int field_blue_3;
	private int field_blue_4;
	
	private int field_red_1;
	private int field_red_2;
	private int field_red_3;
	private int field_red_4;
	
	private int field_green_1;
	private int field_green_2;
	private int field_green_3;
	private int field_green_4;
	
	private int field_yellow_1;
	private int field_yellow_2;
	private int field_yellow_3;
	private int field_yellow_4;
	
	private int field_blue_house_1;
	private int field_blue_house_2;
	private int field_blue_house_3;
	private int field_blue_house_4;
	
	private int field_red_house_1;
	private int field_red_house_2;
	private int field_red_house_3;
	private int field_red_house_4;
	
	private int field_green_house_1;
	private int field_green_house_2;
	private int field_green_house_3;
	private int field_green_house_4;
	
	private int field_yellow_house_1;
	private int field_yellow_house_2;
	private int field_yellow_house_3;
	private int field_yellow_house_4;

	public int getField_blue_1() {
		return field_blue_1;
	}

	public void setField_blue_1(int field_blue_1) {
		this.field_blue_1 = field_blue_1;
	}

	public int getField_blue_2() {
		return field_blue_2;
	}

	public void setField_blue_2(int field_blue_2) {
		this.field_blue_2 = field_blue_2;
	}

	public int getField_blue_3() {
		return field_blue_3;
	}

	public void setField_blue_3(int field_blue_3) {
		this.field_blue_3 = field_blue_3;
	}

	public int getField_blue_4() {
		return field_blue_4;
	}

	public void setField_blue_4(int field_blue_4) {
		this.field_blue_4 = field_blue_4;
	}

	public int getField_red_1() {
		return field_red_1;
	}

	public void setField_red_1(int field_red_1) {
		this.field_red_1 = field_red_1;
	}

	public int getField_red_2() {
		return field_red_2;
	}

	public void setField_red_2(int field_red_2) {
		this.field_red_2 = field_red_2;
	}

	public int getField_red_3() {
		return field_red_3;
	}

	public void setField_red_3(int field_red_3) {
		this.field_red_3 = field_red_3;
	}

	public int getField_red_4() {
		return field_red_4;
	}

	public void setField_red_4(int field_red_4) {
		this.field_red_4 = field_red_4;
	}

	public int getField_green_1() {
		return field_green_1;
	}

	public void setField_green_1(int field_green_1) {
		this.field_green_1 = field_green_1;
	}

	public int getField_green_2() {
		return field_green_2;
	}

	public void setField_green_2(int field_green_2) {
		this.field_green_2 = field_green_2;
	}

	public int getField_green_3() {
		return field_green_3;
	}

	public void setField_green_3(int field_green_3) {
		this.field_green_3 = field_green_3;
	}

	public int getField_green_4() {
		return field_green_4;
	}

	public void setField_green_4(int field_green_4) {
		this.field_green_4 = field_green_4;
	}

	public int getField_yellow_1() {
		return field_yellow_1;
	}

	public void setField_yellow_1(int field_yellow_1) {
		this.field_yellow_1 = field_yellow_1;
	}

	public int getField_yellow_2() {
		return field_yellow_2;
	}

	public void setField_yellow_2(int field_yellow_2) {
		this.field_yellow_2 = field_yellow_2;
	}

	public int getField_yellow_3() {
		return field_yellow_3;
	}

	public void setField_yellow_3(int field_yellow_3) {
		this.field_yellow_3 = field_yellow_3;
	}

	public int getField_yellow_4() {
		return field_yellow_4;
	}

	public void setField_yellow_4(int field_yellow_4) {
		this.field_yellow_4 = field_yellow_4;
	}

	public int getField_blue_house_1() {
		return field_blue_house_1;
	}

	public void setField_blue_house_1(int field_blue_house_1) {
		this.field_blue_house_1 = field_blue_house_1;
	}

	public int getField_blue_house_2() {
		return field_blue_house_2;
	}

	public void setField_blue_house_2(int field_blue_house_2) {
		this.field_blue_house_2 = field_blue_house_2;
	}

	public int getField_blue_house_3() {
		return field_blue_house_3;
	}

	public void setField_blue_house_3(int field_blue_house_3) {
		this.field_blue_house_3 = field_blue_house_3;
	}

	public int getField_blue_house_4() {
		return field_blue_house_4;
	}

	public void setField_blue_house_4(int field_blue_house_4) {
		this.field_blue_house_4 = field_blue_house_4;
	}

	public int getField_red_house_1() {
		return field_red_house_1;
	}

	public void setField_red_house_1(int field_red_house_1) {
		this.field_red_house_1 = field_red_house_1;
	}

	public int getField_red_house_2() {
		return field_red_house_2;
	}

	public void setField_red_house_2(int field_red_house_2) {
		this.field_red_house_2 = field_red_house_2;
	}

	public int getField_red_house_3() {
		return field_red_house_3;
	}

	public void setField_red_house_3(int field_red_house_3) {
		this.field_red_house_3 = field_red_house_3;
	}

	public int getField_red_house_4() {
		return field_red_house_4;
	}

	public void setField_red_house_4(int field_red_house_4) {
		this.field_red_house_4 = field_red_house_4;
	}

	public int getField_green_house_1() {
		return field_green_house_1;
	}

	public void setField_green_house_1(int field_green_house_1) {
		this.field_green_house_1 = field_green_house_1;
	}

	public int getField_green_house_2() {
		return field_green_house_2;
	}

	public void setField_green_house_2(int field_green_house_2) {
		this.field_green_house_2 = field_green_house_2;
	}

	public int getField_green_house_3() {
		return field_green_house_3;
	}

	public void setField_green_house_3(int field_green_house_3) {
		this.field_green_house_3 = field_green_house_3;
	}

	public int getField_green_house_4() {
		return field_green_house_4;
	}

	public void setField_green_house_4(int field_green_house_4) {
		this.field_green_house_4 = field_green_house_4;
	}

	public int getField_yellow_house_1() {
		return field_yellow_house_1;
	}

	public void setField_yellow_house_1(int field_yellow_house_1) {
		this.field_yellow_house_1 = field_yellow_house_1;
	}

	public int getField_yellow_house_2() {
		return field_yellow_house_2;
	}

	public void setField_yellow_house_2(int field_yellow_house_2) {
		this.field_yellow_house_2 = field_yellow_house_2;
	}

	public int getField_yellow_house_3() {
		return field_yellow_house_3;
	}

	public void setField_yellow_house_3(int field_yellow_house_3) {
		this.field_yellow_house_3 = field_yellow_house_3;
	}

	public int getField_yellow_house_4() {
		return field_yellow_house_4;
	}

	public void setField_yellow_house_4(int field_yellow_house_4) {
		this.field_yellow_house_4 = field_yellow_house_4;
	}
	
	
}
