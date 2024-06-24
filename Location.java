//This class represents the location (x, y) of a pel.
//Author - Marc Crasto
public class Location {
	//private variable to hold the x value of location.
	private int x;
	//private variable to hold the y value of location.
	private int y;
	//A constructor that initializes this Location object with the specified coordinates.
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//public method that returns the x coordinate of this Location.
	public int getx() {
		return this.x;
	}
	
	//public method that returns the y coordinate of this Location.
	public int gety() {
		return this.y;
	}
	
	//public method that compares the locations of two objects and returns a number based on a specific condition.
	public int compareTo(Location p) {
		if(this.gety() > p.gety()) {
			return 1;
		}
		else if(this.gety() == p.gety() && this.getx() > p.getx()) {
			return 1;
		}
		else if (this.gety() == p.gety() && this.getx() == p.getx()) {
			return 0;
		}
		else if(this.gety() < p.gety()) {
			return -1;
		}
		else if(this.gety() == p.gety() && this.getx() < p.getx()) {
			return -1;
		}
		else {
			return 2;
		}
	}
}
