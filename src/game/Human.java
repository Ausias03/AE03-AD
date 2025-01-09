package game;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Human extends Player {
	public Human() {
		this.type = "Human";
	}

    @Override
    public void play(ArrayList<Card> cards) {
    	String[] options = { "Stand", "Hit" };
    	int choice = JOptionPane.showOptionDialog(null, "Select Action:", "Action",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
    	
    	if (choice == 1) {
    		this.points += cards.get(cards.size() - 1).getPoints();
    		cards.removeLast();
    		if (this.points > 21) {
    			this.finished = true;
    			this.lost = true;
    		}
    	} else {
    		
    	}
    }
}
