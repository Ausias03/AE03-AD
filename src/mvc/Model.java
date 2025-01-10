package mvc;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import game.Card;
import game.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

/**
 * Model class handles game data, user authentication, and interaction with the MongoDB database.
 * It manages game state, user login, card generation, and storing game scores.
 */
public class Model {
	/**
	 * Directory for card images.
	 */
	public final File cardsDirectory = new File("resources/cards");
	/**
	 * File containing database connection details.
	 */
	public final File dbDataFile = new File("resources/db_data.json");
	/**
	 * Number of collections before card-related collections.
	 */
	public final int collectionsBeforeCards = 2;
	/**
	 * Array of card collections for different languages.
	 */
	public final String[] cardsCollections = new String[] { "cards_es", "cards_fr" };
	
	/**
	 * The username of the player.
	 */
	private String sessionUsername = "";
	/**
	 * The connection to MongoDb.
	 */
	private MongoClient dbClient;
	/**
	 * The database connected.
	 */
	private MongoDatabase db;
	/**
	 * Array with the collections of the database.
	 */
	private MongoCollection<Document>[] collections;
	/**
	 * The current instance of the game.
	 */
	private Game game = null;
	
    /**
     * Retrieves the current game instance.
     * 
     * @return The current game instance.
     */
	public Game getGame() {
		return game;
	}
	
    /**
     * Initializes the Model and opens a connection to the database.
     */
  	public Model() {
		try {
			openConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

    /**
     * Logs in a user by verifying credentials.
     * 
     * @param username The username of the user.
     * @param pwd The password of the user.
     * @return True if login is successful, false otherwise.
     */
	public boolean logInUser(String username, String pwd) {
		String pwdHash = DigestUtils.sha256Hex(pwd);
		if (userExists(username, pwdHash)) {
			sessionUsername = username;
			return true;
		} else {
			return false;
		}
	}

    /**
     * Signs up a new user by storing their credentials in the database.
     * 
     * @param username The username of the user.
     * @param pwd The password of the user.
     * @return True if sign-up is successful, false if the user already exists.
     */
	public boolean signUpUser(String username, String pwd) {
		String pwdHash = DigestUtils.sha256Hex(pwd);
		if (!userExists(username, pwdHash)) {
			Document newUser = new Document("user", username).append("pass", pwdHash);
			collections[0].insertOne(newUser);
			return true;
		} else {
			return false;
		}
	}

    /**
     * Logs out the current user.
     * 
     * @return True if logout is successful, false if no user is logged in.
     */
	public boolean logOutUser() {
		if (sessionUsername.length() == 0) {
			return false;
		} else {
			sessionUsername = "";
			return true;
		}
	}

    /**
     * Opens a connection to the MongoDB database using connection data from a JSON file.
     * 
     * @throws Exception If an error occurs while opening the connection.
     */
	public void openConnection() throws Exception {
		JSONObject dbData = readDbConnectionJson();
		dbClient = new MongoClient(dbData.getString("ip"), dbData.getInt("port"));
		db = dbClient.getDatabase(dbData.getString("db_name"));
		JSONArray collectionsJsonArray = dbData.getJSONArray("collections");
		int collectionsLength = collectionsJsonArray.length();
		collections = new MongoCollection[collectionsLength + cardsCollections.length];
		for (int i = 0; i < collectionsLength; i++) {
			collections[i] = db.getCollection(collectionsJsonArray.getString(i));
		}
	}

    /**
     * Closes the connection to the MongoDB database.
     */
	public void closeConnection() {
		dbClient.close();
	}
	
    /**
     * Checks if the cards have been loaded into the database.
     * 
     * @return True if the cards are loaded, false otherwise.
     */
	public boolean cardsLoaded() {
		return collections[collections.length - 1] != null;
	}
	
    /**
     * Starts a new game with the specified settings.
     * 
     * @param whoStartsFirst The player who starts first.
     * @param suit The suit for the game.
     */
	public void startGame(int whoStartsFirst, int suit) {
		game = new Game(whoStartsFirst, suit, generateRandomCards(suit));
		game.start();
	}

    /**
     * Loads card data from the file system and stores it in the database.
     * 
     * @throws Exception If an error occurs while loading the cards.
     */
	public void loadCardsToDb() throws Exception {
		File[] countryCardsDirs = cardsDirectory.listFiles();
		for (int i = 0; i < countryCardsDirs.length; i++) {
			createCardsCollection(cardsCollections[i]);
			ArrayList<Document> cards = new ArrayList<Document>();
			for (File card : countryCardsDirs[i].listFiles()) {
				String[] cardNameSplitted = card.getName().split("_");
				String suit = cardNameSplitted[0];
				int points = Integer.parseInt(cardNameSplitted[1].split("\\.")[0]);
				if(points > 10)
					points = 10;
				
				Document cardDocument = new Document();
				cardDocument.append("suit", suit);
				cardDocument.append("points", points);
				cardDocument.append("base64", encodeImageToBase64(card));

				cards.add(cardDocument);
			}
			collections[i + collectionsBeforeCards].insertMany(cards);
		}
	}
	
    /**
     * Checks if a user is currently logged in.
     * 
     * @return True if the user is logged in, false otherwise.
     */
	public boolean isLogged() {
		return sessionUsername.length() != 0;
	}
	
    /**
     * Generates a shuffled list of cards based on the specified suit.
     * 
     * @param suit The suit to generate cards for.
     * @return A shuffled list of cards.
     */
	public ArrayList<Card> generateRandomCards(int suit) {
		final int iterations = 1000;
		
		Random random = new Random();
		int i = 0;
		ArrayList<Card> cardsArray = new ArrayList<Card>();
		for(Document card : collections[suit + collectionsBeforeCards].find()) {
			cardsArray.add(new Card(card.getString("suit"), card.getInteger("points"), card.getString("base64")));
		}

		Integer[] posToShuffle = new Integer[2];

		while (i < iterations) {
			posToShuffle[0] = null;
			posToShuffle[1] = null;
			
			do {
				posToShuffle[0] = random.nextInt(cardsArray.size());
				posToShuffle[1] = random.nextInt(cardsArray.size());
			} while (posToShuffle[0] == posToShuffle[1]);
			
			Card aux = cardsArray.get(posToShuffle[0]);
			cardsArray.set(posToShuffle[0], cardsArray.get(posToShuffle[1]));
			cardsArray.set(posToShuffle[1], aux);
			
			i++;
		}

		return cardsArray;
	}
	
    /**
     * Saves the current game score to the database.
     */
	public void saveGameToScores() {
		Document score = new Document().append("user", sessionUsername)
									   .append("suit", String.format("Suit %s", game.getSuitString()))
									   .append("points", game.getHumanPlayer().getPoints())
									   .append("timestamp", game.getTimeStamp());
		
		collections[1].insertOne(score);
	}
	
    /**
     * Retrieves the hall of fame from the database.
     * 
     * @return A string containing the hall of fame.
     */
	public String obtainHallOfFame() {
		String hallOfFame = "";
		for(Document score : collections[1].find()) {
			hallOfFame += String.format("%s %d points (%s, %s)\n", score.getString("user"), score.getInteger("points"), score.getString("suit"), score.getString("timestamp"));
		}
		return hallOfFame;
	}

    /**
     * Checks if a user exists in the database.
     * 
     * @param username The username to check.
     * @param pwd The password hash of the user.
     * @param collection The collection to search.
     * @return True if the user exists, false otherwise.
     */
	private boolean userExists(String username, String pwd) {
		Document filtro = new Document("user", username).append("pass", pwd);
		Document usuarioEncontrado = collections[0].find(filtro).first();
		return usuarioEncontrado != null;
	}

    /**
     * Reads the database connection details from the JSON file.
     * 
     * @return A JSONObject containing the database connection details.
     * @throws Exception If an error occurs while reading the file.
     */
	private JSONObject readDbConnectionJson() throws Exception {
		String jsonString = "";
		FileReader fr = new FileReader(dbDataFile);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null)
			jsonString += line;
		br.close();
		fr.close();
		return new JSONObject(jsonString);
	}
	
    /**
     * Creates a new collection for cards in the database.
     * 
     * @param name The name of the collection.
     * @throws Exception If an error occurs while creating the collection.
     */
	private void createCardsCollection(String name) throws Exception {
		MongoCollection<Document> collection = db.getCollection(name);
		if(collection != null)
			collection.drop();
		db.createCollection(name);
		collections[nextCollectionSpotAvailable()] = db.getCollection(name);
	}
	
    /**
     * Finds the next available spot for a collection.
     * 
     * @return The index of the next available spot.
     */
	private int nextCollectionSpotAvailable() {
		for(int i = 0; i < collections.length; i++) {
			if(collections[i] == null)
				return i;
		}
		return -1;
	}

    /**
     * Encodes an image to a Base64 string.
     * 
     * @param image The image file to encode.
     * @return The Base64 encoded string.
     * @throws Exception If an error occurs while encoding the image.
     */
	private String encodeImageToBase64(File image) throws Exception {
		String encodedImage;
		FileInputStream fis = new FileInputStream(image);
		encodedImage = new String(Base64.getEncoder().encode(fis.readAllBytes()));
		fis.close();
		return encodedImage;
	}
}
