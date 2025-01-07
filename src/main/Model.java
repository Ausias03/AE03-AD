package main;

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

public class Model {
	private MongoClient dbClient;
	private MongoDatabase db;
	private MongoCollection<Document>[] collections;
	private String sessionUsername;
	private String sessionPwd;
	private final File cardsDirectory = new File("resources/cards");
	private final File dbDataFile = new File("resources/db_data.json");
	
	public void openConnection() throws Exception {
		JSONObject dbData = readDbConnectionJson();
		dbClient = new MongoClient(dbData.getString("ip"), dbData.getInt("port"));
		db = dbClient.getDatabase(dbData.getString("db_name"));
		JSONArray collectionsJsonArray = dbData.getJSONArray("collections");
		collections = new MongoCollection[collectionsJsonArray.length()];
		for(int i = 0; i < collections.length; i++) {
			collections[i] = db.getCollection(collectionsJsonArray.getString(i));
		}
	}
	
	public void closeConnection() {
		dbClient.close();
	}
	
	public boolean signUpUser(String username, String pwd) {
		MongoCollection<Document> coleccion = db.getCollection("users");
		Document newUser = new Document("user", username).append("pass", pwd);
	}
	
	private boolean userExists(String username, String pwd) {

	}
	
	public void loadCardsToDb() throws Exception {
		File[] countryCardsDirs = cardsDirectory.listFiles();
		for(int i = 0; i < countryCardsDirs.length; i++) {
			ArrayList<Document> cards = new ArrayList<Document>();
			for(File card : countryCardsDirs[i].listFiles()) {
				String[] cardNameSplitted = card.getName().split("_");
				String suit = cardNameSplitted[0];
				int points = Integer.parseInt(cardNameSplitted[1].split(".")[0]);
				
				Document cardDocument = new Document();
				cardDocument.append("suit", suit);
				cardDocument.append("points", points);
				cardDocument.append("base64", encodeImageToBase64(card));
				
				cards.add(cardDocument);
			}
			collections[i].insertMany(cards);
		}
	}
	
	private JSONObject readDbConnectionJson() throws Exception {
		String jsonString = "";
		FileReader fr = new FileReader(dbDataFile);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null)
			jsonString += line;
		br.close();
		fr.close();
		return new JSONObject(jsonString);
	}
	
	private String encodeImageToBase64(File image) throws Exception {
		String encodedImage;
		FileInputStream fis = new FileInputStream(image);
		encodedImage = new String(Base64.getEncoder().encode(fis.readAllBytes()));
		fis.close();
		return encodedImage;
	}
}
