package de.highscore.dto;

import java.util.List;

public class HighscoreListResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -5754928488884226775L;

	private List<HighscoreTO> highscoreList;
	
	public HighscoreListResponse() {
		// TODO Auto-generated constructor stub
	}

	public List<HighscoreTO> getHighscoreList() {
		return highscoreList;
	}

	public void setHighscoreList(List<HighscoreTO> highscoreList) {
		this.highscoreList = highscoreList;
	}

}
