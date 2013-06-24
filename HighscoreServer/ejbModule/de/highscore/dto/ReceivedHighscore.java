package de.highscore.dto;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import de.highscore.*;
import de.highscore.dao.HighscoreDAOLocal;
import de.highscore.online.*;



/**
 * MDB lauscht auf den Empfang der Highscore Daten die in die Datenbank geschrieben werden sollen.
 * @author Christopher
 *
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/Pretech") })
public class ReceivedHighscore implements MessageListener  {
	
	@EJB(beanName = "HighscoreDAO", beanInterface = de.highscore.dao.HighscoreDAOLocal.class)
	private HighscoreDAOLocal dao;
	
	
	
	@Override
	public void onMessage(Message message) {
		try {
		String user = null;
		int credits = 0;

		ObjectMessage msg = (ObjectMessage) message;
		user = msg.getStringProperty("name");
		credits = msg.getIntProperty("credits");
		
		
		dao.setHighscore(user, credits);
		
		//Testdaten
		dao.setHighscore("Max", 2);
		dao.setHighscore("Paul", 4);
		dao.setHighscore("Dino", 2);
		dao.setHighscore("Krimi", 1);
		dao.setHighscore("Matz", 5);
		dao.setHighscore("Malte", 7);
		//Ende Testdaten
		
		System.out.println("Ausgabe aus DB erfolgreich! -->" + dao.getCredits(user));
		
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
    }
	
	
	

		




}
