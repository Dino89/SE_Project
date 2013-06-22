package de.mensch.onlineservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jws.WebService;
import javax.naming.InitialContext;

import com.sun.corba.se.pept.transport.Connection;

import de.mensch.dto.AcceptOrDeclineFellowPlayer;
import de.mensch.dto.AttemptToJoinResponse;
import de.mensch.dto.CreateGameResponse;
import de.mensch.dto.DiceResponse;
import de.mensch.dto.DiceTO;
import de.mensch.dto.GameDetailResponse;
import de.mensch.dto.GameFieldResponse;
import de.mensch.dto.GameListResponse;
import de.mensch.dto.JoinResponse;
import de.mensch.dto.RequestResponse;
import de.mensch.dto.ReturncodeResponse;
import de.mensch.dto.UserLoginResponse;
import de.mensch.dto.UserRegisterResponse;
import de.mensch.util.DtoAssembler;
import de.mensch.dao.MenschDAO;
import de.mensch.dao.MenschDAOLocal;
import de.mensch.entities.Customer;
import de.mensch.entities.Game;
import de.mensch.entities.GameField;
import de.mensch.entities.MenschSession;
import de.mensch.entities.Request;
import de.mensch.highscore.GetHighscoreListFromHighscoreServer;
import de.mensch.highscore.SendHighscore;


/**
 * @author SE_Project
 * Diese Stateless Session Bean implementiert das fuer das Spiel bereitgestellte Interface.
 *
 */
@WebService
@Stateless
@Remote(MenschOnlineIntegration.class)

public class MenschOnlineIntegrationImpl implements MenschOnlineIntegration {

	/**
	 * EJB zur Erzeugung von DataTransferObjects
	 */
	@EJB
	private DtoAssembler dtoAssembler;
	
//	@EJB
//	private SendHighscore sendH;

	@EJB(beanName = "MenschDAO", beanInterface = de.mensch.dao.MenschDAOLocal.class)
	private MenschDAOLocal dao;

	private MenschSession getSession(int sessionId) throws NoSessionException {
		MenschSession session = dao.findSessionById(sessionId);
		if (session==null)
			throw new NoSessionException("Bitte zunaechst ein Login durchfuehren.");
		else
			return session;
	}

	@Override
	public UserLoginResponse login(String username, String password) {
		UserLoginResponse response = new UserLoginResponse();
		try {
			Customer user = this.dao.findCustomerByName(username);		
			if (user != null && user.getPassword().equals(password)) {
				int sessionId = dao.createSession(user);
				System.out.println("Login erfolgreich. Session=" + sessionId);
				response.setSessionId(sessionId);
				
				//TEST TEMP
				SendHighscore sh = new SendHighscore();
				sh.highscorePoinsForLeavingGame(user);
				
				GetHighscoreListFromHighscoreServer ghl = new GetHighscoreListFromHighscoreServer();
				ghl.getHighscoreListFromServer();
				
				
				//ENDE TEST EMP
			}
			else {
				System.out.println("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username=" + username);
				throw new InvalidLoginException("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username="+username);
			}
		}
		catch (MenschException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	public UserRegisterResponse register(String username, String password) {
		Customer user = this.dao.findCustomerByName(username);	
		UserRegisterResponse response = new UserRegisterResponse();
		if (user == null) {
			dao.registerCustomer(username, password);
			response.setSuccess(true);
		}

		return response;
	}

	@Override
	public ReturncodeResponse logout(int sessionId) {
		dao.closeSession(sessionId);
		ReturncodeResponse response = new ReturncodeResponse();
		System.out.println("Logout erfolgreich. Session=" + sessionId);
		return response;
	}
	/*
	 * Erzeugt eine Zufallszahl zwischen 1 und 6
	 * 
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#diceNumber()
	 */
	@Override
	public DiceResponse diceNumber() {
		DiceResponse response = new DiceResponse();
		double random = Math.round(Math.random()*100%6);
		response.setDiceNumber(random);
		System.out.println(response);
		return response;
	}

	@Override
	public GameListResponse getGames() {
		GameListResponse response = new GameListResponse();
		ArrayList<Game> gameList = this.dao.getGameList();

		response.setGameList(dtoAssembler.makeDTO(gameList));
		return response;
	}
	/*
	 * Details zu einer @param id gameid anzeigen
	 * 
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#getGameDetails(int)
	 */
	@Override
	public GameDetailResponse getGameDetails(int id) {
		System.out.println(id);
		GameDetailResponse response = new GameDetailResponse();
		Game gameDetail = this.dao.getGameDetails(id);
		response.setGameDetails(dtoAssembler.makeDTO(gameDetail));
		System.out.println("GameDetailResponse getGameDetails toString: " + response.getGameDetails(id).toString());
		response.setReturnCode(1);
		return response;
	}
	
	//TODO: Not yet finished method	
	/*
	 * Wird vom Client gepollt, um Veränderungen auf dem Spielfeld abzufragen
	 * 
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#getGameFields(int)
	 */
	@Override
	public GameFieldResponse getGameFields(int id) {
		GameFieldResponse response = new GameFieldResponse();
		Game gameFields = this.dao.getGameFields(id);
		response.setGameFields(dtoAssembler.makeDTO(gameFields));
		return response;
	}
	
	//TODO: Not yet finished method
	/*
	 * Erzeugt eine neue Anfrage an den Spielersteller
	 * 
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#joinGame(int, int)
	 * @param id id vom game welchem beigetreten werden soll
	 * @param sessionId session vom user, welcher das spiel eröffnen möchte
	 * @return statusmeldung, ob dem spiel beigetreten wurde
	 * 
	 */
	@Override
	public AttemptToJoinResponse requestJoinGame(int id, int sessionId) throws NoSessionException {
		AttemptToJoinResponse response = new AttemptToJoinResponse();
		MenschSession session = getSession(sessionId);
		Request request = this.dao.createRequest(id, session.getUsername());
		response.setRequestId(request.getId());
		response.setSuccess(true);
		System.out.println("Neuer JoinGameRequest "+request.getId()+" Gameid "+id);
		return response;
	}
	public void leaveGame(int sessionId, int gameid){
		Game g = this.dao.getGameDetails(gameid);
		MenschSession s = this.dao.findSessionById(sessionId);
		
		if(g.getOwner().getUserName().equals(s.getUsername())){
			System.out.println("Spielleiter "+g.getOwner().getUserName()+" schließt sein Spiel");
			this.closeGame(gameid);
		}else{
			if(g.getSpieler1().getUserName().equals(s.getUsername())){
				g.setSpieler1(null);
			}
			if(g.getSpieler2().getUserName().equals(s.getUsername())){
				g.setSpieler2(null);
			}
			if(g.getSpieler3().getUserName().equals(s.getUsername())){
				g.setSpieler3(null);
			}
			if(g.getSpieler4().getUserName().equals(s.getUsername())){
				g.setSpieler4(null);
			}
			Map<String, Customer> z = g.getZuschauer();
			z.remove(s.getUsername());
		}
	    
		
	}
	
	private void closeGame(int gameid){
		this.dao.removeGame(gameid);
		
	}
	//TODO: Not yet finished method
	/*
	 * Polling vom Spielersteller, ob neue Requests vorliegen
	 * 
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#getRequests(int)
	 * @param id gameid, welchem eine beitrittsanfrage gestellt werden soll
	 */
	@Override
	public ArrayList<Request> getRequests(int id) {
		//RequestResponse response = new RequestResponse();
		System.out.println("Frage ab ob Requests vorliegen für Spiel "+id);
		ArrayList<Request> requests = this.dao.getRequests(id);
		System.out.println(requests.size()+" Requests");
		if(requests == null)
			return new ArrayList<Request>();
		
		return requests;
	}
	
	//TODO: Not yet finished method
	/*
	 * Polling vom Client, ob der Anfrage zugestimmt wurde
	 * 
	 * @param id gameid
	 * @return true or false
	 * 
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#joinGameResponse(boolean)
	 */
//	@Override
//	public JoinResponse joinGameResponse(int id) {
//		JoinResponse joinResponse = new JoinResponse();
//		boolean success = this.dao.getRequests(id).isSuccess();
//		joinResponse.setSuccess(success);
//		return joinResponse;
//	}
	
	public RequestResponse checkMyRequest(int requestId){
		System.out.println("checkrequest reqID:"+requestId);
		Request r = this.dao.getRequest(requestId);
		RequestResponse result = new RequestResponse();
		result.setState(r.getState());
		if(r.getState().equals("declined")){
			this.dao.removeRequest(requestId);
		}
		return result;
		
	}
	/*
	 * Wird vom Client aufgerufen, um ein neues Spiel zu erstellen
	 * 
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#createNewGame(int)
	 */
	//TODO: Not yet finished method -> sollte jz laufen
	@Override
	public CreateGameResponse createNewGame(int sessionId) throws NoSessionException {
		CreateGameResponse response = new CreateGameResponse();
		MenschSession session = getSession(sessionId);
		Customer user = this.dao.findCustomerByName(session.getUsername());
		createGame(user);
		response.setSuccess(true);
		System.out.println("user:" + user);
		Game foundGame = this.dao.findGameByOwnerUserName(user);
		foundGame.setSpieler1(user);
		System.out.println(foundGame + " ; "+ foundGame.getId() + " ; " +foundGame.getOwner());
		response.setId(foundGame.getId());
		response.toString();
		return response;
	}
		
	//TODO: Not yet finished method	-> sollte jz laufen
	/*
	 * Wird von createNewGame aufgerufen, um eine neue Game Entity anzulegen
	 */
	private void createGame(Customer user) {
		System.out.println("Erzeuge Spiel...");
		this.dao.createGame(user);
		System.out.println("Spiel erzeugt");
	}
	
	//TODO: Not yet finished method
	/*
	 * @param sessionId sessionId vom owner um Berechtigung zu prüfen
	 * @param id gameid (vll auch über owner zu kriegen)
	 * Sollte success bei joinGameResponse setzen!
	 */
	@Override
	public AcceptOrDeclineFellowPlayer fellowPlayer(int id, int sessionId) {
		AcceptOrDeclineFellowPlayer response = new AcceptOrDeclineFellowPlayer();
		return response;
	}

	@Override
	public void allowPlayer(int requestId) {
		
		Request r = this.dao.getRequest(requestId);
		r.setState("accepted");
		Game g = r.getGame();
		Customer c = this.dao.findCustomerByName(r.getUser());
		
		if(g.getSpieler1() == null){
			g.setSpieler1(c);
		}else{
		if(g.getSpieler2() == null){
			g.setSpieler2(c);
		}else{
		if(g.getSpieler3() == null){
			g.setSpieler3(c);
		}else{
		if(g.getSpieler4() == null){
			g.setSpieler4(c);
		}
		}
		}
		}
		
		
	}

	@Override
	public void declinePlayer(int requestId) {

		Request r = this.dao.getRequest(requestId);
		r.setState("declined");
		
	}
	
	@Override
	public void getHighscoreList() {
		System.out.println("button bla");

	}
}