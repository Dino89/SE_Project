package de.highscore.dao;
import java.util.ArrayList;

import de.highscore.entities.*;
public interface HighscoreDAOLocal {

	ArrayList<?> getTopTwenty();
	int getCredits(String username);
	Highscore setHighscore(String username, Integer credits);
	
}
