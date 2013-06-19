package de.mensch.dto;

public class RequestResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3175635831091840828L;
	
	String state;
	
	public RequestResponse() {
		// TODO Auto-generated constructor stub
	}

	public void setState(String state) {
		this.state = state;
		
	}

	public String getState() {
		return state;
	}


}
