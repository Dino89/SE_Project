package de.mensch.onlineservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.mensch.dto.DiceResponse;
import de.mensch.dto.DiceTO;
import de.mensch.dto.GameListResponse;
import de.mensch.dto.ReturncodeResponse;
import de.mensch.dto.UserLoginResponse;
import de.mensch.dto.UserRegisterResponse;
import de.mensch.util.DtoAssembler;
import de.mensch.dao.MenschDAO;
import de.mensch.dao.MenschDAOLocal;
import de.mensch.entities.Customer;
import de.mensch.entities.Game;
import de.mensch.entities.MenschSession;
//import de.xbank.dao.XbankDAOLocal;
//import de.xbank.entities.Customer;
//import de.xbank.onlinebanking.InvalidLoginException;
//import de.xbank.onlinebanking.XbankException;
//import de.xbank.entities.XbankSession;
//import de.xbank.onlinebanking.NoSessionException;
//import de.xbank.dto.AccountBalanceResponse;
//import de.xbank.dto.AccountListResponse;
//import de.xbank.entities.Account;
//import de.xbank.entities.XbankSession;
//import de.xbank.onlinebanking.XbankException;


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
	
//	@EJB(beanName = "XbankDAO", beanInterface = de.xbank.dao.XbankDAOLocal.class)
//	private XbankDAOLocal dao;
	
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
			}
			else {
				System.out.println("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username=" + username);
				throw new InvalidLoginException("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username="+user.getUserName());
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

	@Override
	public DiceResponse diceNumber() {
		DiceResponse response = new DiceResponse();
		response.setDiceNumber("abc");
		System.out.println("dice number angefragt");
		System.out.println(response);
		System.out.println(response.getDiceNumber());
		return response;
	}

	@Override
	public GameListResponse getGames() {
		GameListResponse response = new GameListResponse();
		ArrayList <Game> games = this.dao.getGameList();
		response.setGameList(games);
		System.out.println(response);
		return response;
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
