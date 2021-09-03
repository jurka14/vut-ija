package vut.fit.ija.homework1.myMaps;

import vut.fit.ija.homework1.maps.Coordinate;
import vut.fit.ija.homework1.maps.Stop;
import vut.fit.ija.homework1.maps.Street;

public class MyStop implements Stop {
	
	protected String id;
	protected Coordinate c;
	protected Street street;
	
	public MyStop(String s, Coordinate c) {
		this.id = s;
		this.c = c;
	}
	
	public MyStop(String s) {
		this.id = s;
	}
	
	public String getId() {
		return this.id;
	}
	
	public Coordinate getCoordinate() {
		return this.c;
	}
	
	public Street getStreet() {
		return this.street;
	}
	
	public void setStreet(Street s) {
		this.street = s;
	}
	
	@Override
	public boolean equals(Object obj) { 
		if (obj == this) {
			return true;
		} 
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Stop stop = (Stop) obj;
		
		return this.id == stop.getId();
	}
	

}
