package cs241.proj1;
/**
 * This class represents a Node for a Binary Search Tree.
 * Each node has a value, and references to two other nodes, known as its 'children'
 * 
 * @author Eduardo
 *
 */
public class Node {
	//represents the integer value that this node contains
	public int data;
	//reference to the node's left child
	public Node leftChild;
	//reference to the node's right child
	public Node rightChild;
	
	/**
	 * Constructor for the Node class.
	 * Simply assigns parameters to their respective variables.
	 * 
	 * @param data the desired value for the node
	 * @param leftChild the desired left child for the node
	 * @param rightChild the desired right child for the node
	 */
	Node(int data) {
		this(data, null, null);
	}
	
	Node(int data, Node leftChild, Node rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
}
