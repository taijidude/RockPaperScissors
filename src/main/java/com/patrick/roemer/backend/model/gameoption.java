package com.patrick.roemer.backend.model;

/**
 * Die möglichen Auswahlen der Spieler im Spiel. 
 * Mit ihrem Ausgabetext. 
 * @author patrick
 *
 */
public enum gameoption {
	rock("Stein"), paper("Papier"), scissors("Schere"), fountain("Brunnen");
	
	private final String name;

	private gameoption(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}