package de.mensch.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.mensch.entities.Account;
import de.mensch.entities.Customer;
import de.mensch.entities.Game;

/**
 * Klasse KontoRegistry als Singleton. Alle vorhandenen Konto-Objekte sollen hier registriert werden, damit die KontoRegistry
 * als zentrales Kontoverzeichnis fungieren kann.
 */
@Startup
@Singleton
public class DataBuilder {
	
	@PersistenceContext
	EntityManager em;
	
	@Resource
	private String username1, password1, username2, password2, owner1, owner2;
	private int id, id2;

	@PostConstruct
	private void init() {
		
		Customer customer1 = new Customer("username1", "password1");
		em.persist(customer1);

		Game game1 = new Game();
		//game1.setId(id);
		game1.setOwner(customer1);
		em.persist(game1);
		
		Customer customer2 = new Customer("username2", "password2");
		em.persist(customer2);


		Game game2 = new Game();
		//game2.setId(id2);
		game2.setOwner(customer2);
		em.persist(game2);
		System.out.println("game + customer angelegt");
		

	}
	
}