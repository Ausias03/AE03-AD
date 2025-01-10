package game;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Human extends Player {
	public Human() {
		this.type = "Human";
	}

    @Override
    public Card play(ArrayList<Card> cards) {
    	String[] options = { "Stand", "Hit" };
    	int choice = JOptionPane.showOptionDialog(null, "Select Action:", "Action",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
    	
    	if (choice == 1) {
    		Card card = cards.removeLast();
    		if(card.getPoints() == 1) {
    			String[] values = { "1", "11" };
    	    	int pointsChoice = JOptionPane.showOptionDialog(null, "Select Value:", "Value",
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
    	
    	return null;
    }
}
