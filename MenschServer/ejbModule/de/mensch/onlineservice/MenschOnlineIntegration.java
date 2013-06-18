package de.mensch.onlineservice;

import java.math.BigDecimal;

import de.mensch.dto.AcceptOrDeclineFellowPlayer;
import de.mensch.dto.AttemptToJoinResponse;
import de.mensch.dto.CreateGameResponse;
import de.mensch.dto.DiceResponse;
import de.mensch.dto.GameDetailResponse;
import de.mensch.dto.GameFieldResponse;
import de.mensch.dto.GameListResponse;
import de.mensch.dto.JoinResponse;
import de.mensch.dto.RequestResponse;
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
	
	public GameDetailResponse getGameDetails(int id);
	
	public GameFieldResponse getGameFields(int id);

	public AttemptToJoinResponse joinGame(int id, int sessionId) throws NoSessionException;
	
	public void leaveGame(int sessionId, int gameid);
	
	public JoinResponse joinGameResponse(int id);

	public CreateGameResponse createNewGame(int sessionId) throws NoSessionException;

	public RequestResponse getRequests(int id);

	public AcceptOrDeclineFellowPlayer fellowPlayer(int id, int sessionId);
	
}
