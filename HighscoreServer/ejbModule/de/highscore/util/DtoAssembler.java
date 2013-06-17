package de.highscore.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.highscore.dto.HighscoreTO;
import de.highscore.entities.Highscore;


@Stateless
public class DtoAssembler {
  
	public HighscoreTO makeDTO(Highscore highscore){
		HighscoreTO dto = new HighscoreTO(highscore.getUsername(), highscore.getPoints());
		return dto;
	}
	
	public ArrayList <HighscoreTO> makeDTO(ArrayList<Highscore> highscoreList){
		  ArrayList<HighscoreTO> dtoList = new ArrayList<>();
		  for (Highscore h : highscoreList) {
			  dtoList.add(this.makeDTO(h));
		  }
		  return dtoList;
	}
	
//  public GameTO makeDTO(Game game) {
//	  GameTO dto = new GameTO();
//	  dto.setId(game.getId());
//	  dto.setOwner(game.getOwner());
//	  dto.setSlots(game.getSlots());
//	  dto.setSpieler1(game.getSpieler1());
//	  dto.setSpieler2(game.getSpieler2());
//	  dto.setSpieler3(game.getSpieler3());
//	  dto.setSpieler4(game.getSpieler4());
//	  return dto;
//  }
//  
//  public ArrayList<GameTO> makeDTO(ArrayList<Game> gameList) {
//	  ArrayList<GameTO> dtoList = new ArrayList<>();
//	  for (Game g : gameList) {
//		  dtoList.add(this.makeDTO(g));
//	  }
//	  return dtoList;
//  }
//  
//  public CustomerTO makeDTO(CustomerTO customer) {
//	  CustomerTO dto = new CustomerTO();
//	  dto.setId(customer.getId());
//	  dto.setPassword(customer.getPassword());
//	  dto.setUserName(customer.getUserName());
//	  return dto;
//  }
}
