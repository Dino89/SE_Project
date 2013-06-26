package de.highscore.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Game implements Serializable {
	
	private static final long serialVersionUID = 1034567L;
	
	@Id@GeneratedValue
	private int id;
	@OneToOne
	private Highscore highscore;
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Highscore getHighscore() {
		return highscore;
	}

	public void setHighscore(Highscore highscore) {
		this.highscore = highscore;
	}
}
