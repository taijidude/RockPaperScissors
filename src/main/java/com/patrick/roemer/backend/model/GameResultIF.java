package com.patrick.roemer.backend.model;

public interface GameResultIF {

	String getMessage();

	void setMessage(String message);

	String getWinnerName();

	void setWinnerName(String winnerName);

	gameoption getWinnerPick();

	void setWinnerPick(gameoption winnerPick);

	String getLoserName();

	void setLoserName(String loserName);

	gameoption getLoserPick();

	void setLoserPick(gameoption loserPick);

	boolean isDraw();

	void setDraw(boolean draw);
}