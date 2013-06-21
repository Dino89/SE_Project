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

import de.mensch.entities.Account;
import de.mensch.entities.Customer;
import de.mensch.entities.Game;
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
	
	public Account findAccountById(int id) {
		return em.find(Account.class, id);
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
		 em.remove(getGame(gameid));
	}
	
	@Override
	public ArrayList<Game> getGameList() {		
		Query query = em.createQuery("SELECT e FROM Game e where started=false");
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
		em.persist(game);
		return game;
	}
	//TODO: NOT FINISHED
	@Override
	public ArrayList<Request> getRequests(int gameid) {
		Query query = em.createQuery("SELECT e FROM Request e where gameentity="+gameid);
		ArrayList<Request> querylist = (ArrayList<Request>) query.getResultList();
		return (ArrayList<Request>) query.getResultList();
		
	}

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
		// TODO Auto-generated method stub
		em.remove(getRequest(requestId));
	}

	@Override
	public ArrayList<MenschSession> findSessions() {	
		Query query = em.createQuery("SELECT e FROM MenschSession e");
		return (ArrayList<MenschSession>) query.getResultList();
	}
	
	@Override
	public MenschSession findSessionByUserName(String userName) {
		Query query = em.createQuery("SELECT e FROM MenschSession e where username="+userName);
		return (MenschSession) query;
	}
	
}