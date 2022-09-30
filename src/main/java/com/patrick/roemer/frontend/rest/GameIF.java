package com.patrick.roemer.frontend.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface GameIF {

	void play() throws JsonMappingException, JsonProcessingException;

}
