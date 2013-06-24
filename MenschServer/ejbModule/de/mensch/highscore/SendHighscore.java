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
 * Schreibe Highscoreeinträge in die HighscoreDatenbank
 * @author Christopher
 *
 */

@Stateless
@LocalBean
@Resource(name="jms/Pretech")
public class SendHighscore {
	
	//Def. Var.
	 private static Queue queue = null;
	   QueueConnectionFactory factory = null;
	   QueueConnection connection = null;
	   QueueSender sender = null;
	   QueueSession session = null;
	   
	   /**
	    * Wenn ein Spieler das Spiel vorzeitig verlässt.
	    * @param user
	    */
	   public void highscorePoinsForLeavingGame(Customer user) {
		   sendHighscoreList(user.getUserName(), -1); 
	   }
	   
	   /**
	    * Wenn ein Spieler fertig ist(alle Figuren stehen im Haus).
	    * @param finishingPlayer
	    * @param remainingPlayers
	    */
	   public void highscorePointsForFinishingGame(Customer finishingPlayer, int remainingPlayers) {
		   sendHighscoreList(finishingPlayer.getUserName(), remainingPlayers);
	   }
	   
	   /**
	    * 
	    * @param userName
	    * @param credits
	    */
	   public void sendHighscoreList(String userName, Integer credits){
		   
		    System.out.println("in methode der lokalen Bean");
		    final String QUEUE_LOOKUP = "queue/Pretech";
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
			     
			     
			     msg.setStringProperty("name", userName);
			     msg.setIntProperty("credits", credits);
			     producer.send(msg);
			     System.out.println("Aus Methode sendHighscoreList: Liste gesendet! :)");
			     session.close();
			    } 
		    	catch (Exception ex) {
		    		ex.printStackTrace();
			    }
	   }
}