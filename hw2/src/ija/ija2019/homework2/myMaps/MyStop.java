package ija.ija2019.homework2.myMaps;

import ija.ija2019.homework2.maps.Stop;
import ija.ija2019.homework2.maps.Street;
import ija.ija2019.homework2.maps.Coordinate;


public class MyStop implements Stop {
	
	protected String id;
	protected Coordinate c;
	protected Street street;
	
	public MyStop(String id, Coordinate c){
		this.id = id;
		this.c = c;
	}
	
	public java.lang.String getId() {
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
	public String toString() {
		return "stop("+this.id+")";
	}
}
