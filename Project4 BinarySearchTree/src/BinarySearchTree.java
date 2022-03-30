/**
 * The BinarySearchTree class contains:
 * 1. A no=parameter constructor for creating an empty tree.
 * 2. An insertion method.
 * 3. A find method that returns a CFR (double).
 * 4. A delete method that will find and delete a given country (string) from the tree.
 * 5. A printInorder method that will traverse the tree and print in order.
 * 6. A printPreorder method that will traverse the tree and print in preorder.
 * 7. A printPostorder method that will traverse the tree and print in postorder.
 * 8. A printBottomTwenty method that will print in ascending order the bottom 20 countries.
 * 9. A printTopTwenty method that will print in descending order the bottom 20 countries.
 * 
 * @author Lucas Rendell
 * @version 11/14/2020
 */

public class BinarySearchTree {
	
	private Node root;
	private int max = 20;
	private Node[] nodes = new Node[20]; 
	
	private class Node {
		String name;
		double CFR;
		Node leftChild;
		Node rightChild;
		public Node(String name, double CFR) {
			this.name = name;
			this.CFR = CFR;
		}
		public void printNode() {
			System.out.printf("%-20.18s%,-15.6f\n",name,CFR);
		}
	}
	
	public BinarySearchTree() {
		root = null;
	}// end BinarySearchTree constructor
	
	/**
	 * Insert a country to the BinarySearchTree.
	 * 
	 * @param country
	 * @param double CFR
	 * @return void
	 */
	public void insert(String name, double CFR) {
		
		Node newNode = new Node(name, CFR);
		if (root == null)
			root = newNode;
		else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if(name.compareTo(current.name) < 0) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}// end else 
			}// end while left or right insertion
		}// end else not root
	}// end insert 
	
	/**
	 * Find a country in the BinarySearchTree and return its CFR.
	 * 
	 * @param String name
	 * @return double
	 */
	public double find(String name) {
		Node current = root;
		int visited = 0;
		while (current != null && name.compareTo(current.name) != 0)
		{
			visited++;
			if (name.compareTo(current.name) < 0)
				current = current.leftChild;
			else
				current = current.rightChild;
			if (current == null) {
				System.out.println("\n" + name + " is not found");
				System.out.println(visited + " nodes visited");
				return -1;
			}// end if
		}// end while
		System.out.printf("\n" + name + " has been found with CFR %-15.6f\n", current.CFR);
		System.out.println(visited + " nodes visited");
		return current.CFR;
	}// end find
	
	/**
	 * Delete a country in the BinarySearchTree.
	 * 
	 * @param String name
	 * @return void
	 */
	public void delete(String name) {
		Node current = root;
		Node parent = null;
		boolean isLeft = false;
		if (current == null) {
			System.out.println("There is no tree.");
			return;
		}
		while (current != null && name.compareTo(current.name) != 0)
		{
			parent = current;
			if (name.compareTo(current.name) < 0) {
				current = current.leftChild;
				isLeft = true;
			} else {
				current = current.rightChild;
				isLeft = false;
			} if (current == null) {
				System.out.println(name + " is not found");
				return;
			}
		}// end while
		if (isLeft) {
			parent.leftChild = null;
		} else {
			parent.rightChild = null;
		}
		if (current.leftChild == null && current.rightChild == null) {
			if(current == root)
				root = null;
		}
		if (current.rightChild != null && isLeft) {
			parent.leftChild = current.leftChild;
		} else if (current.rightChild != null && !isLeft) {
			parent.rightChild = current.rightChild;
		}
		System.out.println(name + " has been deleted from tree");
	}// end delete
	
	/**
	 * Print the BinarySearchTree in order.
	 * 
	 * @return void
	 */
	public void printInorder() {
		System.out.println("\nInorder Traversal:\n");
		System.out.println("Name                CFR");
		System.out.println("-----------------------------------------");
		inOrder(root);
		System.out.println("");
	}// end printInorder
	
	/**
	 * Print the BinarySearchTree in preorder.
	 * 
	 * @return void
	 */
	public void printPreorder() {
		System.out.println("\nPreorder Traversal:\n");
		System.out.println("Name                CFR");
		System.out.println("-----------------------------------------");
		preOrder(root);
		System.out.println("");
	}// end printPreorder
	
	/**
	 * Print the BinarySearchTree in postorder.
	 * 
	 * @return void
	 */
	public void printPostorder() {
		System.out.println("\nPostorder Traversal:\n");
		System.out.println("Name                CFR");
		System.out.println("-----------------------------------------");
		postOrder(root);
		System.out.println("");
	}// end printPostorder
	
	/**
	 * Print the bottom twenty nodes of the BinarySearchTree.
	 * 
	 * @return void
	 */
	public void printBottomTwenty() {
		System.out.println("\nBottom twenty countries regarding CFR\n");
		System.out.println("Name                CFR");
		System.out.println("-----------------------------------------");
		System.out.println("");
		max = 20;
		leastNode(root);
		for (int i = 0; i < max; i++) {
			nodes[i].printNode();
		}
	}// end printBottomTwenty
	
	/**
	 * Print the top twenty nodes of the BinarySearchTree.
	 * 
	 * @return void
	 */
	public void printTopTwenty() {
		System.out.println("\nTop twenty countries regarding CFR\n");
		System.out.println("Name                CFR");
		System.out.println("-----------------------------------------");
		System.out.println("");
		max = 20;
		greatestNode(root);
		for (int i = 0; i < max; i++) {
			nodes[i].printNode();
		}
	}// end printTopTwenty
	
	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			localRoot.printNode();
			inOrder(localRoot.rightChild);
		}// end if localRoot != null
	}// end inOrder
	
	private void preOrder(Node localRoot) {
		if (localRoot != null) {
			localRoot.printNode();
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}// end if localRoot != null
	}// end preOrder
	
	private void postOrder(Node localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			localRoot.printNode();
		}// end if localRoot != null
	}// end postOrder
	
	private void greatestNode(Node localRoot) {
		if (localRoot != null) {
			greatestNode(localRoot.leftChild);
			Node temp = localRoot;
			boolean swapping = false;
			for(int i = 0; i < max; i++) {
				if (nodes[i] == null) {
					nodes[i] = temp;
				} else if(temp.CFR > nodes[i].CFR && !swapping) {
					if (nodes[i] != null) {
						swapping = true;
						Node swapped = temp;
						temp = nodes[i];
						nodes[i] = swapped;
					}
				}
			}
			greatestNode(localRoot.rightChild);
		}// end if localRoot != null
	}// end greatestNode
	
	private void leastNode(Node localRoot) {
		if (localRoot != null) {
			leastNode(localRoot.leftChild);
			Node temp = localRoot;
			boolean swapping = false;
			for(int i = 0; i < max; i++) {
				if (nodes[i] == null) {
					nodes[i] = temp;
					i = 20;
				} else if(temp.CFR < nodes[i].CFR && !swapping) {
					if (nodes[i] != null) {
						swapping = true;
						Node swapped = temp;
						temp = nodes[i];
						nodes[i] = swapped;
					}
				}
			}
			leastNode(localRoot.rightChild);
		}// end if localRoot != null
	}// end leastNode
}
