package game;

public class Human extends Player {
	public Human() {
		this.type = "Human";
	}

    @Override
    public void play() {
        System.out.println("Human playing with default input.");
    }
}
