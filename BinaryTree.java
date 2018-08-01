package cs241.proj1;
/**
 * This class represents a Binary Search Tree.
 * 
 * @author Eduardo S.
 *
 */
public class BinaryTree {
	private boolean empty;
	private Node root;
	public int numNodes;
	
	/**
	 * Constructor for Binary tree, given no array.
	 * a new tree is created with an empty array of size 1.
	 */
	public BinaryTree() throws DuplicateValueException {
		this(new int[0]);
	}

	/**
	 * Constructor for Binary tree, given an array.
	 * a new tree is created using the array, number are added in the order of the array.
	 * recursively calls the add() method to create the tree.
	 * @throws DuplicateValueException 
	 */
	public BinaryTree(int[] numbers) throws DuplicateValueException {
		root = null;
		empty = true;
		numNodes = 0;
		for(int i = 0; i < numbers.length; i++) {
			add(numbers[i]);
		}
	}
	
	/**
	 * adds a number to the tree
	 * 
	 * @param i the number to be added
	 * @throws DuplicateValueException 
	 */
	public void add(int i) throws DuplicateValueException {
		add(i, root);
	}
	
	/**
	 * adds a number to the subtree with the root at a defined Node.
	 * the UI should not allow users to input duplicate values.
	 * 
	 * @param i 	the number to be added.
	 * @param ptr 	the root of the subtree
	 * @throws DuplicateValueException 
	 */
	private void add(int i, Node ptr) throws DuplicateValueException {
		//if the tree is empty, fill it and set empty false.
		if(empty) {
			root = new Node(i);
			empty = false;
			numNodes++;
			return;
		}
		//should the value go left or right?
		//is it an empty spot? if so, fill it.
		//otherwise, send it along.
		if(i == ptr.data) {
			throw new DuplicateValueException("Cannot add duplicate value...");
		}
		if(i < ptr.data) {
			if(ptr.leftChild == null) {
				ptr.leftChild = new Node(i);
				return;
			}
			add(i, ptr.leftChild);
		}
		if(i > ptr.data) {
			if(ptr.rightChild == null) {
				ptr.rightChild = new Node(i);
				return;
			}
			add(i, ptr.rightChild);
		}
		numNodes++;
	}
	
	/**
	 * searches for and removes a value from the tree.
	 * 
	 * @param i the value to be removed
	 * @throws ValueNotFoundException if {@link i} is not found
	 */
	public void remove(int i) throws ValueNotFoundException {
		if(empty) System.out.println("This tree is empty!");
		try {
			root = removeRec(i, root);
		} catch (ValueNotFoundException e) {
			throw new ValueNotFoundException("cannot remove value that does not exist");
		}
		numNodes--;
		if(numNodes == 0) empty = true;
	}
	
	private Node removeRec(int i, Node ptr) throws ValueNotFoundException {
		//if null, return null
		if (ptr == null) return null;
		//search for problem node
		
		//if larger, run on right
		//if smaller, run on left
		//if problem case...
		if			(i > ptr.data) {
			if(ptr.rightChild == null) throw new ValueNotFoundException("Remove value missing...");
			ptr.rightChild = removeRec(i, ptr.rightChild);
		} else if	(i < ptr.data) {
			if(ptr.leftChild == null) throw new ValueNotFoundException("Remove value missing...");
			ptr.leftChild = removeRec(i, ptr.leftChild);
		} else if 	(i == ptr.data) {
			//if two children, assign node to minimum of right child, then remove that child.
			if			(ptr.leftChild != null && ptr.rightChild != null) {
			Node successor = minVal(ptr.rightChild);
			ptr = removeRec(successor.data, ptr);
			ptr.data = successor.data;
			}
			//if only left child exists, assign left child's data to ptr then delete that child.
			else if 	(ptr.leftChild != null) {
				Node successor = ptr.leftChild;
				ptr = removeRec(successor.data, ptr);
				ptr.data = successor.data;
			}
			//if only right child exists, assign right child's data to ptr then delete that child.
			else if 	(ptr.rightChild != null) {
				Node successor = ptr.rightChild;
				ptr = removeRec(successor.data, ptr);
				ptr.data = successor.data;
			}
			//otherwise, this node has no children, and will become null, deleting it's existence.
			else {
				ptr = null;
			}
		}
		return ptr;
	}
	
	
	/**
	 * returns minimal value of a tree rooted at ptr.
	 * (aka the leftmost node)
	 * 
	 * @param ptr root of tree to evaluate
	 * @return minimum value of tree.
	 */
	private Node minVal(Node ptr) {
		//if left child exists, go there
		if(ptr.leftChild != null) return minVal(ptr.leftChild);
		//otherwise, return yourself
		return ptr;
	}

	/**
	 * traverses a binary tree using the pre-order method,
	 * then creates an array of the numbers traversed.
	 * 
	 * @param ptr The binary tree to be processed.
	 * @return an array of the tree's contents, in pre-order.
	 */
	public void preOrder() {
		System.out.print("Pre Order: ");
		preOrder(root);
		System.out.println();
	}
	
	private void preOrder(Node ptr) {
		if(ptr == null || ptr.data < 0) return;
		System.out.print(ptr.data + " ");
		preOrder(ptr.leftChild);
		preOrder(ptr.rightChild);
	}
	/**
	 * traverses a binary tree using the post-order method, then
	 * creates an array of the numbers traversed.
	 * 
	 * @param ptr The binary tree to be processed.
	 * @return an array of the tree's contents, in post-order.
	 */
	public void postOrder() {
		System.out.print("Post Order: ");
		postOrder(root);
		System.out.println();
	}
	
	private void postOrder(Node ptr) {
		if(ptr == null) return;
		postOrder(ptr.leftChild);
		postOrder(ptr.rightChild);
		System.out.print(ptr.data + " ");
	}
	/**
	 * traverses a binary tree using the in-order method, then
	 * creates an array of the numbers traversed.
	 * 
	 * @param ptr The binary tree to be processed.
	 * @return an array of the tree's contents, in in-order.
	 */
	public void inOrder() {
		System.out.print("In Order: ");
		inOrder(root);
		System.out.println();
	}

	private void inOrder(Node ptr) {
		if(ptr == null) return;
		inOrder(ptr.leftChild);
		System.out.print(ptr.data + " ");
		inOrder(ptr.rightChild);
	}
	
}