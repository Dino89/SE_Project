package de.mensch.dto;

import java.util.ArrayList;
import java.util.List;

import de.mensch.entities.Game;

public class GameListResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3173158310918408228L;

	private ArrayList<GameTO> gameList;
	
	public GameListResponse() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<GameTO> getGameList() {
		return gameList;
	}

	public void setGameList(ArrayList<GameTO> games) {
		this.gameList = games;
	}
}
