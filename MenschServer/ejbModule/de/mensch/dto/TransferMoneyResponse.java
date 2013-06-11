package de.mensch.dto;

import java.math.BigDecimal;

public class TransferMoneyResponse extends ReturncodeResponse {

	private static final long serialVersionUID = 3948656595733352040L;

	private BigDecimal newBalance;
	
	public TransferMoneyResponse() {
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(BigDecimal newBalance) {
		this.newBalance = newBalance;
	}

}
