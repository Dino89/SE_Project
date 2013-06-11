package de.mensch.dto;

import java.math.BigDecimal;

public class AccountBalanceResponse extends ReturncodeResponse {

	private static final long serialVersionUID = 4964175158713886922L;

	private int accountId;
	private BigDecimal balance;
	
	public AccountBalanceResponse() {
		// TODO Auto-generated constructor stub
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
