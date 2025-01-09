package game;

import javax.swing.JOptionPane;

public class Game {
	public final String[] options = { "Crupier (A.I.)", "User (human)" };
	
	private Player[] players = new Player[2];
	private boolean finished = false;
	
	public boolean isFinished() {
		return finished;
	}
	
	public Game(int whoStartsFirst) {
		if(whoStartsFirst == 0) {
			players[0] = new Robot();
			players[1] = new Human();
		}
		else {
			players[0] = new Human();
			players[1] = new Robot();
		}
	}
	
	public void start() {
		while(!players[0].isFinished && !players[1].isFinished) {
			JOptionPane.showMessageDialog(null, String.format("%s's turn", players[0].getType()), "Turn",
					JOptionPane.INFORMATION_MESSAGE);
			if(!players[0].isFinished) {
				players[0].play();
				if(players[0].isLost()) {
					JOptionPane.showMessageDialog(null, String.format("The winner is %s", players[1].getType()), "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}				
			}
			JOptionPane.showMessageDialog(null, String.format("%s's turn", players[1].getType()), "Turn",
					JOptionPane.INFORMATION_MESSAGE);
			if(!players[1].isFinished()) {
				players[1].play();
				if(players[1].isLost()) {
					JOptionPane.showMessageDialog(null, String.format("The winner is %s", players[0].getType()), "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}				
			}
		}
		
		if(!players[0].isLost() && !players[1].isLost()) {
			if(players[0].getPoints() == players[1].getPoints()) {
				JOptionPane.showMessageDialog(null, "It's a draw", "Draw",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, String.format("The winner is %s", whoWins()), "Winner",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private String whoWins() {
		return players[0].getPoints > players[1].getPoints() ? players[0].getType() : players[1].getType();
	}
}
