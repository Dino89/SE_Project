package de.mensch.dto;

public class DiceResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3173158310918408228L;

	private String diceNumber;
	
	public DiceResponse() {
		// TODO Auto-generated constructor stub
	}

	public void setDiceNumber(String diceNumber) {
		this.diceNumber = diceNumber;
		
	}
	
	public String getDiceNumber() {
		return diceNumber;
		
	}
}
