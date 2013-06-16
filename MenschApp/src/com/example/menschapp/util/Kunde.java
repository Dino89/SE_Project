package com.example.menschapp.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Kunde implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String passwort;
	
	public Kunde() {
		super();
	}
	
	public Kunde(String userName, String passwort) {
		this.userName = userName;
		this.passwort = passwort;
//		this.eigeneKonten = new HashMap<Integer,Konto>();
	}
	
//	public void addEigenesKonto(Konto neuesKonto) {
//		this.eigeneKonten.put(neuesKonto.getKontoNr(), neuesKonto);
//	}

	public String getUserName() {
		return userName;
	}

	public String getPasswort() {
		return passwort;
	}

//	public Konto getEigenesKonto(Integer kontoNr) {
//		return eigeneKonten.get(kontoNr);
//	}

//	public Map<Integer,Konto> getEigeneKonten() {
//		return this.eigeneKonten;
//	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

//	public void setEigeneKonten(Map<Integer, Konto> eigeneKonten) {
//		this.eigeneKonten = eigeneKonten;
//	}
	
	public String toString() {
		return "Kunde: " + this.getUserName() +"/" + this.getPasswort();
	}
}
