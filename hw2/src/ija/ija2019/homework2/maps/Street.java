package ija.ija2019.homework2.maps;

import ija.ija2019.homework2.myMaps.MyStreet;

public interface Street {
	
	public java.lang.String getId();
	
	public java.util.List<Coordinate> getCoordinates();
	
	public Coordinate begin();
	
	public Coordinate end();
	
	public java.util.List<Stop> getStops();
	
	public boolean addStop(Stop stop);
	
	public boolean follows(Street s);
	
	public static Street defaultStreet(String id, Coordinate ...coordinates) {
		Street ms = new MyStreet(id, coordinates);
		
		if(ms.getId() == null) {
			return null;
		}
		
		return ms;
	}
}
