package de.mensch.dto;

import java.util.ArrayList;
import java.util.List;

import de.mensch.entities.Game;

public class GameFieldResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3173158310918408228L;

	private GameTO gameFields;
	
	public GameFieldResponse() {
		// TODO Auto-generated constructor stub
	}

	public void setGameFields(GameTO games) {
		this.gameFields = gameFields;
	}
	
	public GameTO getGameFields(int id) {
		return gameFields;
	}
}
