package com.buraquera.connection.model;

import com.mongodb.DB;

/**
 * @author bruno ferrari
 *
 */
public interface MongoDbConnection {

	/**
	 * @return
	 */
	DB getConnection();
}
