package de.mensch.dto;

import java.util.ArrayList;
import java.util.List;

import de.mensch.entities.Game;

public class GameDetailResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -317315831091403428L;

	private GameTO gameDetail  = new GameTO();
	
	public GameDetailResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public GameTO getGameDetails(int id) {
		return gameDetail;
	}
	
	public void setGameDetails(GameTO game) {
		this.gameDetail = game;
	}

}
