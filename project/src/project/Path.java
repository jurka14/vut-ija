

package project;

import java.util.List;

/**
 *Class containing the path of the vehicle
 *@author Vojtech Jurka (xjurka08)
 *@author Samuel Krempasky (xkremp01)
 */

public class Path {
	public List<Coordinate> path;

	public List<Coordinate> getCoords() {
		return path;
	}

	public Path(List<Coordinate> path) {
		this.path = path;
	}
	
	private double calculateDistanceByCoordinates(Coordinate a, Coordinate b) {
		return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
	}
	
	public Coordinate getCoordinateByDistance(double distance) {
		double length = 0;
		Coordinate a = null;
		Coordinate b = null;
		
		for (int i = 0; i < path.size()-1; i++) {
			a = path.get(i);
			b = path.get(i+1);
			
			if(length + calculateDistanceByCoordinates(a,b) >= distance) {
				break;
			}
			
			length += calculateDistanceByCoordinates(a,b);
		}
		
		if (a == null || b == null) {
			return null;
		}
		
		double localDistance = (distance-length) / calculateDistanceByCoordinates(a,b); //calculating ratio of the driven distance between two nodes
		
		return new Coordinate(a.getX() + (b.getX() - a.getX()) * localDistance, a.getY() + (b.getY() - a.getY()) * localDistance);
		
	}
	
	public double getPathSize() {
		double size = 0;
		for (int i = 0; i < path.size()-1; i++) {
			size += calculateDistanceByCoordinates(path.get(i),path.get(i+1));
		}
		return size;
	}
	
	

}
