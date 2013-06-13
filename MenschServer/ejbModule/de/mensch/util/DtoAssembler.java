package de.mensch.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.mensch.dto.AccountTO;
import de.mensch.dto.CustomerTO;
import de.mensch.dto.DiceResponse;
import de.mensch.dto.DiceTO;
import de.mensch.dto.GameTO;
import de.mensch.entities.*;

@Stateless
public class DtoAssembler {
  
  public GameTO makeDTO(Game game) {
	  GameTO dto = new GameTO();
	  dto.setId(game.getId());
	  dto.setOwnerId(game.getId());
	  dto.setSlots(game.getSlots());
	  return dto;
  }
  
  public ArrayList<GameTO> makeDTO(ArrayList<Game> gameList) {
	  ArrayList<GameTO> dtoList = new ArrayList<>();
	  for (Game g : gameList) {
		  dtoList.add(this.makeDTO(g));
	  }
	  return dtoList;
  }
  
  public CustomerTO makeDTO(CustomerTO customer) {
	  CustomerTO dto = new CustomerTO();
	  dto.setId(customer.getId());
	  dto.setPassword(customer.getPassword());
	  dto.setUserName(customer.getUserName());
	  return dto;
  }
}
