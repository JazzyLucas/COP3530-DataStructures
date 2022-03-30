import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 4 - Binary Search Trees
 * <p>
 * This class reads a .csv file of countries and creates a binary search tree calling the insert method
 * Inorder it traverses the tree and prints all nodes using the printNode method as the are visited.
 * It deletes Greece, Mongolia, and Norway (in this order) from the tree by calling the delete method; and
 * preorder traverses the resulting tree printing all nodes as they are visited. It then searches for Mongolia,
 * Cyprus, United States, and Norway by calling the find method. For each search it prints the CFR of the found
 * countries and a message if they aren't found. It then deletes the countries Qatar, Somalia, Canada, Yemen,
 * and New Zealand from the tree, and postorder traverses the remaining trees printing all nodes as they are visited.
 * It calls the printBottomTwenty and printTopTwenty methods to print the bottom 20 and top 20 regarding CFR respectively.
 * 
 * @author Lucas Rendell 
 * @version 10/25/2020
 */


public class Project4 {
	
	public static void main(String[] args) {
		
		boolean askForFile = true;
		Scanner userInputScan = new Scanner(System.in);
		Scanner rowScan = null;
		Scanner dataScan = null;
		String userInput = "";
		
		// Prompt the user.
		System.out.printf("Binary Search Trees\nEnter the file name: ");
		
		// Take the user's input
		while (askForFile) {
			userInput = userInputScan.nextLine();
			try {
				rowScan = new Scanner(new File(userInput));
				dataScan = new Scanner(new File(userInput));
				askForFile = false;
			} catch (FileNotFoundException e) {
				System.out.printf("Unable to find that file.\nEnter the file name: ");
				//e.printStackTrace();
			}
		}//end while
		
		// Scan the CSV file just for row count (to specify the countries[] length)
		int rows = -1; // Starting lower than 0 to negate the first row.
		while (rowScan.findInLine(",") != null) {
			rowScan.nextLine();
			rows++;
		}//end while
		rowScan.close();
		
		// Scan the CSV and now use the data given the row count.
		Country[] countries = new Country[rows];
		dataScan.useDelimiter(",|\n");
		dataScan.nextLine();
		for (int i = 0; i < countries.length; i++) {
			countries[i] = new Country();
			countries[i].setName(dataScan.next());
			countries[i].setCapitol(dataScan.next());
			countries[i].setPopulation(dataScan.nextInt());
			countries[i].setGDP(dataScan.next());
			countries[i].setCases(dataScan.nextInt());
			countries[i].setDeaths(dataScan.nextInt());
		}//end for
		
		System.out.println("\nThere were " + rows + " records read.\n");
		BinarySearchTree tree = new BinarySearchTree();
		
		// Fill the BinarySearchTree
		for (int i = 0; i < countries.length; i++) {
			tree.insert(countries[i].getName(), countries[i].getCFR());
		}// end for
		
		tree.printInorder();
		
		tree.delete("Greece");
		tree.delete("Mongolia");
		tree.delete("Norway");
		
		tree.printPreorder();
		
		tree.find("Mongolia");
		tree.find("Cyprus");
		tree.find("United States");
		tree.find("Norway");
		
		tree.delete("Qatar");
		tree.delete("Somalia");
		tree.delete("Canada");
		tree.delete("Yemen");
		tree.delete("New Zealand");

		tree.printPostorder();
		
		tree.printBottomTwenty();
		tree.printTopTwenty();
		
		// Exit the program
		userInputScan.close();
		System.out.println("\nHave a good day.");
	}
}