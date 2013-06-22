package de.highscore.dto;

public class HighscoreTO {

	private static final long serialVersionUID = 1L;
	
	String username;
	int credits;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getcredits() {
		return credits;
	}
	public void setcredits(int credits) {
		this.credits = credits;
	}
	public HighscoreTO(String username, int credits) {
		super();
		this.username = username;
		this.credits = credits;
	}
	

}
