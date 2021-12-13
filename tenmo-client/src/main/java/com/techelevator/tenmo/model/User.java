package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class User {

	private long id;
	private String username;
	private BigDecimal balance;

	public long getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
