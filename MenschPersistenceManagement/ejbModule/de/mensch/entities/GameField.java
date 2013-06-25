package de.mensch.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class GameField implements Serializable {
	
	private static final long serialVersionUID = 10L;
	
	@Id @GeneratedValue
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Game game;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Field> fields;

	public GameField() {
		fields = new ArrayList<>();
	}
	public void init(){
		for(int i=0; i<=39;i++) {
			Field f = new Field();
			f.setState(0);
			
			fields.add(f);
		}
		field_blue_1 = 1;
		field_blue_2 = 1;
		field_blue_3 = 1;
		field_blue_4 = 1;
		
		field_red_1 = 2;
		field_red_2 = 2;
		field_red_3 = 2;
		field_red_4 = 2;
		
		field_green_1 = 3;
		field_green_2 = 3;
		field_green_3 = 3;
		field_green_4 = 3;
		
		field_yellow_1 = 4;
		field_yellow_2 = 4;
		field_yellow_3 = 4;
		field_yellow_4 = 4;
	}
	
	public int getSize() {
		return this.fields.size();
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public void setField_blue_1(int field_blue_1) {
		this.field_blue_1 = field_blue_1;
	}
	public void setField_blue_2(int field_blue_2) {
		this.field_blue_2 = field_blue_2;
	}
	public void setField_blue_3(int field_blue_3) {
		this.field_blue_3 = field_blue_3;
	}
	public void setField_blue_4(int field_blue_4) {
		this.field_blue_4 = field_blue_4;
	}
	public void setField_red_1(int field_red_1) {
		this.field_red_1 = field_red_1;
	}
	public void setField_red_2(int field_red_2) {
		this.field_red_2 = field_red_2;
	}
	public void setField_red_3(int field_red_3) {
		this.field_red_3 = field_red_3;
	}
	public void setField_red_4(int field_red_4) {
		this.field_red_4 = field_red_4;
	}
	public void setField_green_1(int field_green_1) {
		this.field_green_1 = field_green_1;
	}
	public void setField_green_2(int field_green_2) {
		this.field_green_2 = field_green_2;
	}
	public void setField_green_3(int field_green_3) {
		this.field_green_3 = field_green_3;
	}
	public void setField_green_4(int field_green_4) {
		this.field_green_4 = field_green_4;
	}
	public void setField_yellow_1(int field_yellow_1) {
		this.field_yellow_1 = field_yellow_1;
	}
	public void setField_yellow_2(int field_yellow_2) {
		this.field_yellow_2 = field_yellow_2;
	}
	public void setField_yellow_3(int field_yellow_3) {
		this.field_yellow_3 = field_yellow_3;
	}
	public void setField_yellow_4(int field_yellow_4) {
		this.field_yellow_4 = field_yellow_4;
	}
	public void setField_blue_house_1(int field_blue_house_1) {
		this.field_blue_house_1 = field_blue_house_1;
	}
	public void setField_blue_house_2(int field_blue_house_2) {
		this.field_blue_house_2 = field_blue_house_2;
	}
	public void setField_blue_house_3(int field_blue_house_3) {
		this.field_blue_house_3 = field_blue_house_3;
	}
	public void setField_blue_house_4(int field_blue_house_4) {
		this.field_blue_house_4 = field_blue_house_4;
	}
	public void setField_red_house_1(int field_red_house_1) {
		this.field_red_house_1 = field_red_house_1;
	}
	public void setField_red_house_2(int field_red_house_2) {
		this.field_red_house_2 = field_red_house_2;
	}
	public void setField_red_house_3(int field_red_house_3) {
		this.field_red_house_3 = field_red_house_3;
	}
	public void setField_red_house_4(int field_red_house_4) {
		this.field_red_house_4 = field_red_house_4;
	}
	public void setField_green_house_1(int field_green_house_1) {
		this.field_green_house_1 = field_green_house_1;
	}
	public void setField_green_house_2(int field_green_house_2) {
		this.field_green_house_2 = field_green_house_2;
	}
	public void setField_green_house_3(int field_green_house_3) {
		this.field_green_house_3 = field_green_house_3;
	}
	public void setField_green_house_4(int field_green_house_4) {
		this.field_green_house_4 = field_green_house_4;
	}
	public void setField_yellow_house_1(int field_yellow_house_1) {
		this.field_yellow_house_1 = field_yellow_house_1;
	}
	public void setField_yellow_house_2(int field_yellow_house_2) {
		this.field_yellow_house_2 = field_yellow_house_2;
	}
	public void setField_yellow_house_3(int field_yellow_house_3) {
		this.field_yellow_house_3 = field_yellow_house_3;
	}
	public void setField_yellow_house_4(int field_yellow_house_4) {
		this.field_yellow_house_4 = field_yellow_house_4;
	}
	public int getField_blue_1() {
		return field_blue_1;
	}
	public int getField_blue_2() {
		return field_blue_2;
	}
	public int getField_blue_3() {
		return field_blue_3;
	}
	public int getField_blue_4() {
		return field_blue_4;
	}
	public int getField_red_1() {
		return field_red_1;
	}
	public int getField_red_2() {
		return field_red_2;
	}
	public int getField_red_3() {
		return field_red_3;
	}
	public int getField_red_4() {
		return field_red_4;
	}
	public int getField_green_1() {
		return field_green_1;
	}
	public int getField_green_2() {
		return field_green_2;
	}
	public int getField_green_3() {
		return field_green_3;
	}
	public int getField_green_4() {
		return field_green_4;
	}
	public int getField_yellow_1() {
		return field_yellow_1;
	}
	public int getField_yellow_2() {
		return field_yellow_2;
	}
	public int getField_yellow_3() {
		return field_yellow_3;
	}
	public int getField_yellow_4() {
		return field_yellow_4;
	}
	public int getField_blue_house_1() {
		return field_blue_house_1;
	}
	public int getField_blue_house_2() {
		return field_blue_house_2;
	}
	public int getField_blue_house_3() {
		return field_blue_house_3;
	}
	public int getField_blue_house_4() {
		return field_blue_house_4;
	}
	public int getField_red_house_1() {
		return field_red_house_1;
	}
	public int getField_red_house_2() {
		return field_red_house_2;
	}
	public int getField_red_house_3() {
		return field_red_house_3;
	}
	public int getField_red_house_4() {
		return field_red_house_4;
	}
	public int getField_green_house_1() {
		return field_green_house_1;
	}
	public int getField_green_house_2() {
		return field_green_house_2;
	}
	public int getField_green_house_3() {
		return field_green_house_3;
	}
	public int getField_green_house_4() {
		return field_green_house_4;
	}
	public int getField_yellow_house_1() {
		return field_yellow_house_1;
	}
	public int getField_yellow_house_2() {
		return field_yellow_house_2;
	}
	public int getField_yellow_house_3() {
		return field_yellow_house_3;
	}
	public int getField_yellow_house_4() {
		return field_yellow_house_4;
	}
}
