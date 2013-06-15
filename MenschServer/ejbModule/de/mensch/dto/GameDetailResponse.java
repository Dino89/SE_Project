package de.mensch.dto;

import java.util.ArrayList;
import java.util.List;

import de.mensch.entities.Game;

public class GameDetailResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -317315831091403428L;

	private GameTO gameDetail;
	
	public GameDetailResponse() {
		// TODO Auto-generated constructor stub
	}

	public void setGameDetails(GameTO games) {
		this.gameDetail = games;
	}
	
	public GameTO getGameDetails(int id) {
		return gameDetail;
	}
}
