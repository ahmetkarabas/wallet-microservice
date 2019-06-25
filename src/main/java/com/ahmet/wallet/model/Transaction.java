package com.ahmet.wallet.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
	private String extTransId;
	
	@Column
	private Integer transType;
	
	@Column
	private BigDecimal transAmt;
	
	@Column
	private BigDecimal accountBalance;

	@Column
	private long accountId;

	@Column
	private long playerId;
	
    public Transaction(String extTransId, Integer transType, BigDecimal transAmt, BigDecimal accountBalance, long playerId) {
    	this.extTransId = extTransId;
    	this.transType = transType;
    	this.transAmt = transAmt;
    	this.accountBalance = accountBalance;
    	this.playerId = playerId;
    }
    
    public Transaction() {
    	
    }

	public String getExtTransId() {
		return extTransId;
	}

	public void setExtTransId(String extTransId) {
		this.extTransId = extTransId;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
