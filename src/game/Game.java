package game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import mvc.Principal;

public class Game {
	public final String[] options = { "Crupier (A.I.)", "User (human)" };
	public final String[] suits = { "ES", "FR" };
	
	private String timeStamp;
	private int suit;
	private ArrayList<Card> cards;
	private Player[] players = new Player[2];
	private boolean finished = false;
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public String getSuitString() {
		return suits[suit];
	}
	
	public Human getHumanPlayer() {
		for(Player player : players) {
			if(player instanceof Human)
				return (Human)player;
		}
		return null;
	}
	
	public Game(int whoStartsFirst, int suit, ArrayList<Card> cards) {
		if(whoStartsFirst == 0) {
			players[0] = new Robot();
			players[1] = new Human();
		}
		else {
			players[0] = new Human();
			players[1] = new Robot();
		}
		this.cards = cards;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		timeStamp = sdf.format(new Date());
	}
	
	public void start() {
		while(true) {
			JOptionPane.showMessageDialog(null, String.format("%s's turn", players[0].getType()), "Turn",
					JOptionPane.INFORMATION_MESSAGE);
			if(!players[0].isFinished()) {
				Principal.getControlador().updateCard(players[0].play(cards), players[0]);
				if(players[0].isLost()) {
					JOptionPane.showMessageDialog(null, String.format("The winner is %s", players[1].getType()), "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				if(players[0].getPoints() == 21) {
					break;
				}
			}
			
			JOptionPane.showMessageDialog(null, String.format("%s's turn", players[1].getType()), "Turn",
					JOptionPane.INFORMATION_MESSAGE);
			if(!players[1].isFinished()) {
				Principal.getControlador().updateCard(players[1].play(cards), players[1]);
				if(players[1].isLost()) {
					JOptionPane.showMessageDialog(null, String.format("The winner is %s", players[0].getType()), "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				if(players[1].getPoints() == 21) {
					break;
				}
			}
			
			if(players[0].getAction() == 0 && players[1].getAction() == 0)
				break;
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
		return players[0].getPoints() > players[1].getPoints() ? players[0].getType() : players[1].getType();
	}
}
