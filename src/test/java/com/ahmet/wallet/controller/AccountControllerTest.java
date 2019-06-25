package com.ahmet.wallet.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ahmet.wallet.controller.AccountController;
import com.ahmet.wallet.model.Account;
import com.ahmet.wallet.model.Player;
import com.ahmet.wallet.service.AccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountServiceMock;
	
	private static final String url = "/account";
	
	long playerId;
	
	long accountId;
	
	Account account;
	
	@Before
	public void setup() {
		playerId = 1l;
		accountId= 1l;
		account = new Account("test account", BigDecimal.TEN, new Player(playerId, "test player"));
	}

	@Test
	public void testGetBalancePerPlayer() throws Exception {
		
		when(accountServiceMock.getAccountByPlayerId(playerId)).thenReturn(account);
		
		mockMvc.perform(get(url + "/balance/{playerId}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accountName", is("test account")))
				.andExpect(jsonPath("$.currentBalance", is(10)))
				.andExpect(jsonPath("$.playerId", is(1)));
	}
}
