package ija.ija2019.homework2.maps;

import ija.ija2019.homework2.myMaps.MyLine;

public interface Line {
	
	public static Line defaultLine(java.lang.String id) {
		Line ml = new MyLine(id);
		return ml;
	}
	
	public boolean addStop(Stop stop);
	
	public boolean addStreet(Street street);
	
	public java.util.List<java.util.AbstractMap.SimpleImmutableEntry<Street,Stop>> getRoute();

}
