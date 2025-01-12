package game;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Represents a human player in the game.
 * Extends the Player class and implements the play method for human-specific behavior.
 */
public class Human extends Player {
    /** 
     * Constructs a new Human player with the type set to "Human".
     */
	public Human() {
		this.type = "Human";
	}

    /**
     * Allows the human player to choose an action ("Stand" or "Hit").
     * If the player chooses "Hit", a card is drawn, and the points are updated.
     * If the drawn card is an Ace, the player is prompted to select a value (1 or 11).
     * The game ends if the player's points exceed 21.
     * 
     * @param cards The list of available cards to choose from.
     * @return The card drawn by the player, or null if the player chooses "Stand".
     */
    @Override
    public Card play(ArrayList<Card> cards) {
    	String[] options = { "Stand", "Hit" };
    	int choice = JOptionPane.showOptionDialog(null, "Select Action:", "Action",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
    	
    	if (choice == 1) {
    		this.action = 1;
    		Card card = cards.removeLast();
    		if(card.getPoints() == 1) {
    			String[] values = { "1", "11" };
    	    	int pointsChoice = JOptionPane.showOptionDialog(null, "Select Value:", "You got an Ace!",
    					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, values, values[1]);
    	    	this.points += (pointsChoice == 0) ? 1 : 11;
    		}
    		else
    			this.points += card.getPoints();
    		
    		if (this.points > 21) {
    			this.finished = true;
    			this.lost = true;
    		}
    		
    		return card;
    	}
    	
    	this.action = 0;
    	return null;
    }
}
