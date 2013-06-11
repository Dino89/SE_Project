package de.mensch.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.mensch.entities.Account;
import de.mensch.entities.Customer;
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
	
}
