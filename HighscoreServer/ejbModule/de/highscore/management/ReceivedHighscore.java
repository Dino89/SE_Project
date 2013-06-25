package de.highscore.management;
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




/**
 * MDB lauscht auf den Empfang der Highscore Daten die in die Datenbank geschrieben werden sollen.
 * @author 
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
		dao.setHighscore("Max", 1);
		dao.setHighscore("Paul", 2);
		dao.setHighscore("Dino", 3);
		dao.setHighscore("Krimi", 4);
		dao.setHighscore("Matz", 5);
		dao.setHighscore("Malte", 6);
		dao.setHighscore("Beno", 1);
		dao.setHighscore("Krampe", 2);
		dao.setHighscore("Rex", 1);
		dao.setHighscore("Sense", 3);
		dao.setHighscore("Lukas", 2);
		dao.setHighscore("Tech", 1);
		dao.setHighscore("Viktor", 5);
		dao.setHighscore("Memmet", 2);
		dao.setHighscore("Blümchen88", 4);
		dao.setHighscore("Baum03", 2);
		dao.setHighscore("David", 7);
		dao.setHighscore("Thorsten", 2);
		//Ende Testdaten
		
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
    }
	
	
	

		




}
