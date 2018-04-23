package cs241.proj1;

import java.util.Scanner;

public class UI {
	int page;
	Scanner sc;
	BinaryTree tree;
	
	UI() {
		page = 0;
		sc = new Scanner(System.in);
	}

	public void menuEngine() {
		System.out.println("CS241 Project 1: Binary Search Tree \n");
		while(true) {
			switch(page){
			case 0:
				initialize();
				break;
			case 1:
				printAll();
				break;
			case 2:
				System.out.println("Main Menu:");
				System.out.println("A: add a value");
				System.out.println("R: remove a value");
				System.out.println("H: help");
				System.out.println("E: exit program");
				mainMenu();
				break;
			default:
				System.out.println("UI navigation error, restarting...");
				page = 0;
				break;
			}
		}
	}
	
	private void printAll() {
		//print all traversals
		tree.preOrder();
		tree.inOrder();
		tree.postOrder();
		//next UI page
		page = 2;
	}

	private void initialize() {
		//prompt and receive user input
		System.out.println("Enter initial values for the tree:");
		String choice = sc.nextLine();
		//if input is empty, create an empty tree.
		if(choice == "") {
			try {
				tree = new BinaryTree();
			} catch (DuplicateValueException e) {
				System.out.println("How did you get duplicate values with an empty tree?");
				System.out.println(e.getMessage());
				return;
			}
			return;
		}
		//splits input by spaces, placed into String array strings[]
		String strings[] = choice.split(" ");
		//create new int array numbers[] of same size as strings[]
		int[] numbers = new int[strings.length];
		//loop thru strings[], parsing each into a number, placed in numbers[]
		for(int i = 0; i < strings.length; i++) {
			try {
				int num  = Integer.parseInt(strings[i]);
				numbers[i] = num;
			} catch (NumberFormatException e) {
				System.out.println("Error reading input format, try again");
				System.out.println(e.getMessage());
				return;
			} //end try...catch
		} //end for
		
		//create BinaryTree using int array we made
		try {
			tree = new BinaryTree(numbers);
		} catch (DuplicateValueException e) {
			System.out.println("Duplicate values not allowed!");
			return;
		}
		//send to next page (printing traversals)
		page = 1;
	}
	
	private void mainMenu() {
		while(true) {
			System.out.println("Enter a command:");
			String choice = sc.nextLine();
			switch(choice.toUpperCase()) {
			case "A":
				add();
				tree.inOrder();
				continue;
			case "R":
				remove();
				tree.inOrder();
				continue;
			case "H":
				help();
				break;
			case "E":
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("command not recognized... try again");
				continue;
			}
			break;
		}
	}

	private void help() {
		System.out.println("CS214 Project 1: Binary Search Tree");
		System.out.println(	  "This program creates a Binary Tree from a list of values. \n"
							+ "This list cannot contain duplicates. \n"
							+ "Users can add and remove value from the BST once it is initialized. \n\n");
		System.out.println("Commnads:");
		System.out.println("A: add a value");
		System.out.println("R: remove a value");
		System.out.println("H: help");
		System.out.println("E: exit program");
	}

	private void add() {
		System.out.println("Enter a value to add:");
		int choice = sc.nextInt();
		sc.nextLine();
		
		try {
			tree.add(choice);
		} catch (DuplicateValueException e) {
			System.out.println(choice + " already exists! Duplicate values are not allowed.");
			return;
		}
		page = 2;
	}
	
	private void remove() {
		System.out.println("Enter a value to remove:");
		int choice = sc.nextInt();
		sc.nextLine();
		
		tree.remove(choice);
		page = 2;
	}
	
}
