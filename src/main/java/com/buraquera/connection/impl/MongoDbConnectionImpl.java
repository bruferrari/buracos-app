package com.buraquera.connection.impl;

import java.net.UnknownHostException;

import com.buraquera.connection.model.MongoDbConnection;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


public class MongoDbConnectionImpl implements MongoDbConnection {
	
	private DB db;
	private MongoClient dbClient;
	
	private static final String URI = "localhost";
	private static final int PORT = 27017;
	private static final String TAG = MongoDbConnectionImpl.class.getName();
	
	@Override
	public DB getConnection() {
		
		try {
			dbClient = new MongoClient(URI, PORT);
			db = dbClient.getDB("buracodb");
			
			System.out.println(TAG + "\nNew connection established!");
			
		} catch (MongoException | UnknownHostException e) {
			e.printStackTrace();
		}
		return db;
	}
}
