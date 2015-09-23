package com.buraquera.service;

import java.util.List;

import com.buraquera.model.Markers;
import com.buraquera.repository.MarkersDAOImpl;

/**
 * @author bruno ferrari
 *
 */
public class MarkersServiceImpl implements MarkersService {
	
	@Override
	public List<Markers> getAllMarkers() {
		MarkersDAOImpl dao = new MarkersDAOImpl();
		List<Markers> result = dao.listMarkers();
		return result;
	}

	@Override
	public void storeNewMarker(Double latitude, Double longitude, String email) {
		Markers marker = createMarker(latitude, longitude, email);
		
		MarkersDAOImpl dao = new MarkersDAOImpl();
		dao.saveNewMarker(marker);
		System.out.println("saved!");
	}
	
	public Markers createMarker(Double latitude, Double longitude, String email) {
		Markers marker = new Markers();
		marker.setLatitude(latitude);
		marker.setLongitude(longitude);
		marker.setEmail(email);
		
		return marker;
	}
}
