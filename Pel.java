//This class represents the data items(pixels) to be stored in the nodes of the binary search tree.
//Author - Marc Crasto
public class Pel {
	//private variable to store the location of the Pel object
	private Location p;
	//private variable to store a number that represents the color of the Pel object
	private int color;
	
	//A constructor which initializes the new Pel with the specified coordinates and color.
	public Pel(Location p, int color) {
		this.p = p;
		this.color = color;
	}
	
	//public method that returns the Location of this Pel object.
	public Location getLocus() {
		return this.p;
	}
	
	//public method that returns the color of this Pel object.
	public int getColor() {
		return this.color;
	}
}
