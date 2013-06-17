package com.example.menschapp.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author Thoene
 * Dieses Interface definiert die Schnittstelle zwischen Onlinebanking-Client und -Server.
 * Es wird sowohl als Local- als auch als Remote-Business-Interface benutzt. 
 */
public interface MenschSystem {
	
	/**
	 * Operation zum Einloggen mit Username und Password.
	 * @param username
	 * @param password
	 * @return
	 * @throws PasswortUngueltigException 
	 * @throws UsernameUngueltigException 
	 */
//	public Kunde login(String username, String password);
	
	/**
	 * Operation zum Ausloggen. Schliesst die Session des Nutzers.
	 * @param sessionID
	 */
	public void logout();


	public double diceNumber();
	

	public String register(String username, String password);


	public Kunde login(String username, String password);


	public ArrayList<Games> getGames();


	public Games getGameDetails(int id);


	public Games createGame();

	
	/**
	 * Operation zum Auslesen aller eigenen Konten
	 * @return
	 */
//	public Set<Konto> getEigeneKonten();
	
	/**
	 * Operation zur Abfrage eines Kontostandes
	 * @param kontoNr
	 * @return
	 * @throws KontonummerUngueltigException 
	 * @throws KeineBerechtigungException 
	 */
//	public BigDecimal getKontostand(Integer kontoNr);
	
	/**
	 * Operation zur Ueberweisung eines Geldbetrags von einem Quell- zu einem Zielkonto
	 * @param quellKontoNr
	 * @param zielKontoNr
	 * @param betrag
	 * @return
	 * @throws KontonummerUngueltigException 
	 * @throws KeineBerechtigungException 
	 */
//	public BigDecimal ueberweisen(Integer quellKontoNr, Integer zielKontoNr, BigDecimal betrag);

//	/**
//	 * Operation zur Anlage eines neuen Kunden-Datensatzes
//	 * @param userName mit dem eindeutigen Benutzernamen des Kunden
//	 * @param password mit dem Password zur Authentifizierung des Kunden 
//	 * @throws KeineBerechtigungException 
//	 * @throws UsernameUngueltigException 
//	 */
//	public void kundeAnlegen(String userName, String password);
//
//	
//	/**
//	 * Operation zur Anlage eines neuen Kontos (Startsaldo 0) zu einem Kontoinhaber
//	 * @param inhaberName mit dem eindeutigen Benutzernamen des Kontoinhabers
//	 * @return String mit der neuen Kontonummer des angelegten Kontos
//	 * @throws UsernameUngueltigException
//	 * @throws KeineBerechtigungException 
//	 */
//	public Integer kontoAnlegen(String inhaberName);
//	
}
