package de.highscore.dto;

public class HighscoreTO {

	private static final long serialVersionUID = 1L;
	
	String username;
	int points;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public HighscoreTO(String username, int points) {
		super();
		this.username = username;
		this.points = points;
	}
	

}
