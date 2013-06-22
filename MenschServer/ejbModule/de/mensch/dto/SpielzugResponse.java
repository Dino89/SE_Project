package de.mensch.dto;

public class SpielzugResponse extends Response {
	
	private static final long serialVersionUID = 912470871L;
	
	/*
	 * Wurde der Spielzug ausgeführt?
	 */
	boolean success;

	public void setSuccess(boolean b) {
		this.success = b;
		
	}
}
