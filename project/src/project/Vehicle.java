

package project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *Class containing the vehicle - contains all information needed for vehicle control 
 *@author Vojtech Jurka (xjurka08)
 *@author Samuel Krempasky (xkremp01)
 */

public class Vehicle implements Drawable, TimeUpdate {
	public String idVehicle;
	
	public int waiting = 0;
	public int index = 0;

	private double distance = 0;
	private double speed;
	private Coordinate position;
	private List<Shape> gui;
	public Path path;
	
	public boolean toggled = false;

	ArrayList <Coordinate> CoordinateList=new ArrayList<>();
	ArrayList <Stop> StopList=new ArrayList<>();
	ArrayList <Calendar> TimeList=new ArrayList<>();

	public void addStopList(ArrayList <Stop> StopList){ this.StopList=StopList;}
	public void addCoordinateList(ArrayList <Stop> StopList){ this.StopList=StopList;}
	

	public Vehicle(Coordinate position, double speed, Path path, ArrayList <Stop> StopList, ArrayList <Calendar> TimeList) {

		this.position = position;
		this.speed = speed;
		this.path = path;
		this.gui = new ArrayList<>();
		this.gui.add(new Circle(position.getX(), position.getY(), 8, Color.BLUE));

		this.StopList=StopList;
		this.TimeList=TimeList;

	}
	
	private void moveGui(Coordinate coordinate) {
		for (Shape shape : this.gui) {
			shape.setTranslateX(coordinate.getX() - this.position.getX() + shape.getTranslateX());
			shape.setTranslateY(coordinate.getY() - this.position.getY() + shape.getTranslateY());
		}
	}
	
	@Override
	public List<Shape> getGUI() {
		return this.gui;
	}
	
	public Path getPath() {
		return this.path;
	}


	@Override
	public void update(Calendar time) {
		
		if(this.index < this.StopList.size()-1) { //if this is NOT the last stop
			if(Math.abs(this.position.getX() - this.StopList.get(this.index).getCoordinate().getX()) < 2 && //if the vehicle has arived on stop
					Math.abs(this.position.getY() - this.StopList.get(this.index).getCoordinate().getY()) < 2) {
			
			
			
			
				if(time.before(this.TimeList.get(this.index))) { //if there is still time to departure
					this.waiting++;//count the second the vehicle waited
					return; //do not move
				}
				else { //if the vehicle should depart
					this.index++;//next index in timelist and stoplist, continue moving
				}
			}
		}
		
		
		
		this.distance = this.speed * (time.getTime().getTime()/1000 -  this.TimeList.get(0).getTime().getTime()/1000 - this.waiting);//seconds passed - seconds waiting* speed
		if(this.distance <= 0) {
			return;
		}
		
		if(this.distance > this.path.getPathSize()) {
			this.distance = this.path.getPathSize();
		}
		
		Coordinate coordinate = this.path.getCoordinateByDistance(distance);
		this.moveGui(coordinate);
		this.position = coordinate;
		
	}
	
}
