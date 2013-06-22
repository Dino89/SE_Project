package de.highscore.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Highscore implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String username;

	private int credits;
	
	
	public Highscore(String username, int credits) {
		super();
		this.username = username;
		this.credits = credits;
	}

	private Highscore() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	public Highscore(String username){
		super();
		this.username = username;
		
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	

}
