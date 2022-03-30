/**
 * The HashTable class contains:
 * 1. A no-arg constructor that creates an empty hash table.
 * 2. An insert method to insert a given country, capitol, and CFR into the HashTable.
 * 3. A find method that returns a CFR (double).
 * 4. A delete method that will find and delete a given country (string) and capitol (string) from the HashTable.
 * 5. A display method that will display the entire HashTable.
 * 6. A printFreeAndCollisions method that will print the number of empty cells and collided cells.
 * 
 * @author Lucas Rendell
 * @version 11/14/2020
 */

public class HashTable {

	private int arraySize;
	private Node[] nodes;
	
	private class Node{
		String name;
		String capitol;
		double CFR;
		Node nextNode;
		
		public Node(String name, String capitol, double CFR) {
			this.name = name;
			this.capitol = capitol;
			this.CFR = CFR;
		}// end Node constructor
		
		public void printNode() {
			System.out.printf("%-40s%-20s%-30.6f", name, capitol,CFR);
		}// end printNode
	}// end Node class
	
	public HashTable() {
		arraySize = 307;
		nodes = new Node[arraySize];
	}// end HashTable constructor
	
	/**
	 * Insert a country to the HashTable.
	 * 
	 * @param String country
	 * @param String capitol
	 * @return void
	 */
	public void insert(String country, String capitol, double CFR) {
		// Hash function
		int key = hashFunction(country, capitol);
		// Insertion
		if (nodes[key] != null) {
			Node node = nodes[key];
			while (node.nextNode != null) {
				node = node.nextNode;
			}
			node.nextNode = new Node(country, capitol, CFR);
		} else {
			nodes[key] = new Node(country, capitol, CFR);
		}// end if
	}// end insert()
	
	/**
	 * Find a country in the HashTable.
	 * 
	 * @param String country
	 * @param String capitol
	 * @return double
	 */
	public double find(String country, String capitol) {
		int key = hashFunction(country, capitol);
		if (nodes[key] == null) {
			return -1;
		}
		Node node = nodes[key];
		while (node.nextNode != null) {
			if (node.name.compareTo(country) == 0) {
				return node.CFR;
			}
			node = node.nextNode;
		}// end while
		if (node.name.compareTo(country) == 0) {
			return node.CFR;
		} else {
			return -1;
		}// end if
	}// end find()
	
	/**
	 * Delete a country from the HashTable.
	 * 
	 * @param String country
	 * @param String capitol
	 * @return void
	 */
	public void delete(String country, String capitol) {
		int key = hashFunction(country, capitol);
		if (nodes[key] == null) {
			return;
		}
		Node node = nodes[key];
		while (node.nextNode != null) {
			Node previousNode = node;
			if (node.name.compareTo(country) == 0) {
				nodes[key] = node.nextNode;
				System.out.println(country + " has been deleted from the hash table.");
				return;
			}
			node = node.nextNode;
			if (node.name.compareTo(country) == 0) {
				previousNode.nextNode = node.nextNode;
				System.out.println(country + " has been deleted from the hash table.");
				return;
			}
			node = node.nextNode;
		}
		if (node.name.compareTo(country) == 0) {
			nodes[key] = null;
			System.out.println(country + " has been deleted from the hash table.");
			return;
		} else {
			System.out.println(country + " is not found for deletion.");
		}
	}// end delete()
	
	/**
	 * Display the HashTable.
	 * 
	 * @return void
	 */
	public void display() {
		for (int i = 0; i < arraySize; i++) {
			System.out.printf("\n%4d. ", i);
			if (nodes[i] != null) {
				nodes[i].printNode();
				Node node = nodes[i];
				while (node.nextNode != null) {
					node = node.nextNode;
					System.out.printf("\n" + "      ");
					node.printNode();
				}
			} else {
				System.out.printf("Empty");
			}
		}
		System.out.println();
	}// end display()
	
	/**
	 * Print the number of empty cells in the HashTable and the number of collided cells.
	 * 
	 * @return void
	 */
	public void printFreeAndCollisions() {
		int emptyCells = 0;
		int collisions = 0;
		for (int i = 0; i < arraySize; i++) {
			boolean isEmpty = false;
			if (nodes[i] == null) {
				isEmpty = true;
				emptyCells++;
			}// end if
			if (!isEmpty) {
				Node node = nodes[i];
				while (node.nextNode != null) {
					collisions++;
					node = node.nextNode;
				}// end while
			}// end if
		}// end for
		System.out.println("\nHash table has " + emptyCells + " empty spaces and " + collisions + " collisions.");
	}// end printFreeAndCollisions()

	private int hashFunction(String country, String capitol) {
		String tempString = country + capitol;
		char[] tempCharArray = tempString.toCharArray();
		int result = 0;
		for (char c : tempCharArray) {
			result += c;
		}
		int key = result % arraySize;
		return key;
	}// end hashFunction()
}
