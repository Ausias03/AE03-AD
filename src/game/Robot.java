package game;

import java.util.ArrayList;

public class Robot extends Player {
	public Robot() {
		this.type = "Robot";
	}
	
    @Override
    public void play(ArrayList<Card> cards) {
    	String[] options = { "Stand", "Hit" };
    }
}
