package main;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

public class Model {
	private MongoClient dbClient;
	private MongoDatabase db;
	private String sessionUsername;
	private String sessionPwd;
	private final File cardsDirectory = new File("resources/cards");
	
	public void openConnection() {
		
	}
	
	public void closeConnection() {
		
	}
	
	public boolean signUpUser(String username, String pwd) {
		MongoCollection<Document> coleccion = db.getCollection("users");
		Document newUser = new Document("user", username).append("pass", pwd);
	}
	
	private boolean userExists(String username, String pwd) {

	}
	
	public void loadCardsToDb() {
		
	}
	
	private String encodeImageToBase64(File image) throws Exception {
		String encodedImage;
		FileInputStream fis = new FileInputStream(image);
		encodedImage = new String(Base64.getEncoder().encode(fis.readAllBytes()));
		fis.close();
		return encodedImage;
	}
}
