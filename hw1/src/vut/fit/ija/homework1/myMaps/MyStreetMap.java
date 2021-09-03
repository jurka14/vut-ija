package vut.fit.ija.homework1.myMaps;

import java.util.ArrayList;
import java.util.List;

import vut.fit.ija.homework1.maps.Street;
import vut.fit.ija.homework1.maps.StreetMap;

public class MyStreetMap implements StreetMap {
	
	protected List<Street> streetList = new ArrayList<Street>();
	
	public void addStreet(Street s) {
		this.streetList.add(s);
	}
	
	public Street getStreet(String id) {
		for (Street street : streetList)
		{
			if(street.getId().contentEquals(id) == true)
			{
				return street;
			}
		}
		return null;
	}
	
	
	

}
