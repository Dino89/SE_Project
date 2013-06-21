package com.example.menschapp.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

/** 
 * Diese Klasse übernimmt später einmal die Kommunikation mit dem Server!
 * @author user
 *
 */
public class MenschSystemStub implements MenschSystem {
	
	/**
	 * Namespace is the targetNamespace in the WSDL.
	 */ 
	private static final String NAMESPACE = "http://onlineservice.mensch.de/";

	/**
	 * The WSDL URL. Its value is the location attribute of the soap:address element for a port
	 * element in a WSDL. Unless the web service is also hosted on the Android device, the hostname 
	 * should not be specified as localhost, because the application runs on the Android device while 
	 * the web service is hosted on the localhost server. Specify hostname as the IP address of the 
	 * server hosting the web service (or "10.0.2.2 instead of 'localhost' when running in the emulator). 
	 */
    private static final String URL = "http://10.0.2.2:8080/MenschServer/MenschOnlineIntegrationImpl";

    /**
	 * TAG contains the class name and is used for logging.
	 */
    private static final String TAG = MenschSystemStub.class.getName();

    /**
     * sessionId contains the session id delivered from the server.
     */
	private static int sessionId;

//	@Override
//	public Kunde login(String username, String password) {
//		Kunde result = null;
//		String METHOD_NAME = "login";
//		SoapObject response = executeSoapAction(METHOD_NAME, username, password);
//		Log.d(TAG, response.toString());
//		this.sessionId = Integer.parseInt(response.getPrimitivePropertySafelyAsString("sessionId"));
//		result = new Kunde(username, password);
//		return result;
//	}
			
	
	@Override
	public void logout(){
		Log.d(TAG,"logout called.");
		String METHOD_NAME = "logout";
		SoapObject response = executeSoapAction(METHOD_NAME, sessionId);
		Log.d(TAG, response.toString());
	}

//	@Override
//	public Set<Konto> getEigeneKonten() {
//		Log.d(TAG,"getEigeneKonten called.");		
//		Set<Konto> result = new HashSet<Konto>();
//		String METHOD_NAME = "getMyAccounts";
//		SoapObject response = executeSoapAction(METHOD_NAME, sessionId);
//		Log.d(TAG, response.toString());
//		//Eigene Konten einlesen:
//		for (int i=1; i<response.getPropertyCount(); i++) {
//			SoapObject soapKontoEntry = (SoapObject) response.getProperty(i);
//			SoapPrimitive soapKontoNr = (SoapPrimitive) soapKontoEntry.getProperty("id");
//			SoapPrimitive soapBetrag = (SoapPrimitive) soapKontoEntry.getProperty("balance");
//			Konto konto = new Konto();
//			konto.setKontoNr(Integer.valueOf(soapKontoNr.toString()));
//			konto.setBetrag(new BigDecimal(soapBetrag.toString()));
//			result.add(konto);
//		}
//		return result;
//	}

//	@Override
//	public BigDecimal getKontostand(Integer kontoNr){
//		Log.d(TAG,"getKontostand called.");
//		String METHOD_NAME = "getBalance";
//		SoapObject response = executeSoapAction(METHOD_NAME, sessionId, kontoNr);
//		Log.d(TAG, response.toString());
//		return new BigDecimal(response.getPrimitivePropertySafelyAsString("balance"));
//	}
//
//	@Override
//	//TODO Diese Methode muesste noch aus der App heraus getestet werden, z.B. Ã¼ber die TransactionsActivity...
//	public BigDecimal ueberweisen(Integer quellKontoNr, Integer zielKontoNr, BigDecimal betrag){
//		Log.d(TAG,"ueberweisen called.");
//		String METHOD_NAME = "transfer";
//		SoapObject response = executeSoapAction(METHOD_NAME, sessionId, quellKontoNr, zielKontoNr, betrag);
//		Log.d(TAG, response.toString());
//		return new BigDecimal(response.getPrimitivePropertySafelyAsString("newBalance"));
//	}

//	@Override
//	public void kundeAnlegen(String userName, String password){
//		Log.d(TAG,"kundeAnlegen executed.");
//	}
//
//	@Override
//	public Integer kontoAnlegen(String inhaberName){
//		Log.d(TAG,"kontoAnlegen executed.");
//		return null;
//	}
//

	/**
	 * Diese Methode delegiert einen Methodenaufruf an den hinterlegten WebService.
	 * @param methodName
	 * @return
	 */
	private SoapObject executeSoapAction(String methodName, Object... args) {
		
		Object result = null;
		
	    /* Create a org.ksoap2.serialization.SoapObject object to build a SOAP request. Specify the namespace of the SOAP object and method
	     * name to be invoked in the SoapObject constructor.
	     */
	    SoapObject request = new SoapObject(NAMESPACE, methodName);
	    
	    /* The array of arguments is copied into properties of the SOAP request using the addProperty method. */
	    for (int i=0; i<args.length; i++) {
		    request.addProperty("arg" + i, args[i]);
	    }
	    
	    /* Next create a SOAP envelop. Use the SoapSerializationEnvelope class, which extends the SoapEnvelop class, with support for SOAP 
	     * Serialization format, which represents the structure of a SOAP serialized message. The main advantage of SOAP serialization is portability.
	     * The constant SoapEnvelope.VER11 indicates SOAP Version 1.1, which is default for a JAX-WS webservice endpoint under JBoss.
	     */
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
	    
	    /* Assign the SoapObject request object to the envelop as the outbound message for the SOAP method call. */
	    envelope.setOutputSoapObject(request);
	    
	    /* Create a org.ksoap2.transport.HttpTransportSE object that represents a J2SE based HttpTransport layer. HttpTransportSE extends
	     * the org.ksoap2.transport.Transport class, which encapsulates the serialization and deserialization of SOAP messages.
	     */
	    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	    
	    try {
	        /* Make the soap call using the SOAP_ACTION and the soap envelop. */
		    List<HeaderProperty> reqHeaders = null;
	    	@SuppressWarnings({"unused", "unchecked"})
			List<HeaderProperty> respHeaders = androidHttpTransport.call(NAMESPACE + methodName, envelope, reqHeaders);
	
	        /* Get the web service response using the getResponse method of the SoapSerializationEnvelope object.
	         * The result has to be cast to SoapPrimitive, the class used to encapsulate primitive types, or to SoapObject.
	         */
	        result = envelope.getResponse();	        
	        
	        if (result instanceof SoapFault) {
	        	throw (SoapFault) result;
	        }
	    }
	    catch (SoapFault e) {
	    	e.printStackTrace();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }	    
	    
	    return (SoapObject) result;
	}

	@Override
	public double diceNumber() {
		Log.d(TAG,"diceNumber called");
		String METHOD_NAME = "diceNumber";
		SoapObject response = executeSoapAction(METHOD_NAME);
		Log.d(TAG, response.toString());
		String diceString = response.getProperty("diceNumber").toString();
		double diceNumber=Double.parseDouble(diceString);
		return diceNumber;
	}

	@Override
	public String register(String username, String password) {
		Log.d(TAG, "register called");
		String METHOD_NAME = "register";
		SoapObject response = executeSoapAction(METHOD_NAME, username, password);
		Log.d(TAG, response.toString());
		for (int i = 0; i < response.getPropertyCount(); i++) {
			PropertyInfo pi = new PropertyInfo();
			response.getPropertyInfo(i, pi);
			Log.d(TAG, pi.name + " : " + response.getProperty(i).toString());
		}
		return new String(response.getPrimitivePropertySafelyAsString("success"));
	}

	@Override
	public Kunde login(String username, String password) {
		Kunde result = null;
		Log.d(TAG, "login called");
		String METHOD_NAME = "login";
		SoapObject response = executeSoapAction(METHOD_NAME, username, password);
		Log.d(TAG, response.toString());
	
		if( Integer.parseInt(response.getPrimitiveProperty("sessionId").toString()) ==0){
			Log.d("Falsches PW oder Username","Falsches PW oder Username");
	
		 return null;	
		 
		}
		for (int i = 0; i < response.getPropertyCount(); i++) {
			PropertyInfo pi = new PropertyInfo();
			response.getPropertyInfo(i, pi);
			Log.d(TAG, pi.name + " : " + response.getProperty(i).toString());
		}
		this.sessionId = Integer.parseInt(response.getPrimitivePropertySafelyAsString("sessionId"));
		result = new Kunde(username, password);
		return result;
	}		
	
	@Override
	public ArrayList<Games> getGames() {
		Log.d(TAG,"getGames called.");		
		
		
		ArrayList<Games> result = new ArrayList<Games>();
		
		String METHOD_NAME = "getGames";
		SoapObject response = executeSoapAction(METHOD_NAME);

		Log.d(TAG, METHOD_NAME);
		Log.d(TAG, response.toString());

		for (int i=1; i<response.getPropertyCount(); i++) {
			SoapObject soapGameEntry = (SoapObject) response.getProperty(i);
			SoapPrimitive soapGameNr = (SoapPrimitive) soapGameEntry.getProperty("id");
			SoapPrimitive soapSlots = (SoapPrimitive) soapGameEntry.getProperty("slots");
			
			SoapObject soapOwnerObj = (SoapObject) soapGameEntry.getProperty("owner");
			SoapPrimitive soapOwnerName = (SoapPrimitive) soapOwnerObj.getProperty("userName");
			Games game = new Games();
			
			game.setId(Integer.valueOf(soapGameNr.toString()));
			game.setSlots(Integer.valueOf(soapSlots.toString()));
			game.setOwner(soapOwnerName.toString());
			game.setSpieler1(soapOwnerName.toString());
			
			if(soapGameEntry.toString().contains("spieler2")){
			SoapObject spieler2 = (SoapObject) soapGameEntry.getPropertySafely("spieler2");
			SoapPrimitive spieler2username = (SoapPrimitive) spieler2.getProperty("userName");
			game.setSpieler2(String.valueOf(spieler2username));
			}
			if(soapGameEntry.toString().contains("spieler3")){
			SoapObject spieler3 = (SoapObject) soapGameEntry.getPropertySafely("spieler3");
			SoapPrimitive spieler3username = (SoapPrimitive) spieler3.getProperty("userName");
			game.setSpieler3(String.valueOf(spieler3username));
			}
			if(soapGameEntry.toString().contains("spieler4")){
			SoapObject spieler4 = (SoapObject) soapGameEntry.getPropertySafely("spieler4");
			SoapPrimitive spieler4username = (SoapPrimitive) spieler4.getProperty("userName");
			game.setSpieler4(String.valueOf(spieler4username));
			}
			
//			game.setZuschauer(zuschauer);
			result.add(game);
		}
		return result;
	}
	
	@Override
	public Games getGameDetails(int id) {
		Log.d(TAG,"getGameDetails called.");		
	
		String METHOD_NAME = "getGameDetails";
		SoapObject response = executeSoapAction(METHOD_NAME, id);

		Log.d(TAG, "Methode: "+METHOD_NAME);
		Log.d(TAG, "Response: "+response.toString());
		Log.d(TAG, "Anzahl: "+response.getPropertyCount());
		
		Games result = new Games();
		
		for (int i=1; i<response.getPropertyCount(); i++) {
			SoapObject soapGameEntry = (SoapObject) response.getProperty(i);
			SoapPrimitive soapGameNr = (SoapPrimitive) soapGameEntry.getProperty("id");
			SoapPrimitive soapSlots = (SoapPrimitive) soapGameEntry.getProperty("slots");
			SoapPrimitive soapOwnerId = (SoapPrimitive) soapGameEntry.getProperty("ownerId");

			result.setId(Integer.valueOf(soapGameNr.toString()));
//			game.setSpieler1(soapGamePlayer1.toString());
		}
		Log.d("", ""+result.toString());
		return result;
	}

	@Override
	public Games createGame() {
		String METHOD_NAME = "createNewGame";	
		Log.d(TAG,METHOD_NAME+" called.");
		
		SoapObject response = executeSoapAction(METHOD_NAME, sessionId);
		Log.d(TAG, response.toString());
		
		SoapPrimitive successStatus = (SoapPrimitive) response.getProperty("success");
		Games game = new Games();
		
		SoapPrimitive soapGameNr = (SoapPrimitive) response.getProperty("id");
		game.setId(Integer.valueOf(soapGameNr.toString()));
		
		Log.d(TAG, METHOD_NAME);
		Log.d(TAG, response.toString());
		Log.d(TAG, ""+response.getPropertyCount());
		return game;
	}
	/*
	 * @param id gameid
	 * @param sessionId sessionId
	 * @return boolean success
	 * (non-Javadoc)
	 * @see com.example.menschapp.util.MenschSystem#joinGame()
	 */
	@Override
	public Request joinGame(int id) {
		String METHOD_NAME = "requestJoinGame";
		Log.d(TAG, ""+METHOD_NAME+" called");
		SoapObject response = executeSoapAction(METHOD_NAME,  id,sessionId);
		Log.d(TAG, response.toString());
		
		SoapPrimitive success = (SoapPrimitive) response.getProperty("success");
		
		Request request = new Request();
		request.setSuccess(success);
		request.setId(Integer.parseInt(response.getPrimitivePropertyAsString("requestId")));
		return request;
	}
	public void leaveGame(int id){
		String METHOD_NAME = "leaveGame";
		Log.d(TAG, ""+METHOD_NAME+" called");
		SoapObject response = executeSoapAction(METHOD_NAME, sessionId, id);
		//Log.d(TAG, response.toString());
		
		//SoapPrimitive success = (SoapPrimitive) response.getProperty("success");
				
		//Request request = new Request();
		//request.setSuccess(success);
		//return request;
		//return null;
	}
	//@Override
//	public Response joinGameResponse() {
//		return null;
//	}

	@Override
	public void getGameFields(int gameid) {
		String METHOD_NAME = "getGameFields";
		Log.d(TAG, ""+METHOD_NAME+ " called");
		
		SoapObject response = executeSoapAction(METHOD_NAME, gameid);
		
		GameField gameField = new GameField();
		
		for(int i=1;i<73;i++){ 
			SoapPrimitive valueRe = (SoapPrimitive) response.getProperty(i);
			int value = Integer.valueOf(valueRe.toString());
			gameField.setField(i, value); 
		}
	}

	@Override
	public String checkMyRequest(int requestId) {
		String result;
		
		String METHOD_NAME = "checkMyRequest";
		Log.d(TAG, ""+METHOD_NAME+ " called");
		
		SoapObject response = executeSoapAction(METHOD_NAME, requestId);
		Log.d(TAG, response.toString());
		result = response.getPropertyAsString("state");
		
		return result;
	}

	@Override
	public ArrayList<Request> checkForRequests(int gameId) {
		ArrayList<Request> result = new ArrayList<Request>();
		
		String METHOD_NAME = "getRequests";
		Log.d(TAG, ""+METHOD_NAME+ " called");

		SoapObject response = executeSoapAction(METHOD_NAME, gameId);
		Log.d(TAG, "response: "+response);
		Log.d(TAG, response.toString());
		if(response.getPropertyCount()>1) {
			SoapObject soapRequestEntry = (SoapObject) response.getProperty(1);
			SoapPrimitive requestId = (SoapPrimitive) soapRequestEntry.getProperty("id");
			SoapPrimitive userName = (SoapPrimitive) soapRequestEntry.getProperty("userName");

			Request req = new Request();
			
			req.setId(Integer.valueOf(requestId.toString()));
			req.setUserName(userName.toString());
			
			result.add(req);		
		}
		return result;
	}

	
	public void allowPlayer(int requestId) {
		String METHOD_NAME = "allowPlayer";
		Log.d(TAG, ""+METHOD_NAME+ " called");
		
		SoapObject response = executeSoapAction(METHOD_NAME, requestId);
		
	}

	
	public void declinePlayer(int requestId) {
		String METHOD_NAME = "declinePlayer";
		Log.d(TAG, ""+METHOD_NAME+ " called");
		
		SoapObject response = executeSoapAction(METHOD_NAME, requestId);
		
	}
}