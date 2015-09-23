package com.buraquera.service;

import java.util.List;

import com.buraquera.model.Markers;

/**
 * @author bruno
 *
 */
public interface MarkersService {

	/**
	 * @return
	 */
	List<Markers> getAllMarkers();
	
	/**
	 * @param latitude
	 * @param longitude
	 * @param email
	 */
	void storeNewMarker(Double latitude, Double longitude, String email);
}
