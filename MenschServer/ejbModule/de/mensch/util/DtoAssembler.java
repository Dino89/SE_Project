package de.mensch.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;


import de.mensch.dto.CustomerTO;
import de.mensch.dto.DiceResponse;
import de.mensch.dto.DiceTO;
import de.mensch.dto.GameTO;
import de.mensch.dto.RequestTO;
import de.mensch.dto.SpectatorListTO;
import de.mensch.entities.*;

@Stateless
public class DtoAssembler {

	public RequestTO makeDTO(Request request) {
		RequestTO dto = new RequestTO();
		dto.setUserName(request.getUser());
		System.out.println("REQUESTID: "+request.getId());
		dto.setId(request.getId());
		return dto;
	}
	
	public ArrayList<RequestTO> makeDTORequestList(ArrayList<Request> requestList) {
		ArrayList<RequestTO> dtoList = new ArrayList<>();
		for(Request r : requestList) {
			r.setPulledByHost(true);
			dtoList.add(this.makeDTO(r));
		}
		return dtoList;
	}
	
  public GameTO makeDTO(Game game) {
	  GameTO dto = new GameTO();
	  dto.setId(game.getId());
	  dto.setOwner(game.getOwner());
	  dto.setSlots(game.getSlots());
	  dto.setSpieler1(game.getSpieler1());
	  dto.setSpieler2(game.getSpieler2());
	  dto.setSpieler3(game.getSpieler3());
	  dto.setSpieler4(game.getSpieler4());
	  dto.getOwner().setPassword(null);
	  dto.setStarted(game.isStarted());
	  dto.setDiceNumber((int) Math.round(Math.random()*100%7));

	  ArrayList<Zuschauer> dtoZuschauer = new ArrayList<Zuschauer>();

	  SpectatorListTO kapsel = new SpectatorListTO();
	  kapsel.setZuschauer(dtoZuschauer);
	  dto.setSpectatorListTO(kapsel);  
	  dto.setGameField(game.getGameField());
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
	  dto.setUserName(customer.getUserName());
	  return dto;
  }

//  public Object makeDiceDTO(ArrayList<Dice> diceList) {
//	ArrayList<DiceTO> dtoList = new ArrayList<>();
//	for( Dice d: diceList) {
//		dtoList.add(this.makeDTO(d));
//	}
//	return dtoList;
//  }

//	public DiceTO makeDTO(Dice d) {
//		DiceTO dto = new DiceTO();
//		dto.setDiceId(dto.getDiceId());
//		dto.setDiceNumber((int) Math.round(Math.random()*100%7));
//		return dto;
//	}


}
