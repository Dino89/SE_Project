package de.mensch.dto;

public class CreateGameResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3173584560918408228L;
	
	int id;
	boolean success;
	
	public CreateGameResponse() {
		// TODO Auto-generated constructor stub
	}

	public void setSuccess(boolean success) {
		this.success = success;
		
	}
	
	public String toString() {
		return "Erfolg: "+success;
	}

	public boolean isSuccess() {
		return success;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
