package de.highscore.online;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.highscore.dao.HighscoreDAOLocal;
import de.highscore.dto.HighscoreListResponse;
import de.highscore.dto.HighscoreTO;
import de.highscore.entities.Highscore;
import de.highscore.util.DtoAssembler;


@WebService
@Stateless
@Remote(HighscoreOnlineIntegration.class)

public class HighscoreOnlineIntegrationImpl implements HighscoreOnlineIntegration{

	@EJB
	private DtoAssembler dtoAssembler;
	
	@EJB(beanName = "HighscoreDAO", beanInterface = de.highscore.dao.HighscoreDAO.class)
	private HighscoreDAOLocal dao;

	
//	@Override
//	public HighscoreTO getScore(String username) {
//		Highscore h = this.dao.getScore(username);
//		return dtoAssembler.makeDTO(h);
//	}
//
//	@Override
//	public HighscoreListResponse getTopTwenty() {
//		HighscoreListResponse response = new HighscoreListResponse();
//		ArrayList<Highscore> highscoreList = this.dao.getTopTwenty();
//		response.setHighscoreList( dtoAssembler.makeDTO(highscoreList));
//		return response;
//		
//		
//	}

	
	
}
