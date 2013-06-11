package de.mensch.onlineservice;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import de.mensch.dao.MenschDAOLocal;
import de.mensch.entities.*;
import de.mensch.onlineservice.InvalidLoginException;


/**
 * @author 
 * Diese Session Bean implementiert das fuer das Spiel bereitgestellte Interface.
 *
 */
@Stateful
@Remote(MenschOnlineService.class)
public class MenschOnlineServiceImpl implements MenschOnlineService {

	/**
	 * Hier wird der angemeldete User einer Session gespeichert.
	 */
	private Customer user;
	
	/**
	 * EJB zur Abfrage von Customer-Datensätzen
	 * Referenz auf die EJB wird per Dependency Injection gefüllt.
	 * Der hier angegebene Name könnte später über einen Deployment Deskriptor geändert werden. 
	 */
	@EJB(beanName = "MenschDAO", beanInterface = de.mensch.dao.MenschDAOLocal.class)
	private MenschDAOLocal dao;
	
//	Die folgende Initialisierungsmethode kann entfallen, da wir nun mit Dependency Injection arbeiten.
//	/**
//	 * Anstelle eines Konstruktors wird diese Methode vom Container aufgerufen, um die Initialisierung
//	 * durchzuführen. Sie holt sich Referenzen auf benötigte andere EJBs.
//	 * @throws NamingException
//	 */
//	@PostConstruct
//	private void init() throws NamingException {
//	  Context context = new InitialContext();	
//	  this.customerRegistry = (CustomerRegistry) context.lookup(CUSTOMER_REGISTRY);
//	  this.accountRegistry = (AccountRegistry) context.lookup(ACCOUNT_REGISTRY);
//	}
	
	@Override
	public boolean login(String username, String password) throws InvalidLoginException {
		boolean success = false;
		this.user = this.dao.findCustomerByName(username);		
		if (user != null && user.getPassword().equals(password)) {
			success = true;
			System.out.println("Login erfolgreich.");
		}
		else {
			System.out.println("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username="+username);
			throw new InvalidLoginException("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username="+username);
		}
		return success;
	}

	@Override
	@Remove
	public void logout() throws NoSessionException {
		validateLogin();
		System.out.println("Logout erfolgreich.");
	}

	public String toString() {
		return "Hello, I'm an instance of MenschOnlineServiceImpl!";
	}

	private void validateLogin() throws NoSessionException {
		if (this.user==null) throw new NoSessionException("Bitte zunächst ein Login durchführen.");
	}

	@Override
	public boolean diceNumber() {
		System.out.println("diceNumber" + diceNumber());
		boolean success = true;
		return success;
	}
	
}
