package de.highscore.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.highscore.entities.Highscore;



/**
 * Klasse KontoRegistry als Singleton. Alle vorhandenen Konto-Objekte sollen hier registriert werden, damit die KontoRegistry
 * als zentrales Kontoverzeichnis fungieren kann.
 */
@Startup
@Singleton
public class DataBuilder {
	
	@PersistenceContext
	EntityManager em;
	



	@PostConstruct
	private void init() {
		
		
		//Highscore h1 = new Highscore("Malte");
//		h1.setPoints(100);
//		Highscore h2 = new Highscore("Daniel");
//		h2.setPoints(100);
//		Highscore h3 = new Highscore("Thöne");
//		h3.setPoints(1337);
//		Highscore h4 = new Highscore("Christopher");
//		h4.setPoints(123);
//		Highscore h5 = new Highscore("Wicht");
//		h5.setPoints(1337);
//		
//		em.persist(h1);
//		em.persist(h2);
//		em.persist(h3);
//		em.persist(h4);
//		em.persist(h5);
		//erzeuge ein paar Beispieldaten zu Kunden und Konten, falls sie noch nicht in der DB vorhanden sind.
//		Customer customer1 = em.find(Customer.class, username1);
//		if (customer1 == null) {
//			//Kunde noch nicht vorhanden, also mit neuem Konto anlegen:
//			customer1 = new Customer(username1, password1);
//			em.persist(customer1);
//			Account account1 = new Account(customer1);
//			em.persist(account1);
//			Account account2 = new Account(customer1);
//			em.persist(account2);
//			System.out.println("Neu angelegt: " + account1);
//			System.out.println("Neu angelegt: " + account2);
//		}
//
//		Customer customer2 = em.find(Customer.class, username2);
//		if (customer2 == null) {
//			//Kunde noch nicht vorhanden, also mit neuem Konto anlegen:
//			customer2 = new Customer(username2, password2);
//			em.persist(customer2);
//			Account account1 = new Account(customer2);
//			em.persist(account1);
//			Account account2 = new Account(customer2);
//			em.persist(account2);
//			System.out.println("Neu angelegt: " + account1);
//			System.out.println("Neu angelegt: " + account2);			
//		}
	}
	
}