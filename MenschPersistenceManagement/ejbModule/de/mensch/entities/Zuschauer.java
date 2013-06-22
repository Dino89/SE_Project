package de.mensch.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Zuschauer implements Serializable {
	
	private static final long serialVersionUID = 187656L;
	
	@Id
	private int id;
	
	@OneToOne
	private Customer zuschauer;
	
	@OneToOne
	private Game game;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getZuschauer() {
		return zuschauer;
	}

	public void setZuschauer(Customer zuschauer) {
		this.zuschauer = zuschauer;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
}
