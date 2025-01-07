package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

public class Model {
	private MongoClient dbClient;
	private MongoDatabase db;
	private final File cardsDirectory = new File("resources/cards");
	
	public void openConnection() {
		
	}
	
	public void closeConnection() {
		
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
