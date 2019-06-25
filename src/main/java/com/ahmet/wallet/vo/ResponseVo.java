package com.ahmet.wallet.vo;

import java.math.BigDecimal;

public class ResponseVo {

	private long accountId;
	
	private BigDecimal oldBalance;
	
	private BigDecimal newBalance;
	
	public ResponseVo() {}
	
	public ResponseVo(long accountId, BigDecimal oldBalance, BigDecimal newBalance) {
		this.accountId = accountId;
		this.oldBalance = oldBalance;
		this.newBalance = newBalance;
	}

	public BigDecimal getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(BigDecimal oldNalance) {
		this.oldBalance = oldNalance;
	}

	public BigDecimal getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(BigDecimal newBalance) {
		this.newBalance = newBalance;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
}
