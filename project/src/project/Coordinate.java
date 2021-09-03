
package project;

/**
 *Map coordinate class
 *@author Vojtech Jurka (xjurka08)
 *@author Samuel Krempasky (xkremp01)
 */

public class Coordinate {
	
	private double x;
	private double y;
	
	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) { 
		if (obj == this) {
			return true;
		} 
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Coordinate c = (Coordinate) obj;
		
		return this.x == c.getX() && this.y == c.getY();
	}
	public int diffX(Coordinate c){
		return (int) Math.abs(this.getX()-c.getX());
	}
	public int diffY(Coordinate c){
		return (int) Math.abs(this.getY()-c.getY());
	}

	public int lenghtOfTwoCoordinates(Coordinate c){

		return (int) Math.round( Math.sqrt(diffY(c)*diffY(c) + diffX(c) * diffX(c)));
	}

}
