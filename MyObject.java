//This class represents is used to define the objects in the game, their movements, their positions,etc.
//Author - Marc Crasto
public class MyObject implements MyObjectADT {
	//private variable to store the id of the object.
	private int id;
	//private variable to store the height of the object.
	private int height;
	//private variable to store the width of the object.
	private int width;
	//private variable to store the type of the object.
	private String type;
	//private variable to store the location of the object on the board.
	private Location pos;
	//private variable to store the tree that holds the Pel objects and their positions for a particular MyObject object.
	private BinarySearchTree tree;
	
	//A constructor to initialize all the private variables above using the given parameters.
	public MyObject(int id, int height, int width, String type, Location pos) {
		this.id = id;
		this.height = height;
		this.width = width;
		this.type = type;
		this.pos = pos;
		this.tree = new BinarySearchTree();
	}
	
	//public method that sets the type of the MyObject object with the value given in the parameter.
	public void setType(String type) {
		this.type = type;
	}
	
	//public method that returns the width of the MyObject object.
	public int getWidth() {
		return this.width;
	}
	
	//public method that returns the height of the MyObject object.
	public int getHeight() {
		return this.height;
	}
	
	//public method that returns the type of the MyObject object.
	public String getType() {
		return this.type;
	}
	
	//public method that returns the id of the MyObject object.
	public int getId() {
		return this.id;
	}
	
	//public method that returns the location of the MyObject object.
	public Location getLocus() {
		return this.pos;
	}
	
	//public method that sets the location of the MyObject object with the value given in the parameter.
	public void setLocus(Location value) {
		this.pos = value;
	}
	
	//public method that adds a Pel object to MyObject object's tree.
	public void addPel(Pel pix) throws DuplicatedKeyException {
		tree.put(tree.getRoot(),pix);
	}
	
	//public method that returns returns true if this MyObject object intersects the one specified in the parameter. It returns false otherwise.
	public boolean intersects(MyObject otherObject) {
		Pel currPel = null;
		try {
			currPel = tree.smallest(tree.getRoot());
		}
		catch (EmptyTreeException e) {
			System.out.println("Empty Tree!");
		}
		while(currPel != null) {
			Location pelLocation = new Location(currPel.getLocus().getx()+this.getLocus().getx()-otherObject.getLocus().getx(),currPel.getLocus().gety()+this.getLocus().gety()-otherObject.getLocus().gety());
			if(otherObject.findPel(pelLocation)) {
				return true;
			}
			else {
				currPel = tree.successor(tree.getRoot(),currPel.getLocus());
			}
		}
		return false;
		
	}
	
	//private method that returns true if the Pel object given in the parameter is found in MyObject object's tree.
	private boolean findPel(Location p) {
		boolean result = false;
		Pel pelObject = this.tree.get(this.tree.getRoot(), p);
		if(pelObject != null) {
			result = true;
		}
		return result;
	}
}
