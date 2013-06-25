package de.highscore.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import de.highscore.dao.HighscoreDAO;
import de.highscore.dao.HighscoreDAOLocal;
import de.highscore.entities.Highscore;
//import de.highscore.online.HighscoreOnlineIntegrationImpl;



/**
 * Sendet die HighscoreListe an den MenschServer
 * @author 
 *
 */

@Stateless
@LocalBean
@Resource(name="jms/sendHighscoreListToApp")
public class SendHighscoreList {
	
	//Def. Var.
	 private static Queue queue = null;
	   QueueConnectionFactory factory = null;
	   QueueConnection connection = null;
	   QueueSender sender = null;
	   QueueSession session = null;
	   


	   public void sendHighscoreList(String topTwenty){
		   	
		    final String QUEUE_LOOKUP = "queue/sendHighscoreListToApp";
		    final String CONNECTION_FACTORY = "ConnectionFactory";
    
		    try {
			     //client creates the connection, session, and message sender:
			     Context context = new InitialContext();
			     QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(CONNECTION_FACTORY);
			     QueueConnection connection = factory.createQueueConnection();
			     QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			     Queue queue = (Queue) context.lookup(QUEUE_LOOKUP);
			     QueueSender producer = session.createSender(queue);

			     ObjectMessage msg = session.createObjectMessage();

			     msg.setObject(topTwenty);

			     producer.send(msg);
			     session.close();
			    } 
		    	catch (Exception ex) {
		    		ex.printStackTrace();
			    }
	   }
}