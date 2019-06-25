package com.ahmet.wallet.service;

import java.util.List;

import com.ahmet.wallet.vo.ResponseVo;
import com.ahmet.wallet.vo.TransactionVo;

/**
 * Transaction Management Service
 * @author ahmet.karabas
 */
public interface TransactionService {

	/**
	 * Make debit to account by generated transaction record
	 * @param transactionVo
	 */
	public ResponseVo debit(TransactionVo transactionVo);
	
	/**
	 * Make credit to account by generated transaction record
	 * @param transactionVo
	 */
	public ResponseVo credit(TransactionVo transactionVo);
	
	/**
	 * Get all transaction history by given playerId
	 * @param playerId
	 */
	public List<TransactionVo> getAllTransactionsByPlayerId(long playerId); 
}
