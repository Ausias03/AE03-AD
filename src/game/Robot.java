package game;

import java.util.ArrayList;

public class Robot extends Player {
	public Robot() {
		this.type = "Robot";
	}
	
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
