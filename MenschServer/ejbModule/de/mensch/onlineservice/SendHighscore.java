package de.mensch.onlineservice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;



@Stateless
@LocalBean
public class SendHighscore {
	
	@Resource(mappedName="java:/JmsXA")
	  private ConnectionFactory jmsFactory;
	  
	  @Resource(mappedName="java:/queue/Highscore")
	  private Queue outputQueue;
	  
	  private Connection jmsConnection;

	  @PostConstruct
	  private void startConnection() {
	    try {
			jmsConnection = jmsFactory.createConnection();
		    jmsConnection.start();
	    } catch (JMSException e) {
			// TODO replace with output to logging framework	    	
			e.printStackTrace();
		}
	  }

	  @PreDestroy
	  private void closeConnection() {
	    try {
			jmsConnection.close();
		} catch (JMSException e) {
			// TODO replace with output to logging framework
			e.printStackTrace();
		}
	  }

	  public void printLetter(String letter) {
		Session session = null;
		try {
			session = jmsConnection.createSession(true,Session.SESSION_TRANSACTED);
			TextMessage message = session.createTextMessage();
			message.setStringProperty("DocType", "Letter");
			message.setText(letter);
			MessageProducer sender = session.createProducer(outputQueue);
			sender.send(message);
			session.close();
		}
		catch (JMSException e) {
			// TODO replace with output to logging framework			
			e.printStackTrace();
		}  
	  }


}
