package de.mensch.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.mensch.entities.Account;
import de.mensch.entities.Customer;

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
	private String username1, password1, username2, password2;

	@PostConstruct
	private void init() {

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