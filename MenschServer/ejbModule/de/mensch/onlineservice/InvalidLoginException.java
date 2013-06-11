package de.mensch.onlineservice;

public class InvalidLoginException extends MenschException {

	private static final long serialVersionUID = 8759021636475023682L;
	private static final int CODE = 20;

	public InvalidLoginException(String message) {
		super(CODE, message);
	}

}
