import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 2 - Stacks and Priority Queues
 * <p>
 * This Project2 class defines five groups of countries based on
 * COVID-19 CFR:
 * EXCELLENT countries as those with CFR <1%
 * VGOOD no less than 1% but <2%
 * GOOD no less than 2% but <5%
 * FAIR no less than 5% but <10%
 * POOR no less than 10%.
 * <p>
 * This class will create priority queues for each group, read
 * the csv file, insert Country Objects in their corresponding
 * priority queues, and print the results.
 * Next, the class will create one stack, remove items one at a time
 * from respective priority queues, push them onto a stack, print the stack,
 * then prompt the user to close the program.
 * 
 * @author Lucas Rendell 
 * @version 10/3/2020
 */


public class Project2 {
	
	public static void main(String[] args) {
		
		boolean askForFile = true;
		Scanner userInputScan = new Scanner(System.in);
		Scanner rowScan = null;
		Scanner dataScan = null;
		String userInput = "";
		int excellentLength = 0, vgoodLength = 0, goodLength = 0, fairLength = 0, poorLength = 0;
		
		// Prompt the user.
		System.out.printf("Stacks and Priority Queues\nEnter the file name: ");
		
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
			// Determine the lengths needed for the priority queues
			if(countries[i].getCFR() < 0.01)
				excellentLength++;
			else if(countries[i].getCFR() < 0.02)
				vgoodLength++;
			else if(countries[i].getCFR() < 0.05)
				goodLength++;
			else if(countries[i].getCFR() < 0.1)
				fairLength++;
			else
				poorLength++;
		}//end for
		
		System.out.println("\nThere were " + rows + " records read.");
		
		// Create the 5 Priority queues with their respective lengths
		Priority excellent = new Priority(excellentLength);
		Priority vgood = new Priority(vgoodLength);
		Priority good = new Priority(goodLength);
		Priority fair = new Priority(fairLength);
		Priority poor = new Priority(poorLength);
		
		// Fill the Priority Queues
		for (int i = 0; i < countries.length; i++) {
			if(countries[i].getCFR() < 0.01)
				excellent.insert(countries[i]);
			else if(countries[i].getCFR() < 0.02)
				vgood.insert(countries[i]);
			else if(countries[i].getCFR() < 0.05)
				good.insert(countries[i]);
			else if(countries[i].getCFR() < 0.1)
				fair.insert(countries[i]);
			else
				poor.insert(countries[i]);
		}// end for
		
		// Print the Priority Queues
		excellent.printPQueue();
		vgood.printPQueue();
		good.printPQueue();
		fair.printPQueue();
		poor.printPQueue();
		
		// Remove and push each Priority Queue item onto a stack
		Stack stack = new Stack(rows);
		while(!poor.isEmpty())
			stack.push(poor.remove());
		while(!fair.isEmpty())
			stack.push(fair.remove());
		while(!good.isEmpty())
			stack.push(good.remove());
		while(!vgood.isEmpty())
			stack.push(vgood.remove());
		while(!excellent.isEmpty())
			stack.push(excellent.remove());
		
		// Print the entire stack
		stack.printStack();
		
		// Exit the program
		userInputScan.close();
		System.out.println("\nHave a good day.");
		
	}
}