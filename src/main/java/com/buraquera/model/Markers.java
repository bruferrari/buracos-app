package com.buraquera.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;

/**
 * @author bruno ferrari
 *
 */

@XmlRootElement
public class Markers implements Serializable {

	private static final long serialVersionUID = 1L;

	private ObjectId id;
	private Double latitude;
	private Double longitude;
	private String email;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

}
