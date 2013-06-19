package de.mensch.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import de.mensch.entities.Account;
import de.mensch.entities.Customer;
import de.mensch.entities.Game;
import de.mensch.entities.MenschSession;
import de.mensch.entities.Request;

@Local
public interface MenschDAOLocal {

	public Customer findCustomerByName(String userName);
	
	public Account findAccountById(int id);
	
	public MenschSession findSessionById(int id);
	
	public int createSession(Customer user);

	public void closeSession(int id);
	
	public Customer registerCustomer(String username, String password);

	public Game getGame(int gameid);
	
	public void removeGame(int gameid);
	
	public ArrayList<Game> getGameList();

	public Game getGameDetails(int id);

	public Game getGameFields(int id);

	public Game createGame(Customer owner);

	public ArrayList<Request> getRequests(int gameid);
	
	public Request getRequest(int id);

	public Request createRequest(int id, String username);

	public Game findGameByOwnerUserName(Customer owner);

	public void removeRequest(int requestId);

		
}
