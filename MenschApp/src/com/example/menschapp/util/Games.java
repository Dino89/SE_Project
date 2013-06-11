package com.example.menschapp.util;

import java.io.Serializable;
import java.math.BigDecimal;

public class Games implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer kontoNr;
	private BigDecimal betrag;
	
	public Games() {
		super();
	}
	
	public Games(Integer kontoNr) {
		this.betrag = BigDecimal.ZERO;
		this.kontoNr = kontoNr;
	}
	
	public Integer getKontoNr() {
		return kontoNr;
	}
	
	public BigDecimal getBetrag() {
		return betrag;
	}
	
	public void increase(BigDecimal amount) {
		this.betrag = this.betrag.add(amount);
	}
	
	public void decrease(BigDecimal amount) {
		this.betrag = this.betrag.subtract(amount);
	}
	
	public String toString() {
		return "Konto " + this.kontoNr + " (Saldo=" + this.betrag + ")";
	}

	public void setKontoNr(Integer kontoNr) {
		this.kontoNr = kontoNr;
	}

	public void setBetrag(BigDecimal betrag) {
		this.betrag = betrag;
	}

	public void setGameId(Integer valueOf) {
		// TODO Auto-generated method stub
		
	}
}
