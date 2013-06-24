package de.mensch.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Field implements Serializable{
	
	@Id @GeneratedValue
	int id;
	
	private static final long serialVersionUID = 12L;
	
	int state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public Field (){
		
	}

}
