package com.patrick.roemer.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.patrick.roemer.backend.model.GameResultIF;
import com.patrick.roemer.backend.service.GameServiceIF;
import com.patrick.roemer.frontend.rest.Message;

/**
 * Einstiegspunkt für das Backend. Die wichtige Methode ist newGame. 
 * Die ruft den GameService auf, wo alles weitere abgewickelt wird. 
 * @author patrick
 *
 */
@RestController
public class GameController {
	
	@Autowired
	private GameServiceIF gs;
	
	@PostMapping(path="/game", produces = "application/json")
    public @ResponseBody Message newGame(@RequestBody Message message) {
		String requestContent = message.getContent();
		GameResultIF gameResult = gs.startGame(requestContent);
		return new Message(gameResult.getMessage());
    }
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleException(Exception ex) {
		return ResponseEntity
	        .status(HttpStatus.NOT_FOUND)
	        .body(ex.getMessage());
	  }
	
	/**
	 * Diese Methode habe ich für einen grundsatzlichen Funktionstest des "Backends"
	 * hier drin. 
	 * @return
	 */
	@GetMapping(path="/test", produces = "application/json")
	public @ResponseBody String test() {
		return "Es lebt!";
	}
}