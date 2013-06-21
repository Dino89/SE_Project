package de.mensch.highscore;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;



@Stateless
@LocalBean
@Resource(name="jms/Pretech")
public class SendHighscore {
 
 private static Queue queue = null;
   QueueConnectionFactory factory = null;
   QueueConnection connection = null;
   QueueSender sender = null;
   QueueSession session = null;

   public void sendLetter(String test){
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
     QueueSender sender = session.createSender(queue);
     
     // send message
     TextMessage message = session.createTextMessage();
     message.setText("DJ Nachricht aus lokale Bean");
     sender.send(message);
     
     System.out.println("Message sent");
     session.close();
    } catch (Exception e) {
     e.printStackTrace();
    }
    
//    connection = factory.createQueueConnection();
//    session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
//    sender = session.createSender(queue);
//    //create and set a message to send
//    TextMessage msg = session.createTextMessage();
//    for (int i = 0; i < 5; i++) {
//    msg.setText("This is my sent message " + (i + 1));
//    //finally client sends messages 
//    //asynchronously to the queue
//    sender.send(msg);
//    } 
// 
//    System.out.println("Sending message"); 
//    session.close ();
//    } catch (Exception e) {
//     e.printStackTrace ();
//    }
   }
}