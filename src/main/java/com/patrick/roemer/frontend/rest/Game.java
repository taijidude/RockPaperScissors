package com.patrick.roemer.frontend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.roemer.frontend.cli.CliUtilsIF;

/**
 * Kapselt den "Frontend" Teil der Spielelogik und die Kommunikation mit dem Backend. 
 * Hier würde ich als nächstes (wenn die Logik weiter wachsen würde), vermutlich die
 * Restkommunikation rausziehen.  
 * @author patrick
 *
 */
@Component
public class Game implements GameIF {

	private RestTemplate rt = new RestTemplate();
	
	private ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private CliUtilsIF cliu;
	
	@Autowired
	private Environment env;
	
	@Value("${backendUrl}")
	private String backendUrl;
	
	public Game() {}
	
	@Override
	public void play() throws JsonMappingException, JsonProcessingException {
		boolean gameRunning = true; 
		while(gameRunning) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String requestBody = om.writeValueAsString(new Message(cliu.pickOption()));
			HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
			Message message = restTemplate.postForObject(backendUrl, request, Message.class);
			System.err.println(message.getContent());
			gameRunning =  cliu.processContinue();
		}
	}
}