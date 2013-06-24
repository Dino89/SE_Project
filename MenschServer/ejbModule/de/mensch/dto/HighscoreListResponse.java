package de.mensch.dto;

public class HighscoreListResponse extends Response {
	
	private static final long serialVersionUID = -3173158310918408288L;
	
	private String list;
	
	
	public HighscoreListResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public void setList(String sList) {
		this.list = sList;
		
	}

	public String getList() {
		return list;
	}

}
