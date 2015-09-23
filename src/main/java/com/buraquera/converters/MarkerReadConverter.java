package com.buraquera.converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.buraquera.model.Markers;
import com.mongodb.DBObject;

/**
 * @author bruno ferrari
 * Converter for DBObject into POJO
 *
 */
@ReadingConverter
public class MarkerReadConverter implements Converter<DBObject, Markers> {

	@Override
	public Markers convert(DBObject source) {
		Markers m = new Markers();
		
		m.setId((ObjectId) source.get("_id"));
		m.setLatitude((Double) source.get("latitude"));
		m.setLongitude((Double) source.get("longitude"));
		m.setEmail((String) source.get("email"));
		
		return m;
	}

}
