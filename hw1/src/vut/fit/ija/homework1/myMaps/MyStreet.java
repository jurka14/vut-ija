package vut.fit.ija.homework1.myMaps;

import java.util.ArrayList;
import java.util.List;

import vut.fit.ija.homework1.maps.Coordinate;
import vut.fit.ija.homework1.maps.Stop;
import vut.fit.ija.homework1.maps.Street;

public class MyStreet implements Street {
	
	protected String id;
	protected Coordinate c0;
	protected Coordinate c1;
	protected List<Stop> stopList = new ArrayList<Stop>();
	
	public MyStreet(String id, Coordinate c0, Coordinate c1) {
		this.id = id;
		this.c0 = c0;
		this.c1 = c1;
	}
	
	public String getId() {
		return this.id;
	}
	
	public List<Coordinate> getCoordinates() {
		List<Coordinate> list = new ArrayList<Coordinate>();
		list.add(this.c0);
		list.add(this.c1);
		return list;
	}
	
	public List<Stop> getStops() {
		return this.stopList;
	}
	
	public void addStop(Stop stop) {
		this.stopList.add(stop);
		stop.setStreet(this);
	}
}
