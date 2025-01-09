package game;

public class Robot extends Player {
	public Robot() {
		this.type = "Robot";
	}
	
    @Override
    public void play() {
        System.out.println("Robot playing autonomously.");
    }
}
