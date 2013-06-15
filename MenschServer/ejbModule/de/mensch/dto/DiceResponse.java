package de.mensch.dto;

public class DiceResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3173158310918408228L;

	private double diceNumber;
	
	public DiceResponse() {
		// TODO Auto-generated constructor stub
	}

	public void setDiceNumber(double random) {
		this.diceNumber = random;
		
	}
	
	public double getDiceNumber() {
		return diceNumber;
		
	}

}
