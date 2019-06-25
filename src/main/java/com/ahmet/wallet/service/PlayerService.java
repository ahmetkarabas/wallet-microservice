package com.ahmet.wallet.service;

import com.ahmet.wallet.model.Player;

/**
 * Player Service
 * @author ahmet.karabas
 */
public interface PlayerService {

	/**
	 * Get player by given id
	 * @param playerId
	 * @return
	 */
	public Player getPlayerById(long playerId);
}
