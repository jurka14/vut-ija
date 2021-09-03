package ija.ija2019.homework2.maps;

import ija.ija2019.homework2.maps.Coordinate;
import ija.ija2019.homework2.maps.Street;
import ija.ija2019.homework2.myMaps.MyStop;



public interface Stop {
	
	public java.lang.String getId();
	
	public Coordinate getCoordinate();
	
	public void setStreet(Street s);
	
	public Street getStreet();
	
	public static Stop defaultStop​(java.lang.String id, Coordinate c) {
		Stop ms = new MyStop(id, c);
		return ms;
	}
}
