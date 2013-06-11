package de.mensch.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import de.mensch.entities.Account;
import de.mensch.entities.Customer;
import de.mensch.entities.Game;
import de.mensch.entities.MenschSession;

@Local
public interface MenschDAOLocal {

	public Customer findCustomerByName(String userName);
	
	public Account findAccountById(int id);
	
	public MenschSession findSessionById(int id);
	
	public int createSession(Customer user);

	public void closeSession(int id);
	
	public Customer registerCustomer(String username, String password);

	public ArrayList<Game> getGameList();
		
}
