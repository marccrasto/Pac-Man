//This class represents the nodes of the binary search tree. Each node will store an object of the class Pel and it will have references to its left child, its right child, and its parent.
//Author - Marc Crasto
public class BNode {
	//private variable to store the pel object in the node.
	private Pel value;
	//private variable to store the pointer to the left child of the node.
	private BNode left;
	//private variable to store the pointer to the right child of the node.
	private BNode right;
	//private variable to store the pointer to the parent of the node.
	private BNode parent;
	
	//A constructor for the class. Stores the Pel value in the node and sets left child, right child, and parent to the specified values.
	public BNode(Pel value, BNode left, BNode right, BNode parent) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	//A constructor for the class that initializes a leaf node. The data, children and parent attributes are set to null.
	public BNode() {
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	//public method that returns the parent of this node.
	public BNode parent() {
		return this.parent;
	}
	
	//public method that sets the parent of this node to the specified value.
	public void setParent(BNode newParent) {
		this.parent = newParent;
	}
	
	//public method that sets the left child of this node to the specified value.
	public void setLeftChild(BNode p) {
		this.left = p;
	}
	
	//public method that sets the right child of this node to the specified value
	public void setRightChild(BNode p) {
		this.right = p;
	}
	
	//public method that stores the given Pel object in this node.
	public void setContent(Pel value) {
		this.value = value;
	}
	
	//public method that returns true if this node is a leaf; returns false otherwise.
	public boolean isLeaf() {
		boolean result = false;
		if(this.value == null && this.left == null && this.right == null) {
			result = true;
		}
		else {
			//do nothing
		}
		return result;
	}
	
	//public method that returns the Pel object stored in this node.
	public Pel getData() {
		return this.value;
	}
	
	//public method that returns the left child of this node.
	public BNode leftChild() {
		return this.left;
	}
	
	//public method that returns the right child of this node.
	public BNode rightChild() {
		return this.right;
	}
}
