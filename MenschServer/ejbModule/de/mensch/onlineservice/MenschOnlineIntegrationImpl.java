package de.mensch.onlineservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

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


/**
 * @author 
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
		Customer user = this.dao.findCustomerByName(username);		
		if (user != null && user.getPassword().equals(password)) {
			int sessionId = dao.createSession(user);
			System.out.println("Login erfolgreich. Session=" + sessionId);
			response.setSessionId(sessionId);
			response.setSuccess(true);
		}
		else { response.setSuccess(false); }
		
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
	public AttemptToJoinResponse joinGame(int id, int sessionId) throws NoSessionException {
		AttemptToJoinResponse response = new AttemptToJoinResponse();
		MenschSession session = getSession(sessionId);
		Request request = this.dao.createRequest(id, session.getUsername());
		response.setSuccess(true);
		return response;
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
	public RequestResponse getRequests(int id) {
		RequestResponse response = new RequestResponse();
		Request request = this.dao.getRequests(id);
		return response;
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
	@Override
	public JoinResponse joinGameResponse(int id, boolean success) {
		JoinResponse joinResponse = new JoinResponse();
		joinResponse.setSuccess(success);
		return joinResponse;
	}
	
	/*
	 * Wird vom Client aufgerufen, um ein neues Spiel zu erstellen
	 * 
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#createNewGame(int)
	 */
	//TODO: Not yet finished method
	@Override
	public CreateGameResponse createNewGame(int sessionId) throws NoSessionException {
		CreateGameResponse response = new CreateGameResponse();
		MenschSession session = getSession(sessionId);
		Customer user = this.dao.findCustomerByName(session.getUsername());
		createGame(user);
		response.setSuccess(true);
		System.out.println("user:" + user);
		Game foundGame = this.dao.findGameByOwnerUserName(user);
		System.out.println(foundGame + " ; "+ foundGame.getId() + " ; " +foundGame.getOwner());
		response.setId(foundGame.getId());
		response.toString();
		return response;
	}
	
	//TODO: Not yet finished method	
	/*
	 * Wird von createNewGame aufgerufen, um eine neue Game Entity anzulegen
	 */
	private void createGame(Customer user) {
		System.out.println("Erzeuge Spiel...");
		this.dao.createGame(user);
		System.out.println("Spiel erzeugt");
	}
//	@Override
//	public AccountListResponse getMyAccounts(int sessionId) {
//		AccountListResponse response = new AccountListResponse();
//		try {
//			XbankSession session = getSession(sessionId);
//			Customer user = this.dao.findCustomerByName(session.getUsername());
//			List<Account> accountList = user.getAccounts();
//			response.setAccountList(dtoAssembler.makeDTO(accountList));
//			System.out.println("Abfrage eigener Konten liefert: "+accountList);		
//		}
//		catch (XbankException e) {
//			response.setReturnCode(e.getErrorCode());
//			response.setMessage(e.getMessage());
//		}
//		return response;
//	}
//
//	@Override
//	public AccountBalanceResponse getBalance(int sessionId, int accountId) {
//		AccountBalanceResponse response = new AccountBalanceResponse();
//		try {
//			XbankSession session = getSession(sessionId);
//			Customer user = this.dao.findCustomerByName(session.getUsername());
//			Account account = user.getAccountById(accountId);
//			if (account!=null) {
//				response.setBalance(account.getBalance());
//				response.setAccountId(account.getId());
//				System.out.println("Abfrage Saldo Konto " + account.getId() + " liefert: "+ account.getBalance());
//			}
//		}
//		catch (XbankException e) {
//			response.setReturnCode(e.getErrorCode());
//			response.setMessage(e.getMessage());
//		}
//		return response;
//	}
}
