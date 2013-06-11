package de.mensch.dto;

public class UserRegisterResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3173158310918408228L;

	private int sessionId;
	private int customerId;
	boolean success = false;
	
	public UserRegisterResponse() {
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

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public boolean getSuccess() {
		return success;
	}
}
