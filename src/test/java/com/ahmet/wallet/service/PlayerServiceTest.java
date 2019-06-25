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

import com.ahmet.wallet.exception.PlayerNotFoundException;
import com.ahmet.wallet.model.Player;
import com.ahmet.wallet.repository.PlayerRepository;
import com.ahmet.wallet.service.impl.PlayerServiceImpl;

@RunWith(SpringRunner.class)
public class PlayerServiceTest {

	@InjectMocks
	private PlayerServiceImpl playerServiceMock;
	
	@Mock
	private PlayerRepository playerRepository;

	private static long playerId = 1l;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void verifyPlayerId() {
		Player player = new Player();
		when(playerRepository.findById(any(Long.class))).thenReturn(Optional.of(player));
		Player foundPlayer = playerServiceMock.getPlayerById(playerId);
		assertThat(foundPlayer, is(equalTo(player)));
	}
	
	@Test
	public void testPlayerNotFoundException() throws Exception {

		String errorMessage = "Player Not Found";
		when(playerRepository.findById(playerId)).thenReturn(Optional.empty());
		assertThatThrownBy(() -> playerServiceMock.getPlayerById(playerId))
        .isInstanceOf(PlayerNotFoundException.class)
        .hasMessage(errorMessage);
		
	}


}
