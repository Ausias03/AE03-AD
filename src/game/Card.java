package game;

/**
 * Represents a playing card with a suit, points value, and image encoded in base64.
 */
public class Card {
	/**
	 * The suit of the card (e.g., "Hearts", "Spades").
	 */
	private String suit;
	/**
	 * The points value of the card.
	 */
	private int points;
	/**
	 * The base64-encoded image of the card.
	 */
	private String base64;
	
    /**
     * Returns the suit of the card.
     * 
     * @return The suit of the card.
     */
	public String getSuit() {
		return suit;
	}
	
    /**
     * Returns the points value of the card.
     * 
     * @return The points value of the card.
     */
	public int getPoints() {
		return points;
	}
	
    /**
     * Returns the base64-encoded image of the card.
     * 
     * @return The base64-encoded image of the card.
     */
	public String getBase64() {
		return base64;
	}

    /**
     * Constructs a new card with the specified suit, points, and base64 image.
     * 
     * @param suit The suit of the card.
     * @param points The points value of the card.
     * @param base64 The base64-encoded image of the card.
     */
	public Card(String suit, int points, String base64) {
		this.suit = suit;
		this.points = points;
		this.base64 = base64;
	}
}
