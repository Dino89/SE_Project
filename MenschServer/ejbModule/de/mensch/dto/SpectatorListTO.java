package de.mensch.dto;

import java.util.ArrayList;

public class SpectatorListTO extends ReturncodeResponse{

	public SpectatorListTO() {
		
	}

	private static final long serialVersionUID = -3123583134408228L;
	ArrayList<String> zuschauer;

	public ArrayList<String> getZuschauer() {
		return zuschauer;
	}

	public void setZuschauer(ArrayList<String> zuschauer) {
		this.zuschauer = zuschauer;
	}
}
