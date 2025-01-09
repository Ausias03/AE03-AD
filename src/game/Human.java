package game;

import java.util.ArrayList;

public class Human extends Player {
	public Human() {
		this.type = "Human";
	}

    @Override
    public void play(ArrayList<Card> cards) {
        System.out.println("Human playing with default input.");
    }
}
