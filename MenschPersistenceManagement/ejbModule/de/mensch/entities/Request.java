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
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Request implements Serializable {
	
	private static final long serialVersionUID = 11L;
	
	@Id @GeneratedValue
	private int id;
	@ManyToOne
	private Game gameentity;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	boolean success = false;
	private String userName;
	private String state="not answered";
	
	public Request() {
		super();
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Game getGameentity() {
		return gameentity;
	}
	//TODO: NOT FINISHED
	public void setGameentity(Game game) {
		this.gameentity = game;
		
	}

	public String getUser() {
		return userName;
	}

	public void setUser(String user) {
		this.userName = user;
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
