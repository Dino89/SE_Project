package de.mensch.dto;

public class DiceResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3173145682328L;

	private int diceNumber;

	private int diceId;
	
	public DiceResponse() {
		// TODO Auto-generated constructor stub
	}

	public void setDiceNumber(int random) {
		this.diceNumber = random;
		
	}
	
	public double getDiceNumber() {
		return diceNumber;
		
	}

	public void setDiceId(int id) {
		this.diceId = id;		
	}

	/**
	 * @return the diceId
	 */
	public int getDiceId() {
		return diceId;
	}
	
}
