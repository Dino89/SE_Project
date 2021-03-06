package de.mensch.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlTransient;


import de.mensch.entities.Customer;
import de.mensch.entities.Dice;
import de.mensch.entities.Game;
import de.mensch.entities.GameField;
import de.mensch.entities.MenschSession;
import de.mensch.entities.Request;


/**
 * Session Bean implementation class MenschDAO
 */
@Stateless
public class MenschDAO implements MenschDAOLocal {

	@PersistenceContext
	private EntityManager em;
	
	public Customer findCustomerByName(String userName){
		return em.find(Customer.class, userName);
	}
	
	
	public MenschSession findSessionById(int id) {
		return em.find(MenschSession.class, id);
	}
	
	public int createSession(Customer user) {
		MenschSession newSession = new MenschSession(user);
		em.persist(newSession);
		return newSession.getId();
	}

	public void closeSession(int id) {
		MenschSession session = em.find(MenschSession.class, id);
		em.remove(session);
	}
	
	public Customer registerCustomer(String username, String password) {
		Customer customer = new Customer();
		customer.setUserName(username);
		customer.setPassword(password);
		em.persist(customer);
		return customer;
	}

	public Game getGame(int gameid){
		return em.find(Game.class, gameid);
	}
	public void removeGame(int gameid){
		Game g = em.find(Game.class, gameid);
		em.remove(g);
	}
	
	@Override
	public ArrayList<Game> getGameList() {	
//		ALT: 		Query query = em.createQuery("SELECT e FROM Game e where started=false");
		Query query = em.createQuery("SELECT e FROM Game e");
		return (ArrayList<Game>) query.getResultList();
	 }

	@Override
	public Game getGameDetails(int id) {
		System.out.println("HIER"+em.find(Game.class, id).getOwner().getUserName());
		return em.find(Game.class, id);
	}

	@Override
	public Game getGameFields(int id) {
		return em.find(Game.class, id);
	}
	//TODO: NOT FINISHED
	@Override
	public Game createGame(Customer owner) {
		Game game = new Game();
		game.setOwner(owner);
		game.setSpieler1(owner);
		GameField gf = new GameField();
		gf.init();
		em.persist(gf);
		
		game.setGameField(gf);
		
		em.persist(game);
		return game;
	}
	
	@Override
	public ArrayList<Request> getRequests(int gameid) {
		Query query = em.createQuery("SELECT e FROM Request e where gameentity="+gameid+" AND pulledByHost=false");
		ArrayList<Request> querylist = (ArrayList<Request>) query.getResultList();
		return (ArrayList<Request>) query.getResultList();
		
	}
	
	@Override
	public ArrayList<Request> getAllRequests() {
		Query query = em.createQuery("SELECT e FROM Request e");
		ArrayList<Request> querylist = (ArrayList<Request>) query.getResultList();
		return querylist;
	}
	@Override
	public Request getRequest(int requestid) {
		return em.find(Request.class, requestid);
		
	}
	//TODO: NOT FINISHED
	@Override
	public Request createRequest(int gameid, String username) {
		Request request = new Request();
		request.setUser(username);
		Game game = em.find(Game.class, gameid);
		request.setGameentity(game);
		
		em.persist(request);
		return request;
	}

	@Override
	public Game findGameByOwnerUserName(Customer owner) {
		Query query = em.createQuery("SELECT e FROM Game e");
		ArrayList<Game>result = (ArrayList<Game>) query.getResultList();
		String user1, user2;
		
		for(Game respone : result) {
			user1 = respone.getOwner().getUserName();
			user2 = owner.getUserName();
			if(user1.equals(user2)) {
				return respone;
			}
		}
		return null;
	}

	@Override
	public void removeRequest(int requestId) {
		Request r = em.find(Request.class, requestId);
		em.remove(r);
	}

	@Override
	public ArrayList<MenschSession> findSessions() {	
		Query query = em.createQuery("SELECT e FROM MenschSession e");
		return (ArrayList<MenschSession>) query.getResultList();
	}
	
	@Override
	public MenschSession findSessionByUserName(String userName) {
		Query query = em.createQuery("SELECT e FROM MenschSession e where username="+userName);
		return (MenschSession) query.getSingleResult();
	}
	
	
	@Override
	public ArrayList<Request> getZuschauerListe(int gameid) {
		Query query = em.createQuery("SELECT e FROM Zuschauer e where game="+gameid);
		ArrayList<Request> querylist = (ArrayList<Request>) query.getResultList();
		return (ArrayList<Request>) query.getResultList();
	}
	@Override
	public Game findGameById(int gameid) {
		return em.find(Game.class, gameid);
	}

	@Override
	public Dice createDiceNumber() {
		Dice dice = new Dice();
		System.out.println("Zufallszahl aus MenschDAO");
		dice.setNumber((int) Math.round(Math.random()*100%7));
		dice.setId(dice.getId());
		em.persist(dice);
		return dice;
	}

	@Override
	public ArrayList<MenschSession> getZuschauer(int gameid) {
		Query query = em.createQuery("SELECT zuschauer FROM Game e where id="+gameid);
		ArrayList<MenschSession> querylist = (ArrayList<MenschSession>) query.getResultList();
		return (ArrayList<MenschSession>) query.getResultList();
	}

	@Override
	public ArrayList<Dice> getDiceList() {
		Query query = em.createQuery("SELECT e FROM Dice e");
		ArrayList<Dice> querylist = (ArrayList<Dice>) query.getResultList();
		System.out.println("menschdao querylist "+querylist);
		System.out.println((ArrayList<Dice>) query.getResultList());
		return (ArrayList<Dice>) query.getResultList();
	}
}
