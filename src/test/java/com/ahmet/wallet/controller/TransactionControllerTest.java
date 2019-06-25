package com.ahmet.wallet.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ahmet.wallet.controller.TransactionController;
import com.ahmet.wallet.exception.InSufficientBalanceException;
import com.ahmet.wallet.exception.TransactionConflictException;
import com.ahmet.wallet.service.TransactionService;
import com.ahmet.wallet.vo.ResponseVo;
import com.ahmet.wallet.vo.TransactionVo;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransactionService transactionServiceMock;

	@Autowired
	ObjectMapper objectMapper;
	
	private static final String url = "/transaction";

	String extTransId;
	
	long playerId;
	
	long accountId;
	
	TransactionVo transactionVo;
	
	ResponseVo responseVo;
	
	@Before
	public void setup() {
		UUID uuid = UUID.randomUUID();
		extTransId = uuid.toString();
		playerId = 1l;
		accountId= 1l;
		transactionVo = new TransactionVo(extTransId, playerId, BigDecimal.TEN);
		responseVo = new ResponseVo(accountId, BigDecimal.valueOf(20), BigDecimal.TEN);
	}

	@Test
	public void testdebitShouldSuccess() throws Exception {
		
		when(transactionServiceMock.debit(refEq(transactionVo))).thenReturn(responseVo);

		mockMvc.perform(post(url + "/debit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(transactionVo)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accountId", is(1)))
				.andExpect(jsonPath("$.oldBalance", is(20)))
				.andExpect(jsonPath("$.newBalance", is(10)));

		verify(transactionServiceMock, times(1)).debit(refEq(transactionVo));
	}

	@Test
	public void testcreditShouldSuccess() throws Exception {
		
		when(transactionServiceMock.credit(refEq(transactionVo))).thenReturn(responseVo);

		mockMvc.perform(post(url + "/credit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(transactionVo)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accountId", is(1)))
				.andExpect(jsonPath("$.oldBalance", is(20)))
				.andExpect(jsonPath("$.newBalance", is(10)));

		verify(transactionServiceMock, times(1)).credit(refEq(transactionVo));

	}
	
	@Test
	public void testGetTransactionsByPlayerId() throws Exception {
		List<TransactionVo> transactionVoList = new ArrayList<>();
		transactionVoList.add(transactionVo);
		
		when(transactionServiceMock.getAllTransactionsByPlayerId(playerId)).thenReturn(transactionVoList);

		mockMvc.perform(get(url + "/history/{playerId}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].extTransId", is(extTransId)))
				.andExpect(jsonPath("$[0].playerId", is(1)))
				.andExpect(jsonPath("$[0].transAmt", is(10)));

		verify(transactionServiceMock, times(1)).getAllTransactionsByPlayerId(playerId);

	}

	@Test
	public void testShouldConflictWithSameTransId() throws Exception {
		
		String errorMessage = "Transaction Id Exist! You can not use same transaction Id";

		when(transactionServiceMock.credit(refEq(transactionVo))).thenThrow(new TransactionConflictException(errorMessage));

		mockMvc.perform(post(url + "/credit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(transactionVo)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.message", is(errorMessage)));

	}

	@Test
	public void testInSufficientBalanceException() throws Exception {
		
		String errorMessage = "Balance is not sufficient";

		when(transactionServiceMock.debit(refEq(transactionVo))).thenThrow(new InSufficientBalanceException(errorMessage));

		mockMvc.perform(post(url + "/debit").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(transactionVo))).andExpect(status().isNotAcceptable())
				.andExpect(jsonPath("$.message", is(errorMessage)));

	}
	
}
