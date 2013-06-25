package de.mensch.highscore;

import javax.persistence.Id;

/**
 * 
 * @author Christopher
 *
 */
public class Highscore {
	private static final long serialVersionUID = 1L;

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
	
	public String toString() {
		return (this.username + " - " + this.credits);
	}


}
