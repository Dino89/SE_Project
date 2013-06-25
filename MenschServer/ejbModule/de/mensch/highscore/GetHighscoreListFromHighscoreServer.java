package de.mensch.highscore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
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

import de.mensch.entities.Customer;

/**
 * Diese Methode sendet den Befehl zum erzeugen der HighscoreListe an den HighscoreServer.
 * Der HighscoreServer antwortet dann mit der HighscoreListe
 *  
 * @author 
 *
 */

@Stateless
@LocalBean
@Resource(name="jms/getHighscoreList")
public class GetHighscoreListFromHighscoreServer {
	
	//Def. Var.
	 private static Queue queue = null;
	   QueueConnectionFactory factory = null;
	   QueueConnection connection = null;
	   QueueSender sender = null;
	   QueueSession session = null;
	   
	   public void getHighscoreListFromServer(){
		   
		    
		    final String QUEUE_LOOKUP = "queue/getHighscoreList";
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
			     
			     
			     msg.setStringProperty("befehl", "getList");
			     producer.send(msg);
			     System.out.println("Aus Methode getHighscoreList: Befehl gesendet! :)");
			     session.close();
			    } 
		    	catch (Exception ex) {
		    		ex.printStackTrace();
			    }
	   }
}