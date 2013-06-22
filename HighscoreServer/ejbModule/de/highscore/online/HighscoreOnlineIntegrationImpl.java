package de.highscore.online;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.highscore.dao.HighscoreDAOLocal;
//import de.highscore.dao.HighscoreDAOLocal;
import de.highscore.dto.HighscoreListResponse;
import de.highscore.dto.HighscoreTO;
//import de.highscore.entities.Highscore;
//import de.highscore.util.DtoAssembler;
import de.highscore.entities.Highscore;




@Stateless
@Remote(HighscoreOnlineIntegration.class)
public class HighscoreOnlineIntegrationImpl implements HighscoreOnlineIntegration{

//	@EJB
//	private DtoAssembler dtoAssembler;
	
	@EJB(beanName = "HighscoreDAO", beanInterface = de.highscore.dao.HighscoreDAO.class)
	private HighscoreDAOLocal dao;

	
	@Override
	public int getCredits(String username) {
		int credits = this.dao.getCredits(username);
		
		return credits;
	}

	@Override
	public ArrayList<Highscore> getTopTwenty() {
		
		ArrayList<Highscore> highscoreList = (ArrayList<Highscore>) this.dao.getTopTwenty();
		
		return highscoreList;
		
		
	}

	@Override
	public void setHighscore(String user, int credits) {
		dao.setHighscore(user, credits);
		
	}

	
	
}
