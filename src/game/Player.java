package game;

import java.util.ArrayList;

public abstract class Player {
	protected String type;
	protected int points = 0;
	protected boolean finished = false;
	protected boolean lost = false;
	protected int action;

	public int getPoints() {
		return points;
	}

	public boolean isFinished() {
		return finished;
	}
	
	public boolean isLost() {
		return lost;
	}

	public String getType() {
		return type;
	}
	
	public int getAction() {
		return action;
	}

	public abstract Card play(ArrayList<Card> cards);
}