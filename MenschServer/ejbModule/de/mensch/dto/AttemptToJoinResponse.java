package de.mensch.dto;

public class AttemptToJoinResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3173158310918408228L;

	public boolean success = false;

	public AttemptToJoinResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
