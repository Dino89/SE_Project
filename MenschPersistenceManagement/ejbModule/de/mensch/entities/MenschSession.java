package de.mensch.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MenschSession {

	@Id @GeneratedValue
	private int id;
	private String username;
	private Date creationTime;
	
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

}
