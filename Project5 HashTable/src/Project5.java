import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 5 - Hash Tables
 * <p>
 * This class will read a .csv file of countries and create a hash table by calling the insert() method,
 * and display the whole has table by calling the display method. It will delete countries Australia, Tunisia, and Norway from
 * the hash table by calling the delete() method. It will search for countries Sri Lanka, Cyprus, and Tunisia by calling the
 * find() method. For the found countries, it will print out its CFR. It will delete the countries Malawi, Germany, Ireland,
 * Yemen, and India from the hash table. It will display the hash table using the display() method, and then print the result
 * from printFreeAndCollisions() method.
 * 
 * @author Lucas Rendell 
 * @version 12/4/2020
 */

public class Project5 {
	
	public static void main(String[] args) {
		
		boolean askForFile = true;
		Scanner userInputScan = new Scanner(System.in);
		Scanner rowScan = null;
		Scanner dataScan = null;
		String userInput = "";
		
		// Prompt the user.
		System.out.printf("COP3530 Project 5 - Lucas Rendell\nHash Tables\nEnter the file name: ");
		
		// Take the user's input
		while (askForFile) {
			userInput = userInputScan.nextLine();
			try {
				rowScan = new Scanner(new File(userInput));
				dataScan = new Scanner(new File(userInput));
				askForFile = false;
			} catch (FileNotFoundException e) {
				System.out.printf("Unable to find that file.\nEnter the file name: ");
			}
		}//end while
		
		// Scan the CSV file just for row count (to specify the countries[] length)
		int rows = -1;
		while (rowScan.findInLine(",") != null) {
			rowScan.nextLine();
			rows++;
		}//end while
		rowScan.close();
		
		// Scan the CSV and now use the data given the row count.
		HashTable countries = new HashTable();
		dataScan.useDelimiter(",|\n");
		dataScan.nextLine();
		// Since there is no Country.java in this project, I assign temp values and pass them in to fill the HashTable array.
		for (int i = 0; i < rows; i++) {
			String tempName = dataScan.next();
			String tempCapitol = dataScan.next();
			int tempPopulation = dataScan.nextInt();
			String tempGDP = dataScan.next();
			int tempCases = dataScan.nextInt();
			int tempDeaths = dataScan.nextInt();
			double tempCFR = (double)tempDeaths/(double)tempCases;
			countries.insert(tempName, tempCapitol, tempCFR);
		}//end for
		
		System.out.println("\nThere were " + rows + " records read into the hash table.");
		
		System.out.println("\nHash table content:");
		
		countries.display();
		
		System.out.println();
		countries.delete("Australia","Canberra");
		countries.delete("Tunisia","Tunis");
		countries.delete("Norway","Oslo");
		System.out.println();
		
		double tempCFR = countries.find("Sri Lanka","Colombo");
		if (tempCFR != -1) {
			System.out.println("Sri Lanka was found with CFR of " + tempCFR);
		} else {
			System.out.println("Sri Lanka is not found.");
		}// end if
		tempCFR = countries.find("Cyprus","Nicosia");
		if (tempCFR != -1) {
			System.out.println("Cyprus was found with CFR of " + tempCFR);
		} else {
			System.out.println("Cyprus is not found.");
		}// end if
		tempCFR = countries.find("Tunisia","Tunis");
		if (tempCFR != -1) {
			System.out.println("Tunisia was found with CFR of " + tempCFR);
		} else {
			System.out.println("Tunisia is not found.");
		}// end if

		System.out.println();
		countries.delete("Malawi","Lilongwe");
		countries.delete("Germany","Berlin");
		countries.delete("Ireland","Dublin");
		countries.delete("Yemen","Sanaa");
		countries.delete("India","New Delhi");
		
		System.out.println("\nHash table content:");
		
		countries.display();
		
		countries.printFreeAndCollisions();
		
		// Exit the program
		userInputScan.close();
		System.out.println("\nHave a good day.");
	}// end main
}// end Project5 class