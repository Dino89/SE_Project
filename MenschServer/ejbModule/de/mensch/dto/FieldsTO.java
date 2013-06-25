package de.mensch.dto;

import java.util.ArrayList;

public class FieldsTO extends ReturncodeResponse{

	private static final long serialVersionUID = 9871L;
	
	private ArrayList<FieldTO> fields = new ArrayList<>();
	
	public FieldsTO(){
		
	}
	
	public FieldsTO(ArrayList<FieldTO> fields){
		this.fields = fields;
		
	}

	public ArrayList<FieldTO> getFields() {
		return fields;
	}

	public void setFields(ArrayList<FieldTO> fields) {
		this.fields = fields;
	}
	
}
