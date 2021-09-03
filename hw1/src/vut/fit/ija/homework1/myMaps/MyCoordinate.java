package vut.fit.ija.homework1.myMaps;
import vut.fit.ija.homework1.maps.Coordinate;

public class MyCoordinate implements Coordinate {
	
	protected int x;
	protected int y;
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	
	public static Coordinate create(int x, int y) {
		
		if(x < 0 || y < 0)
		{
			return null;
		}
		MyCoordinate mc = new MyCoordinate();
		mc.x = x;
		mc.y = y;
		
		Coordinate c = mc;
		return c;
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

}
