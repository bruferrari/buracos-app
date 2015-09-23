package com.buraquera.repository;

import java.util.List;

import com.buraquera.model.Markers;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * @author bruno ferrari
 * Contract for implement MarkersDAO
 */
public interface MarkersDAO {
	
	/**
	 * @param marker
	 */
	void saveNewMarker(Markers marker);
	
	/**
	 * @return
	 */
	List<Markers> listMarkers();
	
	/**
	 * @param email
	 */
	void getMarkerByEmail(String email);
	
	/**
	 * @param id
	 */
	void deleteMarker(Long id);
	
}
