package de.highscore.dto;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

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
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/Pretech") })


public class ReceivedHighscore implements MessageListener  {
	
	@Override
	public void onMessage(Message message) {
       try {
    	  TextMessage msg = (TextMessage) message;
          System.out.println("Received message from queue/Highscore: " + msg.getText());
       }
       catch (JMSException e) {
            //throw new EJBException(e);
       }
    }
		




}
