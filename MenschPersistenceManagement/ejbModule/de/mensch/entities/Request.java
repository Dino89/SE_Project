package de.mensch.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Request implements Serializable {
	
	private static final long serialVersionUID = 11L;
	
	@Id @GeneratedValue
	private int id;
	@ManyToOne
	private Game request;
	
	boolean success = false;
	private String user;
	
	public Request() {
		super();
	}
	
	public Game getRequest(int id) {
		return request;
	}
	//TODO: NOT FINISHED
	public Game createRequest(Game game) {
		this.request = game;
		return null;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
