package de.mensch.onlineservice;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.mensch.dto.AcceptOrDeclineFellowPlayer;
import de.mensch.dto.AttemptToJoinResponse;
import de.mensch.dto.CreateGameResponse;
import de.mensch.dto.DiceResponse;
import de.mensch.dto.DiceTO;
import de.mensch.dto.GameDetailResponse;
import de.mensch.dto.GameFieldResponse;
import de.mensch.dto.GameListResponse;
import de.mensch.dto.GameTO;
import de.mensch.dto.HighscoreListResponse;
import de.mensch.dto.JoinResponse;
import de.mensch.dto.RequestListResponse;
import de.mensch.dto.RequestResponse;
import de.mensch.dto.Response;
import de.mensch.dto.ReturncodeResponse;
import de.mensch.dto.SpielzugResponse;
import de.mensch.dto.UserLoginResponse;
import de.mensch.dto.UserRegisterResponse;
import de.mensch.util.DtoAssembler;
import de.mensch.dao.MenschDAO;
import de.mensch.dao.MenschDAOLocal;
import de.mensch.entities.Customer;
import de.mensch.entities.Dice;
import de.mensch.entities.Game;
import de.mensch.entities.GameField;
import de.mensch.entities.MenschSession;
import de.mensch.entities.Request;
import de.mensch.highscore.GetHighscoreListFromHighscoreServer;
import de.mensch.highscore.ReceivedHighscoreListFromHighscoreServer;
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
	
	/*
	 * Diese Methode kümmert (bzw. sollte) sich um das Aufräumen alter Sessions, Spiele und Requests.
	 */
	@Schedule(second="59", minute="*",hour="*", persistent=false)
	public void removeOldSessions() {
//		System.out.println("Removing Sessions...");
		ArrayList<MenschSession> sessionList = this.dao.findSessions();
		ArrayList<Game> gameList = this.dao.getGameList();
		ArrayList<Request> requestList = this.dao.getAllRequests();
		
		for(MenschSession s : sessionList) {
			Date currentTime = new Date();
			
//			System.out.println(currentTime);
			String servertime = currentTime.toString();
			
			String[] serverTimeMinutes = servertime.split(":");
			String[] serverTimeHour = serverTimeMinutes[0].split(" ");
//			System.out.println("server time 1:");
//			for(String string : serverTimeMinutes) {
//				System.out.print(string+ " ### ");
//			}
//			System.out.println("server time 2:");
//			for(String string : serverTimeHour) {
//				System.out.print(string+ " ### ");
//			}
			int currentTimeStamp = Integer.valueOf(serverTimeHour[3]+serverTimeMinutes[1]);
			
//			System.out.println("serverzeit:" +servertime);
			
			String result = s.getCreationTime().toString();
			String[] sessionTimeMinutes = result.split(":");
			String[] sessionTimeHour = sessionTimeMinutes[0].split(" ");
//			System.out.println("Session time 1:");
//			for(String string : sessionTimeMinutes) {
//				System.out.print(string+ " ### ");
//			}
//			System.out.println("Session time 2:");
//			for(String string : sessionTimeHour) {
//				System.out.print(string+ " ### ");
//			}
			
			int timestamp = Integer.valueOf(sessionTimeHour[1]+sessionTimeMinutes[1]);
			
			if(timestamp+1<currentTimeStamp) {
				System.out.println("timestamp: "+timestamp +" < "+ currentTimeStamp);
				System.out.println("Session removed: "+s.getUsername());
				this.dao.closeSession(s.getId());
				Customer c = this.dao.findCustomerByName(s.getUsername());
				for(Request q : requestList) {
					if(q.getUser().equals(c)) this.dao.removeRequest(q.getId());
					System.out.println("Request "+q.getId()+" "+q.getUser()+" entfernt");
				}

			}
		}
		
		for(Game g : gameList) {
			String gameOwnerName = g.getOwner().getUserName();
			int counter = 0;
			for(MenschSession s : sessionList) {
				if(gameOwnerName.equals(s.getUsername())) counter++;
			}
			if(counter==0) {
				this.dao.removeGame(g.getId());
				System.out.println("Game "+g.getId()+" "+g.getOwner().getUserName()+" removed");
			}
		}
	}
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
		try {
			Customer user = this.dao.findCustomerByName(username);		
			if (user != null && user.getPassword().equals(password)) {
				int sessionId = dao.createSession(user);
				System.out.println("Login erfolgreich. Session=" + sessionId);
				response.setSessionId(sessionId);
				
				//TESTDATEN SCHREIBEN TEMP!!!!!!!
				SendHighscore blala = new SendHighscore();
				blala.highscorePoinsForLeavingGame(user);
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
	
	@Override
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
	public GameListResponse diceNumber(int sessionId, int gameId) throws NoSessionException {
		MenschSession session = getSession(sessionId);
		System.out.println("gameid: "+gameId);
		Game game = this.dao.findGameById(gameId);
		GameListResponse response = new GameListResponse();
		
		if(game.getAktuellerSpieler()!=session.getUsername()) return response;
	
		game.setDiceNumber((int) Math.round(Math.random()*100%7));
		System.out.println("game dice "+game.getDiceNumber());
		
		ArrayList<Game> gameList = this.dao.getGameList();
		response.setGameList(dtoAssembler.makeDTO(gameList));
		
		return response;
	}
	/* @return Liste aller vorhandenen Spiele
	 * @param sessionId sessionId des Anfragenden, damit nur eingeloggte Benutzer eine Liste einsehen können.
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#getGames(int)
	 */
	@Override
	public GameListResponse getGames(int sessionId) throws NoSessionException {
		MenschSession session = getSession(sessionId);
		Date date = new Date();
//		System.out.println("Updating session at getGames...");
		session.setCreationTime(date);
//		System.out.println("Session updated");
		GameListResponse response = new GameListResponse();
		ArrayList<Game> gameList = this.dao.getGameList();
		response.setGameList(dtoAssembler.makeDTO(gameList));
		
//		System.out.println("getGames response: "+response.toString());
		return response;
	}
	/*
	 * Details zu einer @param id gameid anzeigen
	 * 
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#getGameDetails(int)
	 */
	@Override
	public GameTO getGameDetails(int id) {
		System.out.println(id);
		GameDetailResponse response = new GameDetailResponse();
		Game game = this.dao.getGame(id);
		response.setGameDetails(dtoAssembler.makeDTO(game));
		System.out.println("GameDetailResponse getGameDetails toString: " + response.getGameDetails(id).toString());
		
		return dtoAssembler.makeDTO(game);
	}
	
	//TODO: Not yet finished method	
	/*
	 * Wird vom Client gepollt, um Veränderungen auf dem Spielfeld abzufragen
	 * 
	 * (non-Javadoc)
	 * @see de.mensch.onlineservice.MenschOnlineIntegration#getGameFields(int)
	 */
	@Override
	public GameListResponse getGameFields(int gameid) {
		GameListResponse response = new GameListResponse();
		
		Game game = this.dao.findGameById(gameid);

		ArrayList<Game> gameList = this.dao.getGameList();
		response.setGameList(dtoAssembler.makeDTO(gameList));
		
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
		s.setCurrentGame(null);
		
		if(g.getOwner().getUserName().equals(s.getUsername())){
			System.out.println("Spielleiter "+g.getOwner().getUserName()+" schließt sein Spiel");
			this.closeGame(gameid);
		}else{
			if(g.getSpieler1().getUserName().equals(s.getUsername())){
				g.setSpieler1(null);
			}
			System.out.println(s.getUsername()+" verlässt Spiel als Spieler 2: "+g.getSpieler2().getUserName());
			if(g.getSpieler2() != null)
			if(g.getSpieler2().getUserName().equals(s.getUsername())){
				g.setSpieler2(null);
				
			}
			if(g.getSpieler3() != null)
			if(g.getSpieler3().getUserName().equals(s.getUsername())){
				g.setSpieler3(null);
			}
			if(g.getSpieler4() != null)
			if(g.getSpieler4().getUserName().equals(s.getUsername())){
				g.setSpieler4(null);
			}
			
			Map<Integer, MenschSession> z = g.getZuschauer();
			z.remove(s);
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
	public RequestListResponse getRequests(int id) {
		RequestListResponse response = new RequestListResponse();
		System.out.println("Frage ab ob Requests vorliegen für Spiel "+id);
		ArrayList<Request> requests = this.dao.getRequests(id);
		System.out.println(requests.size()+" Requests");
		if(requests.size()!=0) System.out.println("user request: "+requests.get(0).getUser());
		response.setRequestList(dtoAssembler.makeDTORequestList(requests)); 
		if(requests.size()!=0) System.out.println(response.getRequestList().get(0).getUserName());
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
//	@Override
//	public JoinResponse joinGameResponse(int id) {
//		JoinResponse joinResponse = new JoinResponse();
//		boolean success = this.dao.getRequests(id).isSuccess();
//		joinResponse.setSuccess(success);
//		return joinResponse;
//	}
	
	public RequestResponse checkMyRequest(int requestId){
		
		Request r = this.dao.getRequest(requestId);
		RequestResponse result = new RequestResponse();
		result.setState(r.getState());
		System.out.println("checkrequest reqID:"+requestId+" "+result);
		if(r.getState().equals("declined") || r.getState().equals("accepted")){
			System.out.println("Request removed");
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
		System.out.println(foundGame + " ; "+ foundGame.getId() + " ; " +foundGame.getOwner().getUserName());
		response.setId(foundGame.getId());
		response.toString();
		System.out.println("createNewGame(): " + foundGame.toString());
//		session.setCurrentGame(foundGame);
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
		System.out.println("Request akzeptiert "+requestId);
		Request r = this.dao.getRequest(requestId);
		r.setState("accepted");
		Game g = r.getGameentity();
		Customer c = this.dao.findCustomerByName(r.getUser());
//		MenschSession session = this.dao.findSessionByUserName(c.getUserName());
//		this.dao.removeRequest(r.getId());
//		System.out.println("request"+r.getId()+" removed");

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
		System.out.println("Request abgelehnt "+requestId);
		
		Request r = this.dao.getRequest(requestId);
		r.setState("declined");
	}
	
	@Override
	public void startGame(int gameid, int sessionId) throws NoSessionException {
		System.out.println(sessionId);
		MenschSession session = getSession(sessionId);
		Game game = this.dao.getGame(gameid);
		game.setStarted(true);
		System.out.println("Gestartet: "+game.isStarted());
	}
	
	@Override
	public void spectateGame(int sessionId, int gameid) throws NoSessionException{
		MenschSession session = getSession(sessionId);
		Map <Integer,MenschSession>z = (Map<Integer, MenschSession>) this.dao.getGame(gameid).getZuschauer();
		Game g = this.dao.findGameById(gameid);
		session.setCurrentGame(g);
		
		
		
		z.put(sessionId, session);
		System.out.println("Neuer Zuschauer: "+z.get(sessionId).getUsername()+" bei Game "+z.get(sessionId).getCurrentGame().getId());
	}
	
	@Override
	public SpielzugResponse spielen(int gameid, int sessionid, int spielfigurfeld, int diceNumber) throws NoSessionException {
		System.out.println("Ich will spielen!");
		MenschSession session = null;
		if(sessionid!=-1) session = getSession(sessionid);
		System.out.println("Prüfe auf verlassene Spieler");
		spielerVerlassen(gameid);
		SpielzugResponse response = new SpielzugResponse();
		Game game = this.dao.findGameById(gameid);
		if(!spielerIstDran(gameid, session)) return response;
		System.out.println("Ziehe...");
		ziehen(gameid, spielfigurfeld, diceNumber);
		pruefeNochmalWuerfeln(gameid);
		if(game.getWuerfelCount()<=2) { 
			System.out.println("Nochmal dran!");
			game.setAktuellerSpieler(game.getAktuellerSpieler());
		} else { 
			if(game.getSpieler2()==null) {
				System.out.println("De Komputa speelt!");
				computerZieht(gameid);
			}
			if(game.getAktuellerSpieler().equals("spieler1")) game.setAktuellerSpieler("spieler2");
			if(game.getAktuellerSpieler().equals("spieler2")) game.setAktuellerSpieler("spieler3");
			if(game.getAktuellerSpieler().equals("spieler3")) game.setAktuellerSpieler("spieler4");
			if(game.getAktuellerSpieler().equals("spieler4")) game.setAktuellerSpieler("spieler1");
		}
		spielerGewonnen(gameid);
		spielZuEnde(gameid);
		response.setSuccess(true);
		return response;
	}

	private void computerZieht(int gameid) {
//		Game game = this.dao.findGameById(gameid);
//		GameField field = game.getGameField();
//		int size = field.getSize();
//		Dice dice = new Dice();
//		int diceid = dice.getId();
//		
//		for(int i = 1; i<size;i++) {
//			if(field.getField(i)==2)
//				try {
//					spielen(gameid, -1, field.getField(i), diceid);
//				} catch (NoSessionException e) {
//					System.out.println("C0mPut3rf3h13r!");
//				}
//		}
		
	}

	private void spielZuEnde(int gameid) {
		// TODO Auto-generated method stub
		
	}

	private void spielerGewonnen(int gameid) {
		// TODO Auto-generated method stub
		
	}

	private boolean ziehen(int gameid, int spielfigurfeld, int diceNumber) {
		Game game = this.dao.findGameById(gameid);
		if(!eigeneFigur(game, spielfigurfeld)) return false;
		setzeFigur(game, spielfigurfeld, diceNumber);
		pruefeNochmalWuerfeln(gameid);
		
		return true;
	}

	private void spielerVerlassen(int gameid) {
		// TODO Auto-generated method stub
		
	}

	private boolean spielerIstDran(int gameid, MenschSession session) {
		if(session!=null) {
			Game g = this.dao.findGameById(gameid);
			if(g.getAktuellerSpieler().equals(session.getUsername())) return true;
			return false;
		}
		return true;
	}
	
//	@Override
//	public SpielzugResponse spielen(int gameid, int sessionId, int spielFeldNummer, int diceId) throws NoSessionException {
//		MenschSession session = getSession(sessionId);
//		Game game = this.dao.findGameById(gameid);
//		SpielzugResponse response = new SpielzugResponse();
//		game.setWuerfelCount(0);
//		while(game.getWuerfelCount()<=3) {
//			if(game.getSpieler2()==null) {
//				spieleMitComputer(gameid, spielFeldNummer, diceId);
//			} else {
//				boolean success = Spielzug(gameid, spielFeldNummer, diceId);
//			}
//			pruefeNochmalWuerfeln(gameid);
//		}
//		response.setSuccess(true);
//		return response;
//	}
//
	private void pruefeNochmalWuerfeln(int gameid) {
		Game game = this.dao.findGameById(gameid);
		
//		if((game.getGameField().getField(1) == 0) & (game.getGameField().getField(2) == 0) & (game.getGameField().getField(3) == 0) & (game.getGameField().getField(4) == 0)) {
//			game.setAktuellerSpieler(game.getAktuellerSpieler());
//			game.setWuerfelCount(game.getWuerfelCount()+1);
//		} else { game.setWuerfelCount(3); }
//		if((game.getGameField().getField(1) == 0) & (game.getGameField().getField(2) == 0) & (game.getGameField().getField(3) == 0) & (game.getGameField().getField(4) == 0)) {
//			game.setAktuellerSpieler(game.getAktuellerSpieler());
//			game.setWuerfelCount(game.getWuerfelCount()+1);
//		} else { game.setWuerfelCount(3); }
//		if((game.getGameField().getField(1) == 0) & (game.getGameField().getField(2) == 0) & (game.getGameField().getField(3) == 0) & (game.getGameField().getField(4) == 0)) {
//			game.setAktuellerSpieler(game.getAktuellerSpieler());
//			game.setWuerfelCount(game.getWuerfelCount()+1);
//		} else { game.setWuerfelCount(3); }
//		if((game.getGameField().getField(1) == 0) & (game.getGameField().getField(2) == 0) & (game.getGameField().getField(3) == 0) & (game.getGameField().getField(4) == 0)) {
//			game.setAktuellerSpieler(game.getAktuellerSpieler());
//			game.setWuerfelCount(game.getWuerfelCount()+1);
//		} else { game.setWuerfelCount(3); }		
	}
//
//	private boolean spieleMitComputer(int gameid, int spielFeldNummer, int diceId) {
//		boolean success = false;
//		Game game = this.dao.findGameById(gameid);
//		eigeneFigur(game,spielFeldNummer);
//		setzeFigur(game,spielFeldNummer,diceId);
//		spielerGewonnen(game);
//		spielZuEnde(game);
//					
//		if(game.getAktuellerSpieler().equals("spieler1")) game.setAktuellerSpieler("computer");
//		if(game.getAktuellerSpieler().equals("computer")) game.setAktuellerSpieler("spieler1");
//		
//		return success;		
//	}
//
//	private boolean Spielzug(int gameid, int spielFeldNummer, int diceId) {	
//		boolean success = false;
//			Game game = this.dao.findGameById(gameid);
//			eigeneFigur(game,spielFeldNummer);
//			setzeFigur(game,spielFeldNummer,diceId);
//			spielerGewonnen(game);
//			spielZuEnde(game);
//					
//		if(game.getAktuellerSpieler().equals("spieler1")) game.setAktuellerSpieler("spieler2");
//		if(game.getAktuellerSpieler().equals("spieler2")) game.setAktuellerSpieler("spieler3");
//		if(game.getAktuellerSpieler().equals("spieler3")) game.setAktuellerSpieler("spieler4");
//		if(game.getAktuellerSpieler().equals("spieler4")) game.setAktuellerSpieler("spieler1");
//		
//		return success;		
//	}
//
//	private void spielZuEnde(Game game) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	private void spielerGewonnen(Game game) {
//		// TODO Auto-generated method stub
//		
//	}
//
	private boolean setzeFigur(Game game, int spielFeldNummer, int diceNumber) {
		GameField spielfeld = game.getGameField();
		
//		int zielFeldValue = spielfeld.getField(spielFeldNummer+diceNumber);
//		int zielFeld = spielFeldNummer+diceNumber;
//		
//		if(!(zielFeld>=73)) {
//			if(zielFeldValue==spielFeldNummer) {
//				return false;
//			} else {
//				if(zielFeldValue==0) { 
//					spielfeld.setField(zielFeld, zielFeldValue);
//					return true;
//				} else {
//					figurSchmeissen(game, zielFeld);
//					return true;
//				}
//			}
//		} else {
//			
//		}
		
		return false;		
	}
	
	/*
	 * int index 1 bis 4 startfelder blau
	 * int index 5 bis 8 startfelder rot
	 * int index 9 bis 12 startfelder grün
	 * int index 13 bis 16 startfelder gelb
	 * */
	private void figurSchmeissen(Game game, int zielFeld) {
		GameField spielfeld = game.getGameField();
//		int zielFeldFigur = spielfeld.getField(zielFeld);
//		
//		if(zielFeldFigur==1) {
//			if(spielfeld.getField(4)==0) {
//				spielfeld.setField(4, 1);
//			} else if(spielfeld.getField(3)==0) {
//				spielfeld.setField(3, 1);
//			} else if(spielfeld.getField(2)==0) {
//				spielfeld.setField(2, 1);
//			} else if(spielfeld.getField(1)==0) {
//				spielfeld.setField(1, 1);
//			}
//		}
//		
//		if(zielFeldFigur==2) {
//			if(spielfeld.getField(4)==0) {
//				spielfeld.setField(4, 2);
//			} else if(spielfeld.getField(3)==0) {
//				spielfeld.setField(3, 2);
//			} else if(spielfeld.getField(2)==0) {
//				spielfeld.setField(2, 2);
//			} else if(spielfeld.getField(1)==0) {
//				spielfeld.setField(1, 2);
//			}
//		}
//			
//		if(zielFeldFigur==3) {
//			if(spielfeld.getField(4)==0) {
//				spielfeld.setField(4, 3);
//			} else if(spielfeld.getField(3)==0) {
//				spielfeld.setField(3, 3);
//			} else if(spielfeld.getField(2)==0) {
//				spielfeld.setField(2, 3);
//			} else if(spielfeld.getField(1)==0) {
//				spielfeld.setField(1, 3);
//			}
//		}
//				
//		if(zielFeldFigur==4) {
//			if(spielfeld.getField(4)==0) {
//				spielfeld.setField(4, 4);
//			} else if(spielfeld.getField(3)==0) {
//				spielfeld.setField(3, 4);
//			} else if(spielfeld.getField(2)==0) {
//				spielfeld.setField(2, 4);
//			} else if(spielfeld.getField(1)==0) {
//				spielfeld.setField(1, 4);
//			}
//		}
	}

	/*
	 * Versucht der Spieler seine eigene Figur zu bewegen?
	 */
	private boolean eigeneFigur(Game game, int spielFeldNummer) {
//		GameField spielfeld = game.getGameField();
//		int feld = spielfeld.getField(spielFeldNummer);
//		String spielfigurOwner = null;
//		
//		if(feld==0) return false;
//		if(feld==1) spielfigurOwner = "spieler1";
//		if(feld==2) spielfigurOwner = "spieler2";
//		if(feld==3) spielfigurOwner = "spieler3";
//		if(feld==4) spielfigurOwner = "spieler4";
//		
//		if(game.getAktuellerSpieler().equals(spielfigurOwner)) { 
//			return true;
//		}
//		
		return false;
	}
	
		/**
	 * Wird von der Android App aufgerufen und holt sich die HighscoreListe
	 */
	@Override
	public HighscoreListResponse getHighscoreList() {

		System.out.println("button bla");
		//Befehl senden an HighscoreServer und so die Liste holen
		GetHighscoreListFromHighscoreServer ghl = new GetHighscoreListFromHighscoreServer();
		ghl.getHighscoreListFromServer();
		//Warten auf Antwort vom HighscoreServer
		try {Thread.sleep(2000);}catch(Exception ex) {System.out.println("ThreadSleep ERROR aus getHighscoreList");}
		
		ReceivedHighscoreListFromHighscoreServer recList = new ReceivedHighscoreListFromHighscoreServer();
		String list = recList.highscoreListeFromHighscoreServer;
		
		HighscoreListResponse response = new HighscoreListResponse();
		response.setList(list);
		return response;
		
	}
}