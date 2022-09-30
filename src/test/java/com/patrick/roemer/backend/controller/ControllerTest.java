package com.patrick.roemer.backend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.roemer.backend.model.GameResult;
import com.patrick.roemer.frontend.app.App;
import com.patrick.roemer.frontend.rest.Message;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT, classes = App.class)
class ControllerTest {
	
	@LocalServerPort
	private int localServerPort;
	
	@Autowired
	private TestRestTemplate trt;
	
	@Autowired
	private ObjectMapper om;
	
	@Autowired
	private GameController gc;

	@Test
	public void contextLoads() throws Exception {
		assertThat(gc).isNotNull();
	}
	
	@Test
	void testBasicEndpoint() throws Exception {
		String result = trt.getForObject("http://localhost:"+localServerPort+"/test", String.class);
		assertEquals("Es lebt!", result);
	}
	
	@Test
	void testGameEndPoint() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		
	    HttpEntity<String> request = new HttpEntity<String>(om.writeValueAsString(new Message("rock")), headers);
		ResponseEntity<Message> response = trt.postForEntity("http://localhost:"+localServerPort+"/game", request, Message.class);
		assertTrue(response.getBody().getContent().contains("gewonnen!"));
		
		request = new HttpEntity<String>(om.writeValueAsString(new Message("paper")), headers);
		response = trt.postForEntity("http://localhost:"+localServerPort+"/game", request, Message.class);
		assertTrue(response.getBody().getContent().contains("gewonnen!"));

		request = new HttpEntity<String>(om.writeValueAsString(new Message("scissors")), headers);
		response = trt.postForEntity("http://localhost:"+localServerPort+"/game", request, Message.class);
		assertTrue(response.getBody().getContent().contains("gewonnen!"));
		
		request = new HttpEntity<String>(om.writeValueAsString(new Message("fountain")), headers);
		response = trt.postForEntity("http://localhost:"+localServerPort+"/game", request, Message.class);
		assertTrue(response.getBody().getContent().contains("gewonnen!"));
	}
	
	@Test
	void testGameEndPointWrongInput() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		
	    HttpEntity<String> request = new HttpEntity<String>(om.writeValueAsString(new Message("rocko")), headers);
		ResponseEntity<String> response = trt.postForEntity("http://localhost:"+localServerPort+"/game", request, String.class);
		assertEquals("Es konnte keine gameoption zu dem Parameter rocko gefunden werden!", response.getBody());
	}
}