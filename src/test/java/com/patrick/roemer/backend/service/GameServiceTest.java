package com.patrick.roemer.backend.service;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.roemer.backend.model.GameResultIF;
import com.patrick.roemer.backend.model.gameoption;
import com.patrick.roemer.backend.service.GameService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class GameServiceTest {
	

	private GameService gs = new GameService();

	
	@Test
	void testGameService() throws Exception {
		
		assertThrows(IllegalStateException.class, () -> {
			gs.startGame("");
		});
		
		assertThrows(IllegalStateException.class, () -> {
			gs.startGame("test");
		});	
	}
	
	@Test
	void testPickWinner() throws Exception {
		assertTrue(gs.pickWinner("Computer",gameoption.paper,"Mensch",gameoption.paper).isDraw()); 
		assertTrue(gs.pickWinner("Computer",gameoption.scissors, "Mensch", gameoption.scissors).isDraw());
		assertTrue(gs.pickWinner("Computer",gameoption.rock, "Mensch",gameoption.rock).isDraw());
	
		assertEquals("Computer",gs.pickWinner("Computer",gameoption.rock,"Mensch",gameoption.scissors).getWinnerName());
		assertEquals("Computer",gs.pickWinner("Computer",gameoption.scissors,"Mensch",gameoption.paper).getWinnerName());
		assertEquals("Computer",gs.pickWinner("Computer",gameoption.paper,"Mensch",gameoption.rock).getWinnerName());

		assertEquals("Mensch",gs.pickWinner("Computer",gameoption.scissors,"Mensch",gameoption.rock).getWinnerName());
		assertEquals("Mensch",gs.pickWinner("Computer",gameoption.paper,"Mensch",gameoption.scissors).getWinnerName());
		assertEquals("Mensch",gs.pickWinner("Computer",gameoption.rock,"Mensch",gameoption.paper).getWinnerName());
		
		
		assertEquals("Mensch",gs.pickWinner("Mensch", gameoption.fountain, "Computer", gameoption.rock).getWinnerName());
		assertEquals("Mensch",gs.pickWinner("Mensch", gameoption.fountain, "Computer", gameoption.scissors).getWinnerName());
		assertEquals("Mensch",gs.pickWinner("Mensch", gameoption.paper, "Computer", gameoption.fountain).getWinnerName());
		
		
	}
	
}