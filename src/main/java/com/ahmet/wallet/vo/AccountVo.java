package com.ahmet.wallet.vo;

import java.math.BigDecimal;

public class AccountVo {
	
	private long playerId;

	private BigDecimal currentBalance;
	
	private String accountName;
	
	public AccountVo(long playerId, BigDecimal currentBalance, String accountName) {
		this.playerId = playerId;
		this.currentBalance = currentBalance;
		this.accountName = accountName;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	
}
