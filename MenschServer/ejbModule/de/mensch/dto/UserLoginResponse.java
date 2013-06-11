package de.mensch.dto;

public class UserLoginResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3173158310918408228L;

	private int sessionId;
	private int customerId;
	
	public UserLoginResponse() {
		// TODO Auto-generated constructor stub
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
