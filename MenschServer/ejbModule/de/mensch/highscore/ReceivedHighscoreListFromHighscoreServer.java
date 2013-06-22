package de.mensch.highscore;
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





//Unter Window Server Properties die xml ge�ndert
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
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/sendHighscoreListToApp") })
public class ReceivedHighscoreListFromHighscoreServer implements MessageListener  {
	
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("Received die verdammte LISTE!!!");
		//ArrayList highscoreList = new ArrayList();	
		ObjectMessage msg = (ObjectMessage) message;
		String bla = msg.getStringProperty("test");
		

		System.out.println("HighscoreListe vom HighscoreServer empfangen " + bla);
		

		
		
		
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
    }
	
	
	

		




}
