package game;

import java.util.ArrayList;

/**
 * Represents a robot (AI) player in the game.
 * Extends the Player class and implements the play method for robot-specific behavior.
 */
public class Robot extends Player {
    /** 
     * Constructs a new Robot player with the type set to "Robot".
     */
	public Robot() {
		this.type = "Robot";
	}
	
    /**
     * Allows the robot player to play a turn.
     * The robot will draw cards (Hit) if its points are less than 17.
     * If the robot's points exceed 21, it loses the game.
     * The robot stops playing when the robot's points are 17 or higher.
     * 
     * @param cards The list of available cards to choose from.
     * @return The card drawn by the robot, or null if the robot stands.
     */
    @Override
    public Card play(ArrayList<Card> cards) {
    	if(this.finished)
    		return null;
    	
    	if(points < 17) {
    		this.action = 1;
    		Card card = cards.removeLast();
    		
    		if(card.getPoints() == 1)
    			this.points += (this.points + 11 > 21) ? 1 : 11;
    		else
    			this.points += card.getPoints();
    		
    		if(this.points > 21) {
    			this.lost = true;
    			this.finished = true;
    		}
    		
    		return card;
    	}
    	
		this.finished = true;
		this.action = 0;
		return null;
    }
}
