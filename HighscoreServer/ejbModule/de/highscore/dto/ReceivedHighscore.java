package de.highscore.dto;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
		activationConfig = {  
			 @ActivationConfigProperty(
			      propertyName = "destinationType",
			      propertyValue = "javax.jms.Queue"),
			 @ActivationConfigProperty(
			      propertyName = "destination",
			      propertyValue = "queue/Highscore"),
			 @ActivationConfigProperty(
			      propertyName = "messageSelector",
			      propertyValue = "DocType LIKE 'Letter'") })
public class ReceivedHighscore implements MessageListener  {
	
	@Override
	public void onMessage(Message message) {
       try {
    	  TextMessage msg = (TextMessage) message;
          System.out.println("Received message from queue/Highscore: " + msg.getText());
       }
       catch (JMSException e) {
            throw new EJBException(e);
       }
    }
	




}
