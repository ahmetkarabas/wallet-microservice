package com.ahmet.wallet.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmet.wallet.service.TransactionService;
import com.ahmet.wallet.vo.ResponseVo;
import com.ahmet.wallet.vo.TransactionVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="/transaction", produces ="application/json")
@RequestMapping(value = "/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@ApiOperation(value = "Make Debit to Player Account Balance")
	@PostMapping(value = "/debit")
	public ResponseEntity<ResponseVo> debit(@Valid @RequestBody TransactionVo transactionVo) throws Exception {
		ResponseVo responseVo = transactionService.debit(transactionVo);
		return new ResponseEntity<>(responseVo, HttpStatus.OK);
	}

	@ApiOperation(value = "Make Credit to Player Account Balance")
	@PostMapping(value = "/credit")
	public ResponseEntity<ResponseVo> credit(@Valid @RequestBody TransactionVo transactionVo) throws Exception {
		ResponseVo responseVo = transactionService.credit(transactionVo);
		return new ResponseEntity<>(responseVo, HttpStatus.OK);
	}

	@ApiOperation(value = "Get Transaction History of player")
	@GetMapping(value = "/history/{playerId}")
	public ResponseEntity<List<TransactionVo>> transactionHistory(@PathVariable("playerId") long playerId) {
		List<TransactionVo> playerTransactionHistoryList = transactionService.getAllTransactionsByPlayerId(playerId);
		return new ResponseEntity<>(playerTransactionHistoryList, HttpStatus.OK);
	}

	@ApiOperation(value = "Generate external transaction Id for api operations")
	@GetMapping(value="/generateExtTransId")
	public String generateTransactionId() {
		UUID transId = UUID.randomUUID();
		return transId.toString();
	}

}
