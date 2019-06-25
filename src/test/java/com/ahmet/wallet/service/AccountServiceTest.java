package com.ahmet.wallet.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahmet.wallet.exception.AccountNotFoundException;
import com.ahmet.wallet.model.Account;
import com.ahmet.wallet.repository.AccountRepository;
import com.ahmet.wallet.service.impl.AccountServiceImpl;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

	@InjectMocks
	private AccountServiceImpl accountServiceMock;
	
	@Mock
	private AccountRepository accountRepository;

	private static long playerId = 1l;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAccountByPlayerId() {
		Account account = new Account();
		when(accountRepository.findAccountByPlayerId(any(Long.class))).thenReturn(Optional.of(account));
		Account foundAccount = accountServiceMock.getAccountByPlayerId(playerId);
		assertThat(foundAccount, is(equalTo(account)));
	}
	
	@Test
	public void testAccountNotFoundException() throws Exception {

		String errorMessage = "Account Not Found";
		when(accountRepository.findAccountByPlayerId(playerId)).thenReturn(Optional.empty());
		
		assertThatThrownBy(() -> accountServiceMock.getAccountByPlayerId(playerId))
        .isInstanceOf(AccountNotFoundException.class)
        .hasMessage(errorMessage);
		
	}

}
