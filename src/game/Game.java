package game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import mvc.Principal;

/**
 * Represents a blackjack game between a human player and a croupier (AI).
 */
public class Game {
	/**
	 * Array of player types.
	 */
	public final String[] options = { "Crupier (A.I.)", "User (human)" };
	/**
	 * Array of possible suits.
	 */
	public final String[] suits = { "ES", "FR" };
	
	/**
	 * Timestamp of the game start.
	 */
	private String timeStamp;
	/**
	 * Index of the current suit.
	 */
	private int suit;
	/**
	 * List of cards available for the game.
	 */
	private ArrayList<Card> cards;
	/**
	 * Array of players in the game.
	 */
	private Player[] players = new Player[2];
	/**
	 * Flag to indicate if the game has finished.
	 */
	private boolean finished = false;
	
    /**
     * Returns the timestamp of the game.
     * 
     * @return The timestamp of the game.
     */
	public String getTimeStamp() {
		return timeStamp;
	}
	
    /**
     * Returns whether the game is finished.
     * 
     * @return True if the game is finished, false otherwise.
     */
	public boolean isFinished() {
		return finished;
	}
	
    /**
     * Returns the string representation of the current suit.
     * 
     * @return The current suit.
     */
	public String getSuitString() {
		return suits[suit];
	}
	
    /**
     * Returns the human player in the game.
     * 
     * @return The human player or null if not found.
     */
	public Human getHumanPlayer() {
		for(Player player : players) {
			if(player instanceof Human)
				return (Human)player;
		}
		return null;
	}
	
    /**
     * Initializes a new game with the specified starting player, suit, and list of cards.
     * 
     * @param whoStartsFirst The player who starts first (0 for Robot, 1 for Human).
     * @param suit The index of the suit to be used in the game.
     * @param cards The list of cards to be used in the game.
     */
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
		
		this.suit = suit;
	}
	
    /**
     * Starts the game and handles the turns for both players until the game ends.
     */
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
	
    /**
     * Determines the winner based on the players' points.
     * 
     * @return The type of the winning player.
     */
	private String whoWins() {
		return players[0].getPoints() > players[1].getPoints() ? players[0].getType() : players[1].getType();
	}
}
