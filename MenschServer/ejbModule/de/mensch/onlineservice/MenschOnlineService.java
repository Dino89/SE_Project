package de.mensch.onlineservice;

import java.math.BigDecimal;
import java.util.List;


/**
 * Dieses Business Interface definiert die Schnittstelle zum OnlineSystem.
 * 
 * @author   
 */
public interface MenschOnlineService {
	
	/**
	 * Operation zum Einloggen mit Username und Password.
	 * @param request
	 * @return
	 */
	public boolean login(String username, String password) throws InvalidLoginException;
	
	/**
	 * Operation zum Ausloggen. Schliesst die Session des Nutzers.
	 * @throws NoSessionException
	 */
	public void logout() throws NoSessionException;
	
	public boolean diceNumber();
	
}
