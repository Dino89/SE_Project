package de.highscore.online;
import java.util.ArrayList;

import de.highscore.dto.HighscoreListResponse;
import de.highscore.dto.HighscoreTO;


public interface HighscoreOnlineIntegration {

	HighscoreTO getScore(String username);
	HighscoreListResponse getTopTwenty();
	
}
