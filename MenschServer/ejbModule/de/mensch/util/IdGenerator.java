package de.mensch.util;

/**
 * Mit dieser Klasse k�nnen eindeutige IDs vom Typ 'int' generiert werden. Die Klasse ist als Singleton realisiert.
 */
public class IdGenerator {
	
	private static IdGenerator singleInstance = new IdGenerator();
	private int lastID;
	
	private IdGenerator() {
		lastID = 0;
	}

	private int getNextFreeID() {
		this.lastID++;
		return this.lastID;
	}
	
	
	/**
	 * Diese statische Methode greift auf die Singleton-Instanz der Klasse zur�ck, um eine neue ID zu berechnen.
	 * @return eine neue, noch nicht verwendete ID
	 */
	public static int getFreeID() {
		return singleInstance.getNextFreeID();
	}
	
}
