package ija.ija2019.homework2.myMaps;

import java.util.ArrayList;


import ija.ija2019.homework2.maps.Coordinate;
import ija.ija2019.homework2.maps.Street;
import ija.ija2019.homework2.maps.Stop;

public class MyStreet implements Street {
	
	protected String id;
	protected Coordinate begin;
	protected Coordinate end;
	protected java.util.List<Stop> stopList = new ArrayList<Stop>();
	protected java.util.List<Coordinate> coordList = new ArrayList<Coordinate>();

	public MyStreet(java.lang.String id, Coordinate... coordinates) {
		
		for(int i = 0; i < coordinates.length-1; i++) {
			if( (coordinates[i].diffX(coordinates[i+1]) != 0) && (coordinates[i].diffY(coordinates[i+1]) != 0) ) {
				this.id = null;
				return;
			}
		}
		
		this.id = id;
		this.begin = coordinates[0];
		this.end = coordinates[coordinates.length-1];
		
		for(Coordinate i:coordinates) {
			coordList.add(i);
		}
	}
	
	public java.lang.String getId(){
		return this.id;
	}
	
	public java.util.List<Coordinate> getCoordinates(){
		return this.coordList;
	}
	
	public Coordinate begin() {
		return this.begin;
	}
	
	public Coordinate end() {
		return this.end;
	}
	
	public java.util.List<Stop> getStops(){
		return this.stopList;
	}
	
	public boolean follows(Street s) {
		return (this.begin.equals(s.begin())) || (this.begin.equals(s.end())) || (this.end.equals(s.begin())) || (this.end.equals(s.end()));
	}
	
	public boolean addStop(Stop stop) {
		for(int i = 0; i < this.coordList.size()-1; i++) {
			
			if(this.coordList.get(i).diffX(this.coordList.get(i+1)) == 0) {
				
				if(this.coordList.get(i).getY() < this.coordList.get(i+1).getY()) {
					if(this.coordList.get(i).getY() >= stop.getCoordinate().getY() || stop.getCoordinate().getY() >= this.coordList.get(i+1).getY()) {
						continue;
					}
				}
				else {
					if(this.coordList.get(i).getY() <= stop.getCoordinate().getY() || stop.getCoordinate().getY() <= this.coordList.get(i+1).getY()) {
						continue;
					}
				}
				
				if(stop.getCoordinate().getX() == this.coordList.get(i).getX()) {
					this.stopList.add(stop);
					stop.setStreet(this);
					return true;
				}
			}
			else {	
				
				if(this.coordList.get(i).getX() < this.coordList.get(i+1).getX()) {
					if(this.coordList.get(i).getX() >= stop.getCoordinate().getX() || stop.getCoordinate().getX() >= this.coordList.get(i+1).getX()) {
						continue;
					}
				}
				else {
					if(this.coordList.get(i).getX() <= stop.getCoordinate().getX() || stop.getCoordinate().getX() <= this.coordList.get(i+1).getX()) {
						continue;
					}
				}
				
				if(stop.getCoordinate().getY() == this.coordList.get(i).getY()) {
					this.stopList.add(stop);
					stop.setStreet(this);
					return true;
				}
			}
			
		}
		return false;
	}
}
