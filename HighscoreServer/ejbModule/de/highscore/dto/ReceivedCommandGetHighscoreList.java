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

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/getHighscoreList") })
public class ReceivedCommandGetHighscoreList implements MessageListener  {
	
	SendHighscoreList shList = new SendHighscoreList();
	@Override
	public void onMessage(Message message) {
		try {


		ObjectMessage msg = (ObjectMessage) message;
		if(msg.getStringProperty("befehl").equals("getList")) {
			shList.sendHighscoreList();
			System.out.println("Vergleich OK");
		}
		
		

		
		
		
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
    }
	
	
	

		




}
