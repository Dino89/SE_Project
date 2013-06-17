package de.highscore.entities;

import javax.persistence.Id;

public class Highscore {

	private static final long serialVersionUID = 1L;
	
	@Id
	String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	int points;
	
	public Highscore(String username){
		super();
		this.username = username;
		
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
