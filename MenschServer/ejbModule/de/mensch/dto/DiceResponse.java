package de.mensch.dto;

import java.util.ArrayList;

public class DiceResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3173145682328L;
	
	private ArrayList<DiceTO> diceList;
	
	public DiceResponse() {
		// TODO Auto-generated constructor stub
	}

	public void setDiceList(Object makeDiceDTO) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<DiceTO> getDiceList() {
		return diceList;
	}

	public void setGameList(ArrayList<DiceTO> dice) {
		this.diceList = dice;
	}
}
