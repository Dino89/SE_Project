package com.example.menschapp.util;

import java.io.Serializable;

public class HighscoreList implements Serializable {
	
	private static final long serialVersionUID = -3173158310918408288L;
	
	private String[] list;
	
	public HighscoreList() {
		super();
	}

	public String[] getList() {
		return list;
	}

	public void setList(String[] list) {
		this.list = list;
	}
	
	

}
