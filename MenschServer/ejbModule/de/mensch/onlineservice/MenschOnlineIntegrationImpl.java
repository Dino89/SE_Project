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
//	@Schedule(second="59", minute="*",hour="*", persistent=false)
//	public void removeOldSessions() {
//		System.out.println("Removing Sessions...");
//		ArrayList<MenschSession> sessionList = this.dao.findSessions();
//		ArrayList<Game> gameList = this.dao.getGameList();
//		ArrayList<Request> requestList = this.dao.getAllRequests();
//		
//		for(MenschSession s : sessionList) {
//			Date currentTime = new Date();
//			
////			System.out.println(currentTime);
//			String servertime = currentTime.toString();
//			
//			String[] serverTimeMinutes = servertime.split(":");
//			String[] serverTimeHour = serverTimeMinutes[0].split(" ");
////			System.out.println("server time 1:");
////			for(String string : serverTimeMinutes) {
////				System.out.print(string+ " ### ");
////			}
////			System.out.println("server time 2:");
////			for(String string : serverTimeHour) {
////				System.out.print(string+ " ### ");
////			}
//			int currentTimeStamp = Integer.valueOf(serverTimeHour[3]+serverTimeMinutes[1]);
//			
////			System.out.println("serverzeit:" +servertime);
//			
//			String result = s.getCreationTime().toString();
//			String[] sessionTimeMinutes = result.split(":");
//			String[] sessionTimeHour = sessionTimeMinutes[0].split(" ");
////			System.out.println("Session time 1:");
////			for(String string : sessionTimeMinutes) {
////				System.out.print(string+ " ### ");
////			}
////			System.out.println("Session time 2:");
////			for(String string : sessionTimeHour) {
////				System.out.print(string+ " ### ");
////			}
//			
//			int timestamp = Integer.valueOf(sessionTimeHour[1]+sessionTimeMinutes[1]);
//			
//			if(timestamp+1<currentTimeStamp) {
//				System.out.println("timestamp: "+timestamp +" < "+ currentTimeStamp);
//				System.out.println("Session removed: "+s.getUsername());
//				this.dao.closeSession(s.getId());
//				Customer c = this.dao.findCustomerByName(s.getUsername());
//				for(Request q : requestList) {
//					if(q.getUser().equals(c)) this.dao.removeRequest(q.getId());
//					System.out.println("Request "+q.getId()+" "+q.getUser()+" entfernt");
//				}
//
//			}
//		}
//		
//		for(Game g : gameList) {
//			String gameOwnerName = g.getOwner().getUserName();
//			int counter = 0;
//			for(MenschSession s : sessionList) {
//				if(gameOwnerName.equals(s.getUsername())) counter++;
//			}
//			if(counter==0) {
//				this.dao.removeGame(g.getId());
//				System.out.println("Game "+g.getId()+" "+g.getOwner().getUserName()+" removed");
//			}
//		}
//	}
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
		if(game.getSpieler1().getUserName().equals(session.getUsername())) game.setWuerfelCount(game.getWuerfelCount()+1);
		GameListResponse response = new GameListResponse();
		
//		if(game.getAktuellerSpieler()!=session.getUsername()) return response;
	
		game.setDiceNumber(random());
//		System.out.println("game dice "+game.getDiceNumber());
		
		ArrayList<Game> gameList = this.dao.getGameList();
		response.setGameList(dtoAssembler.makeDTO(gameList));
		
		return response;
	}
	private int random() {
		System.out.println("Erzeuge Zufallszahl...");
		double random = Math.round(Math.random()*100%7);
		if(random == 1) return (int) random;
		if(random == 2) return (int) random;
		if(random == 3) return (int) random;
		if(random == 4) return (int) random;
		if(random == 5) return (int) random;
		if(random == 6) return (int) random;
		if(random == 0) return random();
		if(random == 7) return random();
		return random();
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
			g.getPlayers().remove(s.getUsername());
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
		//	this.dao.removeRequest(requestId); //DAS HÄTTE DAS SPIEL MITGELÖSCHT
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
		g.getPlayers().add(c.getUserName());
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
		System.out.println("Starte Spiel "+gameid);
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
	public SpielzugResponse spielen(int gameid, int sessionid, int spielfigurfeld) throws NoSessionException {
		
		System.out.println("Ich will spielen!");
		MenschSession session = null;
		if(sessionid!=-1) session = getSession(sessionid);
		
		System.out.println("Prüfe auf verlassene Spieler");
		spielerVerlassen(gameid);
		
		SpielzugResponse response = new SpielzugResponse();
		
		Game game = this.dao.findGameById(gameid);
		
		
		if(!spielerIstDran(gameid, session)) return response;
		
		System.out.println("Ziehe...");
		boolean successful = ziehen(gameid, spielfigurfeld, game.getDiceNumber());
		if(!successful) return response;
//		pruefeNochmalWuerfeln(gameid);
//		
//		if(game.getWuerfelCount()<=2) { 
//			System.out.println("Nochmal dran!");
//			game.setAktuellerSpieler(game.getAktuellerSpieler());
//		}  else {
//			game.setWuerfelCount(0);
//		}
		
//		if(game.getSpieler2()!=null) {
//			if(game.getSpieler3()!=null) {
//				game.setAktuellerSpieler("spieler3"); 
//			}
//			else game.setAktuellerSpieler("spieler2");
//		} else {
//			computerZieht(gameid);
//		}
		if(game.getAktuellerSpieler() == game.getPlayers().size()-1){
			game.setAktuellerSpieler(0);
		}else{
			game.setAktuellerSpieler(game.getAktuellerSpieler()+1);
		}
		
		
//		if(game.getSpieler3()!=null) {
//			if(game.getSpieler4()!=null) {
//				game.setAktuellerSpieler("spieler4");
//			}
//			else game.setAktuellerSpieler("spieler1");
//		}
		
		spielerGewonnen(gameid);
		game.setStateMessage("Warte auf Spieler "+game.getPlayers().get(game.getAktuellerSpieler()));
		response.setSuccess(true);
		return response;
	}

	private void computerZieht(int gameid) throws NoSessionException {
		System.out.println("Der Computer zieht!");
		Game game = this.dao.findGameById(gameid);
		int spielfigurenfeld = 0;
		int random = random();
		System.out.println("Computer hat gewürfelt: "+random);
		for(int i=0;i<game.getGameField().getFields().size();i++) {
			if(game.getGameField().getFields().get(i).getState()==2) spielen(gameid, -1, i);			
		}
		
		if(spielfigurenfeld==0) {
			if(game.getGameField().getField_red_4()==2) { 
				spielen(gameid, -1, game.getGameField().getField_red_4()); 
			} else {
				if(game.getGameField().getField_red_3()==2) { 
					spielen(gameid, -1, game.getGameField().getField_red_3());
				} else {
					if(game.getGameField().getField_red_2()==2) { 
						spielen(gameid, -1, game.getGameField().getField_red_2());
				} else {
					if(game.getGameField().getField_red_1()==2) { 
						spielen(gameid, -1, game.getGameField().getField_red_1());
					}
				} 
			}
		}
	}
	}
	private void spielZuEnde(int gameid) {
		Game g = this.dao.findGameById(gameid);
		g.setStateMessage("Spiel zu Ende");
	}

	private void spielerGewonnen(int gameid) {
		SendHighscore score = new SendHighscore();
		Game g = this.dao.findGameById(gameid);
		GameField feld = g.getGameField();
		int remainingPlayers = 3;
		if(g.getSpieler4()==null) remainingPlayers=-1;
		if(g.getSpieler3()==null) remainingPlayers=-1;
		if(g.getSpieler2()==null) remainingPlayers=-1;

		if((feld.getField_blue_house_1()==1)&(feld.getField_blue_house_2()==1)&(feld.getField_blue_house_3()==1)&(feld.getField_blue_house_4()==1)){ System.out.println("Gewonnen Spieler1"); score.highscorePointsForFinishingGame(g.getSpieler1(), remainingPlayers);} 
		if((feld.getField_red_house_1()==1)&(feld.getField_red_house_2()==1)&(feld.getField_red_house_3()==1)&(feld.getField_red_house_4()==1)){ System.out.println("Gewonnen Spieler2"); score.highscorePointsForFinishingGame(g.getSpieler2(), remainingPlayers);}
		if((feld.getField_green_house_1()==1)&(feld.getField_green_house_2()==1)&(feld.getField_green_house_3()==1)&(feld.getField_green_house_4()==1)){ System.out.println("Gewonnen Spieler3"); score.highscorePointsForFinishingGame(g.getSpieler3(), remainingPlayers);}
		if((feld.getField_yellow_house_1()==1)&(feld.getField_yellow_house_2()==1)&(feld.getField_yellow_house_3()==1)&(feld.getField_yellow_house_4()==1)){ System.out.println("Gewonnen Spieler4"); score.highscorePointsForFinishingGame(g.getSpieler4(), remainingPlayers);}
		if(remainingPlayers==1) spielZuEnde(gameid);
	}

	private boolean ziehen(int gameid, int spielfigurfeld, int diceNumber) {
		Game game = this.dao.findGameById(gameid);
		if(!eigeneFigur(game, spielfigurfeld)){ 
			System.out.println("Nicht eigene Figur "+spielfigurfeld);
			return false;
		}
		System.out.println("Figur setzten..");
		setzeFigur(game, spielfigurfeld, diceNumber);
		
		return true;
	}

	private void spielerVerlassen(int gameid) {
		// TODO Auto-generated method stub
		
	}

	private boolean spielerIstDran(int gameid, MenschSession session) {
		if(session!=null) {
			Game g = this.dao.findGameById(gameid);
			System.out.println("spielerIstDran" + g.getAktuellerSpieler());
			if(g.getPlayers().get(g.getAktuellerSpieler()).equals(session.getUsername())){
				return true;
			}
		}
		return false;
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
//	private void pruefeNochmalWuerfeln(int gameid) {
//		Game game = this.dao.findGameById(gameid);
//		
//		if(game.getAktuellerSpieler().equals("spieler1")) {
//			if((game.getGameField().getField_blue_1() != 0) & (game.getGameField().getField_blue_2() != 0) & (game.getGameField().getField_blue_3() != 0) & (game.getGameField().getField_blue_4() != 0) & (game.getWuerfelCount()<3)) {
//				game.setWuerfelCount(game.getWuerfelCount()+1);
//				game.setAktuellerSpieler(game.getAktuellerSpieler());
//			} else { game.setWuerfelCount(3); }	
//		}
//		
//		if(game.getAktuellerSpieler().equals("spieler2")) {
//			if((game.getGameField().getField_red_1() != 0) & (game.getGameField().getField_red_2() != 0) & (game.getGameField().getField_red_3() != 0) & (game.getGameField().getField_red_4() != 0) & (game.getWuerfelCount()<3)) {
//				game.setWuerfelCount(game.getWuerfelCount()+1);
//				game.setAktuellerSpieler(game.getAktuellerSpieler());
//			} else { game.setWuerfelCount(3); }	
//		}
//		
//		if(game.getAktuellerSpieler().equals("spieler3")) {
//			if((game.getGameField().getField_green_1() != 0) & (game.getGameField().getField_green_2() != 0) & (game.getGameField().getField_green_3() != 0) & (game.getGameField().getField_green_4() != 0) & (game.getWuerfelCount()<3)) {
//				game.setWuerfelCount(game.getWuerfelCount()+1);
//				game.setAktuellerSpieler(game.getAktuellerSpieler());
//			} else { game.setWuerfelCount(3); }	
//		}
//		
//		if(game.getAktuellerSpieler().equals("spieler4")) {
//			if((game.getGameField().getField_yellow_1() != 0) & (game.getGameField().getField_yellow_2() != 0) & (game.getGameField().getField_yellow_3() != 0) & (game.getGameField().getField_yellow_4() != 0) & (game.getWuerfelCount()<3)) {
//				game.setWuerfelCount(game.getWuerfelCount()+1);
//				game.setAktuellerSpieler(game.getAktuellerSpieler());
//			} else { game.setWuerfelCount(3); }	
//		}	
//	}
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
		
		if(spielFeldNummer<100) {
			
			//häuser
			
			//BLAUES HAUS
			if(spielfeld.getFields().get(spielFeldNummer-1).getState() ==1){
				if(spielFeldNummer+diceNumber>40){
					System.out.println("Häusersprung "+(spielFeldNummer+diceNumber));
					
					if(spielFeldNummer+diceNumber ==41){
						if(spielfeld.getField_blue_house_1() == 1)
							return false;
						spielfeld.setField_blue_house_1(1);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==42){
						if(spielfeld.getField_blue_house_2() == 1)
							return false;
						spielfeld.setField_blue_house_2(1);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==43){
						if(spielfeld.getField_blue_house_3() == 1)
							return false;
						spielfeld.setField_blue_house_3(1);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==44){
						if(spielfeld.getField_blue_house_4() == 1)
							return false;
						spielfeld.setField_blue_house_4(1);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					
					if(spielFeldNummer+diceNumber>44){
						return false;
					}
				}
			}
			//ROTES HAUS
			if(spielfeld.getFields().get(spielFeldNummer-1).getState() ==2){
				if(spielFeldNummer+diceNumber>10 && spielFeldNummer<=10){
					System.out.println("Häusersprung "+(spielFeldNummer+diceNumber));
					
					if(spielFeldNummer+diceNumber ==11){
						if(spielfeld.getField_red_house_1() == 2)
							return false;
						spielfeld.setField_red_house_1(2);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==12){
						if(spielfeld.getField_red_house_2() == 2)
							return false;
						spielfeld.setField_red_house_2(2);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==13){
						if(spielfeld.getField_red_house_3() == 2)
							return false;
						spielfeld.setField_red_house_3(2);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==14){
						if(spielfeld.getField_red_house_4() == 2)
							return false;
						spielfeld.setField_red_house_4(2);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					
					if(spielFeldNummer+diceNumber>14){
						return false;
					}
				}
			}
			
			//GRÜNES HAUS
			
			if(spielfeld.getFields().get(spielFeldNummer-1).getState() ==3){
				if(spielFeldNummer+diceNumber>20 && spielFeldNummer<=10){
					System.out.println("Häusersprung "+(spielFeldNummer+diceNumber));
					
					if(spielFeldNummer+diceNumber ==21){
						if(spielfeld.getField_green_house_1() == 3)
							return false;
						spielfeld.setField_green_house_1(3);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==22){
						if(spielfeld.getField_green_house_2() == 3)
							return false;
						spielfeld.setField_green_house_2(3);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==23){
						if(spielfeld.getField_green_house_3() == 3)
							return false;
						spielfeld.setField_green_house_3(3);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==24){
						if(spielfeld.getField_green_house_4() == 3)
							return false;
						spielfeld.setField_green_house_4(3);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					
					if(spielFeldNummer+diceNumber>24){
						return false;
					}
				}
			}
			
			//GELBES HAUS
			
			if(spielfeld.getFields().get(spielFeldNummer-1).getState() ==4){
				if(spielFeldNummer+diceNumber>30 && spielFeldNummer<=10){
					System.out.println("Häusersprung "+(spielFeldNummer+diceNumber));
					
					if(spielFeldNummer+diceNumber ==31){
						if(spielfeld.getField_yellow_house_1() == 4)
							return false;
						spielfeld.setField_yellow_house_1(4);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==32){
						if(spielfeld.getField_yellow_house_2() == 4)
							return false;
						spielfeld.setField_yellow_house_2(4);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==33){
						if(spielfeld.getField_yellow_house_3() == 4)
							return false;
						spielfeld.setField_yellow_house_3(4);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					if(spielFeldNummer+diceNumber ==34){
						if(spielfeld.getField_yellow_house_4() == 4)
							return false;
						spielfeld.setField_yellow_house_4(4);
						spielfeld.getFields().get(spielFeldNummer-1).setState(0);
					}
					
					if(spielFeldNummer+diceNumber>34){
						return false;
					}
				}
			}
			
			
			
			//Normales spielen innerhalb des Feldes außerhalb von Startpositionen oder Häusern
			
			int zielFeld = spielFeldNummer+diceNumber-1;
			if((zielFeld>39)){
				zielFeld=zielFeld-40;
			}
			
			int zielFeldValue = spielfeld.getFields().get(zielFeld).getState();
			
			
			
			
			if(zielFeldValue==spielfeld.getFields().get(spielFeldNummer-1).getState()) return false;
			if(zielFeldValue==0) {
				spielfeld.getFields().get(zielFeld).setState(spielfeld.getFields().get(spielFeldNummer-1).getState());
				spielfeld.getFields().get(spielFeldNummer-1).setState(0);
				return true;
			} else {
				figurSchmeissen(game, zielFeld);
				spielfeld.getFields().get(zielFeld).setState(spielfeld.getFields().get(spielFeldNummer-1).getState());
				spielfeld.getFields().get(spielFeldNummer-1).setState(0);
				return true;
			}		
		} else {
			if(diceNumber!=6) return false;
			if(spielFeldNummer<=104) {
				
				if(spielfeld.getFields().get(0).getState()==1) { //startfeld hat schon eigenen figur?
					return false;
				} else {
					
					if(spielfeld.getField_blue_4() ==1){
						if(spielfeld.getFields().get(0).getState() !=0){
							figurSchmeissen(game, 0);
						}
						spielfeld.getFields().get(0).setState(1);
						spielfeld.setField_blue_4(0);
					}else{
						if(spielfeld.getField_blue_3() ==1){
							if(spielfeld.getFields().get(0).getState() !=0){
								figurSchmeissen(game, 0);
							}
							spielfeld.getFields().get(0).setState(1);
							spielfeld.setField_blue_3(0);
						}else{
							if(spielfeld.getField_blue_2() ==1){
								if(spielfeld.getFields().get(0).getState() !=0){
									figurSchmeissen(game, 0);
								}
								spielfeld.getFields().get(0).setState(1);
								spielfeld.setField_blue_2(0);
							}else{
								if(spielfeld.getField_blue_1() ==1){
									if(spielfeld.getFields().get(0).getState() !=0){
										figurSchmeissen(game, 0);
									}
									spielfeld.getFields().get(0).setState(1);
									spielfeld.setField_blue_1(0);
								}
							}	
						}
					}
				}
			}
		
			
						//ROT-start
						
					if(spielFeldNummer<=108&spielFeldNummer>=105) {
						
						if(spielfeld.getFields().get(10).getState()==2) { //startfeld hat schon eigenen figur?
							return false;
						} else {
							
							if(spielfeld.getField_red_4() ==2){
								if(spielfeld.getFields().get(10).getState() !=0){
									figurSchmeissen(game, 10);
								}
								spielfeld.getFields().get(10).setState(2);
								spielfeld.setField_red_4(0);
							}else{
								if(spielfeld.getField_red_3() ==2){
									if(spielfeld.getFields().get(10).getState() !=0){
										figurSchmeissen(game, 10);
									}
									spielfeld.getFields().get(10).setState(2);
									spielfeld.setField_red_3(0);
								}else{
									if(spielfeld.getField_red_2() ==2){
										if(spielfeld.getFields().get(10).getState() !=0){
											figurSchmeissen(game, 10);
										}
										spielfeld.getFields().get(10).setState(2);
										spielfeld.setField_red_2(0);
									}else{
										if(spielfeld.getField_red_1() ==2){
											if(spielfeld.getFields().get(10).getState() !=0){
												figurSchmeissen(game, 10);
											}
											spielfeld.getFields().get(10).setState(2);
											spielfeld.setField_red_1(0);
										}
									}	
								}
							}
						}
					}
					
					//GRÜN-start
					
					if(spielFeldNummer<=109&spielFeldNummer>=112) {
						
						if(spielfeld.getFields().get(20).getState()==3) { //startfeld hat schon eigenen figur?
							return false;
						} else {
							
							if(spielfeld.getField_green_4() ==3){
								if(spielfeld.getFields().get(20).getState() !=0){
									figurSchmeissen(game, 20);
								}
								spielfeld.getFields().get(20).setState(3);
								spielfeld.setField_green_4(0);
							}else{
								if(spielfeld.getField_green_3() ==3){
									if(spielfeld.getFields().get(20).getState() !=0){
										figurSchmeissen(game, 20);
									}
									spielfeld.getFields().get(20).setState(3);
									spielfeld.setField_green_3(0);
								}else{
									if(spielfeld.getField_green_2() ==3){
										if(spielfeld.getFields().get(20).getState() !=0){
											figurSchmeissen(game, 20);
										}
										spielfeld.getFields().get(20).setState(3);
										spielfeld.setField_green_2(0);
									}else{
										if(spielfeld.getField_green_1() ==3){
											if(spielfeld.getFields().get(20).getState() !=0){
												figurSchmeissen(game, 20);
											}
											spielfeld.getFields().get(20).setState(3);
											spielfeld.setField_green_1(0);
										}
									}	
								}
							}
						}
					}
					if(spielFeldNummer<=113&spielFeldNummer>=116) {
						
						if(spielfeld.getFields().get(30).getState()==4) { //startfeld hat schon eigenen figur?
							return false;
						} else {
					
					if(spielfeld.getField_yellow_4() ==4){
						if(spielfeld.getFields().get(30).getState() !=0){
							figurSchmeissen(game, 30);
						}
						spielfeld.getFields().get(30).setState(4);
						spielfeld.setField_yellow_4(0);
					}else{
						if(spielfeld.getField_yellow_3() ==4){
							if(spielfeld.getFields().get(30).getState() !=0){
								figurSchmeissen(game, 30);
							}
							spielfeld.getFields().get(30).setState(4);
							spielfeld.setField_yellow_3(0);
						}else{
							if(spielfeld.getField_yellow_2() ==4){
								if(spielfeld.getFields().get(30).getState() !=0){
									figurSchmeissen(game, 30);
								}
								spielfeld.getFields().get(30).setState(4);
								spielfeld.setField_yellow_2(0);
							}else{
								if(spielfeld.getField_yellow_1() ==4){
									if(spielfeld.getFields().get(30).getState() !=0){
										figurSchmeissen(game, 30);
									}
									spielfeld.getFields().get(30).setState(4);
									spielfeld.setField_yellow_1(0);
								}
							}	
						}
					}
				}
			}
					
					
					
					
		}
							
			
//			if(diceNumber!=6) return false;	
//			if(spielFeldNummer<=104) {
//					if(spielfeld.getFields().get(0).getState()==1) { //startfeld leer?
//						return false;
//					} else {
//						
//						
//						
//						
//					
//						if(spielfeld.getFields().get(0).getState()>0) figurSchmeissen(game, 0);
//						spielfeld.getFields().get(0).setState(1);
//						if(spielfeld.getField_blue_4()==1) {
//							spielfeld.setField_blue_4(0); }
//								else {
//								if(spielfeld.getField_blue_3()==1) {
//									spielfeld.setField_blue_3(0); }
//									else {
//										if(spielfeld.getField_blue_2()==1) {
//											spielfeld.setField_blue_2(0); }
//											else {
//												if(spielfeld.getField_blue_1()==1) {
//													spielfeld.setField_blue_1(0);
//													}
//												}
//											}
//										}
//				if(spielFeldNummer<=108&spielFeldNummer>=105) {
//					if(spielfeld.getFields().get(10).getState()==2) { //startfeld leer?
//						return false;
//					}else {
//							if(spielfeld.getFields().get(10).getState()!=2 && spielfeld.getFields().get(10).getState() !=0 ) figurSchmeissen(game, 10);
//									spielfeld.getFields().get(10).setState(2);
//								if(spielfeld.getField_red_4()==2) {
//									spielfeld.setField_red_4(0); }
//								else{
//									if(spielfeld.getField_red_3()==2) {
//										spielfeld.setField_red_3(0); }
//										else {
//											if(spielfeld.getField_red_2()==2) {
//												spielfeld.setField_red_2(0); }
//												else {
//													if(spielfeld.getField_red_1()==2) {
//														spielfeld.setField_red_1(0);
//														}
//													}
//										}
//									}
//								}
//							}
//						}
//					}
//			}
		
		return true;
	}
	

	private void figurSchmeissen(Game game, int zielFeld) {
		
		System.out.println("Figurschmeissen Feld:"+zielFeld);
		
		GameField spielfeld = game.getGameField();
		int zielFeldValue = spielfeld.getFields().get(zielFeld).getState();
//		
		if(zielFeldValue==1) {
			if(spielfeld.getField_blue_1() ==0){
				spielfeld.setField_blue_1(1);
			}else{
			if(spielfeld.getField_blue_2() ==0){
				spielfeld.setField_blue_2(1);
			}else{
			if(spielfeld.getField_blue_3() ==0){
				spielfeld.setField_blue_3(1);
			}else{
			if(spielfeld.getField_blue_4() ==0){
				spielfeld.setField_blue_4(1);
		}}}}}
		
		if(zielFeldValue==2) {
			if(spielfeld.getField_red_1() ==0){
				spielfeld.setField_red_1(2);
		}else{
			if(spielfeld.getField_red_2() ==0){
				spielfeld.setField_red_2(2);
		}else{
			if(spielfeld.getField_red_3() ==0){
				spielfeld.setField_red_3(2);
		}else{
			if(spielfeld.getField_red_4() ==0){
				spielfeld.setField_red_4(2);
		}}}}}
		
		if(zielFeldValue==3) {
			if(spielfeld.getField_green_1() ==0){
				spielfeld.setField_green_1(3);
		}else{
			if(spielfeld.getField_green_2() ==0){
				spielfeld.setField_green_2(3);
		}else{
			if(spielfeld.getField_green_3() ==0){
				spielfeld.setField_green_3(3);
		}else{
			if(spielfeld.getField_green_4() ==0){
				spielfeld.setField_green_4(3);
		}}}}}
		
		if(zielFeldValue==4) {
			if(spielfeld.getField_yellow_1() ==0){
				spielfeld.setField_yellow_1(4);
		}else{
			if(spielfeld.getField_yellow_2() ==0){
				spielfeld.setField_yellow_2(4);
		}else{
			if(spielfeld.getField_yellow_3() ==0){
				spielfeld.setField_yellow_3(4);
		}else{
			if(spielfeld.getField_yellow_4() ==0){
				spielfeld.setField_yellow_4(4);
		}}}}}
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
		GameField spielfeld = game.getGameField();
		if(spielFeldNummer>99) {
			if(game.getAktuellerSpieler()==0) {
				if(spielFeldNummer>=100&spielFeldNummer<=104) return true;
			}
			if(game.getAktuellerSpieler()==1) {
				if(spielFeldNummer>=105&spielFeldNummer<=108) return true;
			}
			if(game.getAktuellerSpieler()==2) {
				if(spielFeldNummer>=109&spielFeldNummer<=112) return true;
			}
			if(game.getAktuellerSpieler()==3) {
				if(spielFeldNummer>=116&spielFeldNummer<=119) return true;
			} 
		}

		
		
		if(spielfeld.getFields().get(spielFeldNummer-1).getState() == 0){
			System.out.println("Keine Spielfigur auf angeklickter Stelle");
			return false;
		}
		

		
		if(game.getAktuellerSpieler()+1 == spielfeld.getFields().get(spielFeldNummer-1).getState()) { 
	//		System.out.println("Spieler "+ game.getAktuellerSpieler()+1+" setzt eigene Figur mit Farbe "+spielfeld.getFields().get(spielFeldNummer-1).getState());
			return true;
		}
		
		return false;
	}
	
		/**
	 * Wird von der Android App aufgerufen und holt sich die HighscoreListe
	 */
	@Override
	public HighscoreListResponse getHighscoreList() {

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