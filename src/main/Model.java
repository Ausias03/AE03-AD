package main;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Model {
	private String sessionUsername;
	private String sessionPwd;
	private MongoClient mongoClient = new MongoClient("localhost", 27017);
	private MongoDatabase database = mongoClient.getDatabase("Casino");
	
	public boolean signUpUser(String username, String pwd) {
		MongoCollection<Document> coleccion = database.getCollection("users");
		Document newUser = new Document("user", username).append("pass", pwd);
	}
	
	private boolean userExists(String username, String pwd) {
		
	}
}
