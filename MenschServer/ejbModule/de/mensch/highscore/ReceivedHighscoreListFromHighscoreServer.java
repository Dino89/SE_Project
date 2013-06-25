package de.mensch.highscore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import de.mensch.dao.MenschDAOLocal;

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
 * MDB lauscht auf den Empfang der HighscoreListe vom HighscoreServer
 * @author 
 *
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/sendHighscoreListToApp") })
public class ReceivedHighscoreListFromHighscoreServer implements MessageListener  {
	

	
	public static String highscoreListeFromHighscoreServer = null;
	
	/**
	 * 
	 */
	@Override
	public void onMessage(Message message) {
		try {

		ObjectMessage msg = (ObjectMessage) message;
		String topTwenty = msg.getObject().toString();

		highscoreListeFromHighscoreServer = topTwenty; 
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		

    }
	

	
	
	
	

		




}
