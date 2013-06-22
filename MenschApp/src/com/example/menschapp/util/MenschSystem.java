package com.example.menschapp.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author SE_Project
 * Dieses Interface definiert die Schnittstelle zwischen Spiele-Client und -Server.
 * Es wird sowohl als Local- als auch als Remote-Business-Interface benutzt. 
 */

public interface MenschSystem {
	
	/**
	 * Operation zum Ausloggen. Schliesst die Session des Nutzers.
	 * @param sessionID
	 */
	public void logout();


	public double diceNumber();
	

	public String register(String username, String password);

	
	/**
	 * Operation zum Einloggen mit Username und Password.
	 * @param username
	 * @param password
	 * @return
	 * @throws PasswortUngueltigException 
	 * @throws UsernameUngueltigException 
	 */


	public Kunde login(String username, String password);


	public ArrayList<Games> getGames();


	public Games getGameDetails(int id);


	public Games createGame();


	public Request joinGame(int id);
	
	public void leaveGame(int id);

	//public Response joinGameResponse();


	public void getGameFields(int gameid);


	public String checkMyRequest(int requestId);


	public ArrayList<Request> checkForRequests(int gameId);


	public void allowPlayer(int id);


	public void declinePlayer(int id);


	public void spectateGame(int gameid);
	
	public int getSessionId();

	void startGame(int id);
}
