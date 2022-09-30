package com.patrick.roemer.backend.model;

/**
 * Die Klasse kapselt das Ergbnis einer Partie. Beim Erstellen 
 * wird auch gleich die Nachricht generiert, die über die Schnittstelle
 * and das "Frontend" übergeben wird.
 * @author patrick
 *
 */
public class GameResult implements GameResultIF {
	private String message;
	private String winnerName;
	private gameoption winnerPick;
	private String loserName;
	private gameoption loserPick;
	private boolean draw;
	
	//Jackson will den Konstruktor hier haben
	public GameResult() {}
	
	public GameResult(String winnerName, gameoption winnerPick, String loserName, gameoption loserPick, boolean draw) {
		this.winnerName = winnerName;
		this.winnerPick = winnerPick;
		this.loserName = loserName;
		this.loserPick = loserPick;
		this.draw = draw;
		if(this.draw) {
			message = "Unentschieden! "+winnerName+" mit "+winnerPick.getName()+" vs "+loserName+" mit "+loserPick.getName() ;
		} else {
			message = winnerName + " hat mit "+winnerPick.getName()+" gegen "+loserName+" mit "+loserPick.getName() +" gewonnen!";
		}
	}
		
	@Override
	public String toString() {
		return "GameResult [message=" + message + ", winnerName=" + winnerName + ", winnerPick=" + winnerPick
				+ ", loserName=" + loserName + ", loserPick=" + loserPick + ", draw=" + draw + "]";
	}
	
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getWinnerName() {
		return winnerName;
	}

	@Override
	public gameoption getWinnerPick() {
		return winnerPick;
	}

	@Override
	public String getLoserName() {
		return loserName;
	}

	@Override
	public gameoption getLoserPick() {
		return loserPick;
	}

	@Override
	public boolean isDraw() {
		return draw;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
		
	}

	@Override
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

	@Override
	public void setWinnerPick(gameoption winnerPick) {
		this.winnerPick = winnerPick;
	}

	@Override
	public void setLoserName(String loserName) {
		this.loserName = loserName;
	}

	@Override
	public void setLoserPick(gameoption loserPick) {
		this.loserPick = loserPick;
	}

	@Override
	public void setDraw(boolean draw) {
		this.draw = draw;
	}
}