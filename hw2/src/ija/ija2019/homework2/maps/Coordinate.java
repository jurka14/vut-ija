package ija.ija2019.homework2.maps;



public class Coordinate {
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
		Coordinate mc = new Coordinate();
		mc.x = x;
		mc.y = y;
		
		Coordinate c = mc;
		return c;
	}
	
	public int diffX(Coordinate c) {
		return this.x-c.x;
	}
	
	public int diffY(Coordinate c) {
		return this.y-c.y;
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
