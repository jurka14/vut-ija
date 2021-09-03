

package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

/**
 *Class containing a street on the map
 *@author Vojtech Jurka (xjurka08)
 *@author Samuel Krempasky (xkremp01)
 */

public class Street implements Drawable {
	 private Coordinate start;
	 private Coordinate stop;
	 List <Coordinate> StreetLocation=new ArrayList<>();
	 private String name;
	 private List<Shape> gui;

	List <Stop> stops= new ArrayList<>();

	public Street(String name){
		this.name=name;
	}
	 
	public Street(String name, Coordinate start, Coordinate stop) {
		this.start = start;
		this.stop = stop;
		this.name = name;
		this.gui = Arrays.asList(
				new Text((start.getX() + stop.getX())/2, (start.getY() + stop.getY())/2, name),
				new Line(start.getX(), start.getY(), stop.getX(), stop.getY())
				);
	}

	public String getId() {return name;}

	public  Coordinate getStart(){ return start;}

	public Coordinate getStop(){return stop;}

	public boolean equals (Object obj){
		Street s= (Street) obj;

		return s.getId().equals(this.getId());
	}

	public void addStop(Stop stop) {
		stop.setStreet(this);
		this.stops.add(stop);
		return;
	}

	public boolean liesOnStreet(Stop stop){
		if (	this.getStart().lenghtOfTwoCoordinates(stop.getCoordinate()) +
				this.getStop().lenghtOfTwoCoordinates(stop.getCoordinate()) ==this.getStart().lenghtOfTwoCoordinates(this.getStop())){
			return true;
		}

		return false;
	}

	public void setStop(Stop stop){ stops.add(stop);}

	@Override
	public List<Shape> getGUI() {
		return this.gui;
	}
	
	
	
	
	 
	 
}
