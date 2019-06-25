package com.ahmet.wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ahmet.wallet.model.Transaction;
import com.ahmet.wallet.vo.TransactionVo;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{

	public Optional<Transaction> getTransactionByExtTransId(String transId);
	
	@Query("SELECT new com.ahmet.wallet.vo.TransactionVo(t.extTransId, t.playerId, t.transAmt) FROM Transaction t WHERE t.playerId = :playerId")
	public List<TransactionVo> findTransactionsByPlayerId(@Param("playerId") long playerId);
}
