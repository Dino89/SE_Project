package de.highscore.dto;
import java.util.ArrayList;
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
import de.highscore.entities.Highscore;

//Unter Window Server Properties die xml geändert
//<jms-destinations>
//<jms-queue name="testQueue">
//    <entry name="queue/Pretech"/>
//    <entry name="java:jboss/exported/jms/queue/test"/>
//</jms-queue>
//<jms-topic name="testTopic">
//    <entry name="topic/test"/>
//    <entry name="java:jboss/exported/jms/topic/test"/>
//</jms-topic>
//</jms-destinations>


/**
 * MDB lauscht auf den Empfang des Befehls vom MenschServer zum senden der HighscoreList
 * @author Christopher
 *
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/getHighscoreList") })
public class ReceivedCommandGetHighscoreList implements MessageListener  {
	
	@EJB(beanName = "HighscoreDAO", beanInterface = de.highscore.dao.HighscoreDAOLocal.class)
	private HighscoreDAOLocal dao;
	
	SendHighscoreList shList = new SendHighscoreList();
	@Override
	public void onMessage(Message message) {
		try {


		ObjectMessage msg = (ObjectMessage) message;
		if(msg.getStringProperty("befehl").equals("getList")) {
			
			System.out.println("Vergleich OK, starte Senden der Liste");
			String topTwenty = null;
			topTwenty = dao.getTopTwenty().toString();
			
			System.out.println("aus receivedCommand der String:  " + topTwenty);
			shList.sendHighscoreList(topTwenty);
			
	
		}

		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
    }
	
	
	

		




}
