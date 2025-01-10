package game;

import java.util.ArrayList;

/**
 * Abstract class representing a player in the game.
 * It holds common attributes and methods for both human and robot players.
 */
public abstract class Player {
	/**
	 * Type of the player (e.g., "Human", "Robot").
	 */
	protected String type;
	/**
	 * Points accumulated by the player.
	 */
	protected int points = 0;
	/**
	 * Flag indicating if the player has finished their participation in the game.
	 */
	protected boolean finished = false;
	/**
	 * Flag indicating if the player has lost.
	 */
	protected boolean lost = false;
	/**
	 * Player's action in the current turn.
	 */
	protected int action;

    /**
     * Returns the player's current points.
     * 
     * @return The points of the player.
     */
	public int getPoints() {
		return points;
	}

    /**
     * Returns whether the player has finished their participation in the game.
     * 
     * @return True if the player has finished, false otherwise.
     */
	public boolean isFinished() {
		return finished;
	}
	
    /**
     * Returns whether the player has lost.
     * 
     * @return True if the player has lost, false otherwise.
     */
	public boolean isLost() {
		return lost;
	}

    /**
     * Returns the type of the player.
     * 
     * @return The type of the player.
     */
	public String getType() {
		return type;
	}
	
    /**
     * Returns the player's action in the current turn.
     * 
     * @return The action of the player.
     */
	public int getAction() {
		return action;
	}

    /**
     * Abstract method for playing their turn in the game.
     * 
     * @param cards The list of available cards.
     * @return The card that the player gets.
     */
	public abstract Card play(ArrayList<Card> cards);
}