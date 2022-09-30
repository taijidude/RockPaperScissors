package com.patrick.roemer.frontend.cli;

import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

/**
 * In dieser Klasse finden sich Funktionen mit cli Bezug.
 * Ich bin mit der Lösung hier etwas unzufrieden. In der Lösung wird keine GUI gefordert, 
 * weshalb ich hier mit einer einfachen CLI Ausgabe arbeite. Aber das lässt sich schlecht testen.
 * Ich glaube normalerweise würde ich hier mit einem Logger arbeiten und dann wärend der Tests 
 * einfach die Logdateien kontrollieren. 
 *    
 * @author patrick
 *
 */
@Component
public class CliUtils implements CliUtilsIF {
	protected Map<String, String> pickOptions = Map.of("1", "scissors", "2", "rock", "3", "paper", "4", "fountain");
	protected final String possiblePicks = "=== Schere Stein Papier ===\nTriff eine Auswahl! Bitte die entsprechende Zahl zur entsprechenden Auswahl angeben!"
			+ "\n1 - Schere\n2 - Stein\n3 - Papier\n4 - Brunnen"; 
	private Scanner scanner = new Scanner(System.in);
	
	//Um die Ausgaben in die Commandline zu testen
	protected String lastPrinted;

	/**
	 * Methode für die Darstellung der Auswahloptionen 
	 * in der CLI. 
	 */

	@Override
	public String pickOption() {
		String result = null;
		boolean makePick = true;
		while(makePick) {
			Pair<Boolean,String> pick = pick();
			makePick = pick.getKey().booleanValue();
			if(makePick) {
				System.out.println(pick.getValue());
			} else {
				result = pick.getValue();
			}
		}
		
		return result;
	}
	

	protected Pair<Boolean, String> pick() {
		System.err.println(possiblePicks);
		String answer = getInput();
		String result = pickOptions.get(answer);
		if(result == null) {
			return new ImmutablePair<>(Boolean.TRUE, answer + " - Ungueltige Auswahl! Nochmal bitte!"); 
		} else {
			return new ImmutablePair<>(Boolean.FALSE, result);
		}
	}
	
	
	
	@Override
	public boolean processContinue() {
		System.err.println("Weiterspielen? y/n");
		String answer = getInput();
		if(!answer.equalsIgnoreCase("y")) {
			String output = "Spiel wird beendet!";
			lastPrinted = output;
			System.out.println(output);
			return false;
		}
		return true;
	}
	
	/**
	 * Ich ziehe diese eine Zeile in 
	 * eine Methode raus, damit ich
	 * das Verhalten in 
	 * Tests überschreiben kann.
	 * @return
	 */
	public String getInput() {
		return scanner.next();
	}
}