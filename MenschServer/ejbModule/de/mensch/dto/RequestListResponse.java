package de.mensch.dto;

import java.util.ArrayList;
import java.util.List;

import de.mensch.entities.Request;

public class RequestListResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -3123583134408228L;
	
	private ArrayList<RequestTO> requestList;
	
	public RequestListResponse() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<RequestTO> getRequestList() {
		return requestList;
	}

	public void setRequestList(ArrayList<RequestTO> requestList) {
		this.requestList = requestList;
	}
}
