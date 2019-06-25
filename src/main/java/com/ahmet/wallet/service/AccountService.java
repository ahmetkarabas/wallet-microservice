package com.ahmet.wallet.service;

import java.math.BigDecimal;

import com.ahmet.wallet.model.Account;

/**
 * Account Service
 * @author ahmet.karabas
 */
public interface AccountService {

	public Account getAccountByPlayerId(long playerId);

	public void updateAccountBalanceById(BigDecimal newBalance, Long id);
}
