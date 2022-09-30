package com.patrick.roemer.frontend.app;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.patrick.roemer.frontend.rest.GameIF;

/**
 * Das hier ist der Einstiegspunkt zu der Applikation. 
 * Ich bin hier mit dem Beenden der Anwendung noch nicht zufrieden, aber ich wollte das Backend aber
 * nicht laufen lassen, wenn das Spiel beendet wird.
 * @author patrick
 *
 */
@SpringBootApplication
@ComponentScan("com")
public class App {
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		ConfigurableApplicationContext  context = SpringApplication.run(App.class, args);
		context.getBean(GameIF.class).play();
		int exitCode = SpringApplication.exit(context, new ExitCodeGenerator() {
			@Override
			public int getExitCode() {
				return 0;
			}
		});
		System.exit(exitCode);
		
	}
}