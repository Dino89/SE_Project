package de.mensch.dto;

import java.util.List;

public class AccountListResponse extends ReturncodeResponse {

	private static final long serialVersionUID = -5754928488884226775L;

	private List<AccountTO> accountList;
	
	public AccountListResponse() {
		// TODO Auto-generated constructor stub
	}

	public List<AccountTO> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<AccountTO> accountList) {
		this.accountList = accountList;
	}

}
