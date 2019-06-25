package com.ahmet.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmet.wallet.exception.PlayerNotFoundException;
import com.ahmet.wallet.model.Player;
import com.ahmet.wallet.repository.PlayerRepository;
import com.ahmet.wallet.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	public Player getPlayerById(long playerId){
		Optional<Player> player =  playerRepository.findById(playerId);
		if(!player.isPresent()) {
			throw new PlayerNotFoundException("Player Not Found");
		}
		return player.get();
	}
}
