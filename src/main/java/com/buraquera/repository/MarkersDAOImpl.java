package com.buraquera.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.buraquera.connection.impl.MongoDbConnectionImpl;
import com.buraquera.converters.MarkerReadConverter;
import com.buraquera.model.Markers;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * @author bruno ferrari
 *
 */
public class MarkersDAOImpl implements Serializable, MarkersDAO {

	private static final long serialVersionUID = 1L;
	private MongoDbConnectionImpl conn = new MongoDbConnectionImpl();
	private DBCollection collection;
	private DB database;
	private MarkerReadConverter converter = new MarkerReadConverter();
	private Markers marker;
	
	/**
	 * @param marker
	 * @return
	 */
	private static DBObject createDbObject(Markers marker) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		
		docBuilder.append("_id", marker.getId());
		docBuilder.append("latitude", marker.getLatitude());
		docBuilder.append("longitude", marker.getLongitude());
		docBuilder.append("email", marker.getEmail());
		
		return docBuilder.get();
	}
	
	/**
	 * @param marker
	 */
	@Override
	public void saveNewMarker(Markers marker) {
		database = conn.getConnection();
		collection = database.getCollection("markers");
		collection.insert(createDbObject(marker));
	}
	
	/**
	 * @return
	 */
	@Override
	public List<Markers> listMarkers() {
		database = conn.getConnection();
		collection = database.getCollection("markers");

		List<Markers> result = new ArrayList<Markers>();
		DBCursor cursor = collection.find();
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			marker = new Markers();
			marker = converter.convert(obj);
			result.add(marker);
		}
		return result;
	}
	
	/**
	 * @param email
	 */
	@Override
	public void getMarkerByEmail(String email) {
		database = conn.getConnection();
		collection = database.getCollection("markers");
		
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		DBCursor cursor = collection.find(query);
		
		while(cursor.hasNext()) {
			// DO anything
			System.out.println(cursor.next());
		}
	}
	
	/**
	 * @param id
	 */
	@Override
	public void deleteMarker(Long id) {
		database = conn.getConnection();
		collection = database.getCollection("markers");
		try{
			BasicDBObject query = new BasicDBObject();
			query.put("_id", id);
			collection.remove(query);
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
}
