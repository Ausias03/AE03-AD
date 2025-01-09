package game;

public abstract class Player {
	protected String type;
	protected int points = 0;
	protected boolean finished = false;
	protected boolean lost = false;

	public int getPoints() {
		return points;
	}

	public boolean isFinished() {
		return finished;
	}

	public String getType() {
		return type;
	}

	public abstract void play();
}