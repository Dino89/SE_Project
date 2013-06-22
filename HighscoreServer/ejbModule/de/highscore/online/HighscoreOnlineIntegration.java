package de.highscore.online;
import java.util.ArrayList;

import de.highscore.dto.HighscoreListResponse;
import de.highscore.dto.HighscoreTO;
import de.highscore.entities.Highscore;


public interface HighscoreOnlineIntegration {

	int getCredits(String username);
	ArrayList<Highscore> getTopTwenty();
	void setHighscore(String user, int credits);
	
}
