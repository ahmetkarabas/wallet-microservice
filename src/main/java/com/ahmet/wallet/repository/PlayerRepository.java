package com.ahmet.wallet.repository;

import org.springframework.data.repository.CrudRepository;

import com.ahmet.wallet.model.Player;

public interface PlayerRepository extends CrudRepository<Player, Long>{

	public Player getPlayerById(long id);

}
