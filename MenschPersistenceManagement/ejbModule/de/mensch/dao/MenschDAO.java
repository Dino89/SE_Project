package de.mensch.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import de.mensch.entities.Account;
import de.mensch.entities.Customer;
import de.mensch.entities.Game;
import de.mensch.entities.MenschSession;

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

	@Override
	public ArrayList<Game> getGameList() {
//		return em.find(Games.class, id);
//	    public List<Person> findAllPersons(String Id) {
//	        EntityManagerFactory factory = Persistence
//	                .createEntityManagerFactory("persistenceUnitName");
//	        em = factory.createEntityManager();
		
//	        em.getTransaction().begin();
	        ArrayList<Game> response = null;
	        em.createNamedQuery("SELECT id FROM Customer");
	        System.out.println(response);
	        for (Game game : response) {
//	        	response.add()
	        }
//	        response = (ArrayList<Game>) em.createQuery(
//	                "SELECT id FROM Game").getResultList();
////	        em.getTransaction().commit();
////	        em.close();
////	        factory.close();
//	        System.out.println(response);
//	        if (response == null) {
//	        	Game g = new Game();
//	            response.add(g);
//	        } else {
//	            for (Game game : response) {
//	            System.out.println("Person name= " + game.getId() + ", gender" + game.getOwner());
//	            }
//	        }
	 return response;
	 }
}

