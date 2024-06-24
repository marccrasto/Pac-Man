//This class implements an ordered dictionary using a binary search tree. Each node of the tree will store a Pel object; the attribute Location of the Pel object stored in a node will be its key attribute.
//Author - Marc Crasto
public class BinarySearchTree implements BinarySearchTreeADT {
	//private variable to store the root node of the tree.
	BNode root;
	//A constructor method to initialize the root node.
	public BinarySearchTree() {
		this.root = new BNode();
	}
	
	//public method that returns the Pel object storing the given key, if the key is stored in the tree; returns null otherwise.
	public Pel get(BNode r, Location key) {
		if(r.isLeaf()) {
			return r.getData();
		}
		else {
			if(key.compareTo(r.getData().getLocus()) == 0) {
				return r.getData();
			}
			else {
				if(key.compareTo(r.getData().getLocus()) == 1) {
					return get(r.rightChild(),key);
				}
				else {
					return get(r.leftChild(),key);
				}
			}
		}
	}
	
	//private method that returns the node storing the given key, if the key is stored in the tree; returns null otherwise.
	private BNode getNode(BNode r, Location key) {
		if(r.isLeaf()) {
			return r;
		}
		else {
			if(key.compareTo(r.getData().getLocus()) == 0) {
				return r;
			}
			else {
				if(key.compareTo(r.getData().getLocus()) == -1) {
					return getNode(r.leftChild(),key);
				}
				else {
					return getNode(r.rightChild(),key);
				}
			}
		}
	}
	
	//public method that inserts newData in the tree if no data item with the same key is already there; if a node already stores the same key, the algorithm throws a DuplicatedKeyException.
	public void put(BNode r, Pel newData) throws DuplicatedKeyException{
		BNode currNode = getNode(r, newData.getLocus());
		if(!currNode.isLeaf()) {
			throw new DuplicatedKeyException("Key Already Exists!");
		}
		else {
			BNode newLeftChild = new BNode();
			BNode newRightChild = new BNode();
			currNode.setContent(newData);
			currNode.setLeftChild(newLeftChild);
			currNode.setRightChild(newRightChild);
			newLeftChild.setParent(currNode);
			newRightChild.setParent(currNode);
		}
	}
	
	//public method that removes the data item with the given key, if the key is stored in the tree; throws an InexistentKeyException otherwise.
	public void remove(BNode r, Location key) throws InexistentKeyException {
		BNode currNode = getNode(r,key);
		if(currNode.isLeaf()) {
			throw new InexistentKeyException("Key does not exist!");
		}
		else {
			BNode leftChild = currNode.leftChild();
			BNode rightChild = currNode.rightChild();
			BNode parent = currNode.parent();
			if(leftChild.isLeaf()) {
				if(parent != null) {
					rightChild.setParent(parent);
					if(parent.leftChild() == currNode) {
						parent.setLeftChild(rightChild);
					}
					else if(parent.rightChild() == currNode) {
						parent.setRightChild(rightChild);
					}
				}
				else {
					this.root = rightChild;
					rightChild.setParent(null);
				}
			}
			else if(rightChild.isLeaf()) {
				if(parent != null) {
					leftChild.setParent(parent);
					if(parent.leftChild() == currNode) {
						parent.setLeftChild(leftChild);
					}
					else if(parent.rightChild() == currNode) {
						parent.setRightChild(leftChild);
					}
				}
				else {
					this.root = leftChild;
					leftChild.setParent(null);
				}
			}
			else {
				try {
					BNode smallestNode = smallestN(currNode.rightChild());
					currNode.setContent(smallestNode.getData());
					remove(smallestNode, smallestNode.getData().getLocus());
				}
				catch (EmptyTreeException e) {
					System.out.println("Empty Tree!");
				}
			}
		}
	}
	
	//public method that returns the Pel object in the tree with the smallest key. Throws an EmptyTreeException if the tree does not contain any data.
	public Pel smallest(BNode r) throws EmptyTreeException {
		Pel result = null;
		if(r.isLeaf()) {
			throw new EmptyTreeException("Empty Tree!");
		}
		else {
			BNode currNode = r;
			while(!currNode.isLeaf()) {
				currNode = currNode.leftChild();
			}
			result = currNode.parent().getData();
		}
		return result;
	}
	
	//private method that returns the node in the tree with the smallest key. Throws an EmptyTreeException if the tree does not contain any data.
	private BNode smallestN(BNode r) throws EmptyTreeException {
		if(r.isLeaf()) {
			throw new EmptyTreeException("Empty Tree!");
		}
		else {
			BNode currNode = r;
			while(!currNode.isLeaf()) {
				currNode = currNode.leftChild();
			}
			return currNode.parent();
		}
	}
	
	//public method that returns the Pel object in the tree with the largest key. Throws an EmptyTreeException if the tree does not contain any data.
	public Pel largest(BNode r) throws EmptyTreeException {
		if(r.isLeaf()) {
			throw new EmptyTreeException("Empty Tree!");
		}
		else {
			BNode currNode = r;
			while(!currNode.isLeaf()) {
				currNode = currNode.rightChild();
			}
			return currNode.parent().getData();
		}
	}
	
	//public method that returns returns the Pel object with the smallest key larger than the given one. Returns null if the given key has no successor.
	public Pel successor(BNode r, Location key) {
		if(r.isLeaf()) {
			return null;
		}
		else {
			BNode currNode = getNode(r,key);
			if(!currNode.isLeaf() && !currNode.rightChild().isLeaf()) {
				try {
					return smallest(currNode.rightChild());
				} catch (EmptyTreeException e) {
					System.out.println("Empty Tree!");
					return null;
				}
			}
			else {
				currNode = currNode.parent();
				while((currNode != null) && (currNode.getData().getLocus().compareTo(key) == -1)) {
					currNode = currNode.parent();
				}
				if(currNode == null) {
					return null;
				}
				else {
					return currNode.getData();
				}
			}
		}
	}
	
	//public method that returns the Pel object with the largest key smaller than the given one. Returns null if the given key has no predecessor.
	public Pel predecessor(BNode r, Location key) {
		if(r.isLeaf()) {
			return null;
		}
		else {
			BNode currNode = getNode(r,key);
			if(!currNode.isLeaf() && !currNode.leftChild().isLeaf()) {
				try {
					return largest(currNode.leftChild());
				} catch (EmptyTreeException e) {
					System.out.println("Empty Tree!");
					return null;
				}
			}
			else {
				currNode = currNode.parent();
				while((currNode != null) && (currNode.getData().getLocus().compareTo(key) == 1)) {
					currNode = currNode.parent();
				}
				if(currNode == null) {
					return null;
				}
				else {
					return currNode.getData();
				}
			}
		}
	}
	
	//public method that returns the root of this tree.
	public BNode getRoot() {
		return this.root;
	}
}

