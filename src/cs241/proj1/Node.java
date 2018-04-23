package cs241.proj1;

public class Node {
	public int data;
	public Node leftChild;
	public Node rightChild;
	
	Node(int data) {
		this(data, null, null);
	}
	
	Node(int data, Node leftChild, Node rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
}
