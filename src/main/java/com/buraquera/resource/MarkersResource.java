package com.buraquera.resource;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.buraquera.model.Markers;
import com.buraquera.repository.MarkersDAOImpl;
import com.buraquera.service.MarkersServiceImpl;

/**
 * @author bruno ferrari
 *
 */

@Path("/markers")
public class MarkersResource implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Markers> result;
	private MarkersDAOImpl dao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Markers> allMarkers() {
		result = new MarkersServiceImpl().getAllMarkers();
		return result;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newMarker(@FormParam("latitude") Double latitude,
			@FormParam("longitude") Double longitude,
			@FormParam("email") String email,
			@Context HttpServletResponse servletResponse) throws IOException{
		new MarkersServiceImpl().storeNewMarker(latitude, longitude, email);
	}
}
