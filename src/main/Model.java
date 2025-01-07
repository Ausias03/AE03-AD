package main;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Model {
	private String sessionUsername = "";
	private String sessionPwd = "";
	private MongoClient mongoClient = new MongoClient("localhost", 27017);
	private MongoDatabase database = mongoClient.getDatabase("casino");

	public boolean logInUser(String username, String pwd) {
		MongoCollection<Document> collection = database.getCollection("users");
		String pwdHash = DigestUtils.sha256Hex(pwd);
		if (userExists(username, pwdHash, collection)) {
			sessionUsername = username;
			sessionPwd = pwdHash;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean signUpUser(String username, String pwd) {
		MongoCollection<Document> collection = database.getCollection("users");
		String pwdHash = DigestUtils.sha256Hex(pwd);
		if (!userExists(username, pwdHash, collection)) {
			Document newUser = new Document("user", username).append("pass", pwdHash);
			collection.insertOne(newUser);
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

	private boolean userExists(String username, String pwd, MongoCollection<Document> collection) {
		Document filtro = new Document("user", username).append("pass", pwd);
		Document usuarioEncontrado = collection.find(filtro).first();
		return usuarioEncontrado != null;
	}
}
