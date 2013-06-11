package de.mensch.onlineservice;

import java.math.BigDecimal;

import de.mensch.dto.DiceResponse;
import de.mensch.dto.GameListResponse;
import de.mensch.dto.ReturncodeResponse;
import de.mensch.dto.UserLoginResponse;
import de.mensch.dto.UserRegisterResponse;

/**
 * Dieses Business Interface definiert die Schnittstelle zum OnlineSystem.
 * 
 * @author   
 */
public interface MenschOnlineIntegration {
	
	/**
	 * Operation zum Einloggen mit Username und Password.
	 */
	public UserLoginResponse login(String username, String password);
	
	/**
	 * Operation zum Ausloggen. Schliesst die Session des Nutzers.
	 */
	public ReturncodeResponse logout(int sessionId);
	
	public DiceResponse diceNumber();
	
	public UserRegisterResponse register(String username, String password);
	
	public GameListResponse getGames();
	
}
