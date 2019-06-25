package com.ahmet.wallet.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.ahmet.wallet.annotation.UUID;

public class TransactionVo {

	@NotNull
	@UUID
	private String extTransId;
	
	@NotNull
	private long playerId;
	
	@NotNull
	private BigDecimal transAmt;
	
	public TransactionVo() {}
	
	public TransactionVo(String extTransId, long playerId, BigDecimal transAmt) {
		this.extTransId = extTransId;
		this.playerId = playerId;
		this.transAmt = transAmt;
	}

	public String getExtTransId() {
		return extTransId;
	}

	public void setExtTransId(String extTransId) {
		this.extTransId = extTransId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

}
