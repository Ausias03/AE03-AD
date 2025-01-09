package mvc;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

public class Model {
	private final File cardsDirectory = new File("resources/cards");
	private final File dbDataFile = new File("resources/db_data.json");
	private final int collectionsBeforeCards = 2;
	private final String[] cardsCollections = new String[] { "cards_es", "cards_fr" };
	
	private String sessionUsername = "";
	private String sessionPwd = "";
	private MongoClient dbClient;
	private MongoDatabase db;
	private MongoCollection<Document>[] collections;
	
  	public Model() {
		try {
			openConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean logInUser(String username, String pwd) {
		String pwdHash = DigestUtils.sha256Hex(pwd);
		if (userExists(username, pwdHash, collections[0])) {
			sessionUsername = username;
			sessionPwd = pwdHash;
			return true;
		} else {
			return false;
		}
	}

	public boolean signUpUser(String username, String pwd) {
		String pwdHash = DigestUtils.sha256Hex(pwd);
		if (!userExists(username, pwdHash, collections[0])) {
			Document newUser = new Document("user", username).append("pass", pwdHash);
			collections[0].insertOne(newUser);
			return true;
		} else {
			return false;
		}
	}

	public boolean logOutUser() {
		if (sessionUsername.length() == 0) {
			return false;
		} else {
			sessionUsername = "";
			sessionPwd = "";
			return true;
		}
	}

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

	public void closeConnection() {
		dbClient.close();
	}

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
	
	public boolean isLogged() {
		return sessionUsername.length() != 0;
	}
	
	public ArrayList<Integer> generateRandomNumbers(int arrayLength) {
		final int iterations = 1000;
		
		ArrayList<Integer> numberArray = new ArrayList<Integer>();
		Random random = new Random();
		int i = 0;

		for (int j = 1; j <= arrayLength; j++) {
			numberArray.add(j);
		}

		Integer[] posToShuffle = new Integer[2];

		while (i < iterations) {
			posToShuffle[0] = null;
			posToShuffle[1] = null;
			
			do {
				posToShuffle[0] = random.nextInt(arrayLength);
				posToShuffle[1] = random.nextInt(arrayLength);
			} while (posToShuffle[0] == posToShuffle[1]);
			
			int aux = numberArray.get(posToShuffle[0]);
			numberArray.set(posToShuffle[0], numberArray.get(posToShuffle[1]));
			numberArray.set(posToShuffle[1], aux);
			
			i++;
		}

		return numberArray;
	}

	private boolean userExists(String username, String pwd, MongoCollection<Document> collection) {
		Document filtro = new Document("user", username).append("pass", pwd);
		Document usuarioEncontrado = collection.find(filtro).first();
		return usuarioEncontrado != null;
	}

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
	
	private void createCardsCollection(String name) throws Exception {
		MongoCollection<Document> collection = db.getCollection(name);
		if(collection != null)
			collection.drop();
		db.createCollection(name);
		collections[nextCollectionSpotAvailable()] = db.getCollection(name);
	}
	
	private int nextCollectionSpotAvailable() {
		for(int i = 0; i < collections.length; i++) {
			if(collections[i] == null)
				return i;
		}
		return -1;
	}

	private String encodeImageToBase64(File image) throws Exception {
		String encodedImage;
		FileInputStream fis = new FileInputStream(image);
		encodedImage = new String(Base64.getEncoder().encode(fis.readAllBytes()));
		fis.close();
		return encodedImage;
	}
}
