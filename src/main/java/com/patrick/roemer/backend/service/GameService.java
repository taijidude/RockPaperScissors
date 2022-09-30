package com.patrick.roemer.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.patrick.roemer.backend.model.GameResult;
import com.patrick.roemer.backend.model.GameResultIF;
import com.patrick.roemer.backend.model.gameoption;

@Service
public class GameService implements GameServiceIF {

	//Hier ist festgehalten wie sich die drei Optionen untereinander schlagen können
	private Map<gameoption,List<gameoption>> vulnerability = new HashMap<>();
	
	public GameService() {
		vulnerability.put(gameoption.rock, List.of(gameoption.paper, gameoption.fountain));
		vulnerability.put(gameoption.paper, List.of(gameoption.scissors));
		vulnerability.put(gameoption.scissors, List.of(gameoption.rock, gameoption.fountain));
		vulnerability.put(gameoption.fountain, List.of(gameoption.paper));
	}
	
	/**
	 * 'Spielzug' des Computers
	 * @return
	 */
	protected gameoption computerPick() {
		Random random = new Random();
		return gameoption.values()[random.nextInt(0, 4)];		
	}
	
	/**
	 * 'Spielzug des Spielers'
	 * @param itemName
	 * @return
	 */
	protected gameoption playerPick(String itemName) {
		for (gameoption option : gameoption.values()) {
			if(option.name().equals(itemName)) {
				return option;
			}
		}
		throw new IllegalStateException("Es konnte keine gameoption zu dem Parameter "+itemName+" gefunden werden!");
	}
	
	/**
	 * Hier werden die Partien ausgewertet
	 * @param player1name
	 * @param player1pick
	 * @param player2name
	 * @param player2pick
	 * @return
	 */
	protected GameResultIF pickWinner(String player1name, gameoption player1pick, String player2name, gameoption player2pick) {
		GameResult result = null;
		if(vulnerability.get(player2pick).contains(player1pick)) {
			result = new GameResult(player1name, player1pick, player2name, player2pick, false);
		} else if(vulnerability.get(player1pick).contains(player2pick)) {
			result = new GameResult(player2name, player2pick, player1name, player1pick, false);
		} else {
			result = new GameResult(player1name, player1pick, player2name, player2pick, true);
		}
		return result;
	}
	
	/**
	 * Diese Methode wird aus dem Controller aufgerufen. 
	 */
	@Override
	public GameResultIF startGame(String itemName) {
		gameoption playerPick = null;
		gameoption computerPick = null;
		try {
			boolean equalPicks = true;
			while(equalPicks) {
				playerPick = playerPick(itemName);
				computerPick = computerPick();
				equalPicks = playerPick.equals(computerPick);				
			}
			
		} catch (IllegalStateException e) {
			throw e; 
		}
		return pickWinner("Mensch", playerPick, "Computer", computerPick);
		
	}
}