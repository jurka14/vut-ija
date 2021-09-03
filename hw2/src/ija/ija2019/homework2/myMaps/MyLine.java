package ija.ija2019.homework2.myMaps;


import java.util.ArrayList;

import ija.ija2019.homework2.maps.Line;
import ija.ija2019.homework2.maps.Stop;
import ija.ija2019.homework2.maps.Street;


public class MyLine implements Line {
	
	protected String id;
	protected java.util.List<Street> streetList = new ArrayList<Street>();
	protected java.util.List<Stop> stopList = new ArrayList<Stop>();
	protected java.util.List<java.util.AbstractMap.SimpleImmutableEntry<Street,Stop>> route = new ArrayList<java.util.AbstractMap.SimpleImmutableEntry<Street,Stop>>();
	
	public MyLine(java.lang.String id) {
		this.id = id;
	}
	
	public boolean addStreet(Street street) {
		
		if(this.streetList.isEmpty() == true) {
			return false;
		}
		
		if(this.streetList.get(this.streetList.size()-1).follows(street) == false) {
			return false;
		}
		
		this.streetList.add(street);
		
		java.util.AbstractMap.SimpleImmutableEntry<Street,Stop> e = new java.util.AbstractMap.SimpleImmutableEntry<Street, Stop>(street, null);
		this.route.add(e);
		return true;
	}
	
	public boolean addStop(Stop stop) {
		
		if(this.stopList.isEmpty() == true) {
			this.stopList.add(stop);
			this.streetList.add(stop.getStreet());
			
			java.util.AbstractMap.SimpleImmutableEntry<Street,Stop> e = new java.util.AbstractMap.SimpleImmutableEntry<Street, Stop>(stop.getStreet(), stop);
			this.route.add(e);
			return true;
		}
		
		if(this.streetList.get(this.streetList.size()-1).follows(stop.getStreet()) == true) {
			this.stopList.add(stop);
			this.streetList.add(stop.getStreet());
			
			java.util.AbstractMap.SimpleImmutableEntry<Street,Stop> e = new java.util.AbstractMap.SimpleImmutableEntry<Street, Stop>(stop.getStreet(), stop);
			this.route.add(e);
			return true;
		}
		
		return false;
		
	}
	
	public java.util.List<java.util.AbstractMap.SimpleImmutableEntry<Street,Stop>> getRoute(){
		return new ArrayList<java.util.AbstractMap.SimpleImmutableEntry<Street,Stop>>(this.route);
	}
	
	
}
