/**
 * 
 */
package com.example.menschapp.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Malte
 *
 */
/*
 * int value 0 leeres feld
 * int value 1 blau
 * int value 2 rot
 * int value 3 grün
 * int value 4 gelb
 * 
 * int index 1 bis 4 startfelder blau
 * int index 5 bis 8 startfelder rot
 * int index 9 bis 12 startfelder grün
 * int index 13 bis 16 startfelder gelb
 * int index 17 bis 20 endfelder blau
 * int index 21 bis 24 endfelder rot
 * int index 25 bis 28 endfelder grün
 * int index 29 bis 32 endfelder gelb
 * int index 33 bis 72 spielfelder, startend mit dem blauen feld (auf der grafik field_1);
*/
public class GameField implements Serializable {
	
	private static final long serialVersionUID = 12L;
	
	private int id;
	
	private ArrayList<Integer> fields = new ArrayList<Integer>();
// TODO: HashMap? ArrayList?
	
	public void setField(int index, int value) {
		this.fields.set(index, value);
	}
	
	public int getField(int index) {
		return this.fields.get(index);
	}	
}
