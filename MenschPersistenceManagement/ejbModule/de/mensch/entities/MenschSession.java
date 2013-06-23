package de.mensch.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class MenschSession implements Serializable{

	@Id @GeneratedValue
	private int id;
	private String username;
	private Date creationTime;
	@OneToOne(cascade = CascadeType.ALL)
	private Game currentGame;
	
	public MenschSession(Customer user) {
		this.username = user.getUserName();
		this.creationTime = new Date();
	}
	
	public MenschSession() {
		this.creationTime = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the currentGame
	 */
	public Game getCurrentGame() {
		return currentGame;
	}

	/**
	 * @param currentGame the currentGame to set
	 */
	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

}
