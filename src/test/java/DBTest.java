import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.buraquera.connection.impl.MongoDbConnectionImpl;
import com.buraquera.model.Markers;
import com.buraquera.repository.MarkersDAOImpl;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class DBTest {
	
	private Markers marker;
	private DB db;
	DBCollection collection;
	
	@Test
	@Ignore
	public void testDbConnection() {
		MongoDbConnectionImpl db = new MongoDbConnectionImpl();
		db.getConnection();
	}
	
	private Markers createMarker() {
		marker = new Markers();
		marker.setLatitude(21.12383874835);
		marker.setLongitude(34.19283816232);
		marker.setEmail("teste@teste.com");
		
		return marker;
	}
	
	@Test
	@Ignore
	public void testInsert() {
		MarkersDAOImpl dao = new MarkersDAOImpl();
		marker = createMarker();
		dao.saveNewMarker(marker);
	}
	
	@Test
	@Ignore
	public void testListAllMarkers() {
		MarkersDAOImpl dao = new MarkersDAOImpl();
		List<Markers> result = new ArrayList<Markers>();
		result = dao.listMarkers();
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void testListMarkerByEmail() {
		MarkersDAOImpl dao = new MarkersDAOImpl();
		dao.getMarkerByEmail("b_ferrari@live.com");
	}
	
	@Test
	@Ignore
	public void testDeleteById() {
		MarkersDAOImpl dao = new MarkersDAOImpl();
		dao.deleteMarker(2L);
	}
}
