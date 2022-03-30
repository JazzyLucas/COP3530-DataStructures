import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 3 - Linked Lists
 * <p>
 * This Project3 class defines five groups of countries based on
 * COVID-19 CFR:
 * EXCELLENT countries as those with CFR <1%
 * VGOOD no less than 1% but <2%
 * GOOD no less than 2% but <5%
 * FAIR no less than 5% but <10%
 * POOR no less than 10%.
 * <p>
 * This class reads a .csv file of countries and creates a single stack of country objects containing
 * countries from FAIR, GOOD, and VGOOD groups (discarding any countries not in
 * those groups). Prints the stack starting at the top of the stack. Creates a queue. Pops
 * the items from the stack and inserts them onto the queue. It prints the queue from front to end.
 * It then deletes by calling an intervalDelete method on countries with COVID-19 CFR between 2.5% and 3.5%.
 * After printing the queue again, it removes the items from the queue, one at a time, and pushes them on the stack.
 * It then prints the stack and exits the program.
 * 
 * @author Lucas Rendell 
 * @version 10/25/2020
 */


public class Project3 {
	
	public static void main(String[] args) {
		
		boolean askForFile = true;
		Scanner userInputScan = new Scanner(System.in);
		Scanner rowScan = null;
		Scanner dataScan = null;
		String userInput = "";
		
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
		}//end for
		
		System.out.println("\nThere were " + rows + " records read.");
		Stack stack = new Stack();
		
		// Fill the stack initially with VGOOD, GOOD, and FAIR CFRs
		for (int i = 0; i < countries.length; i++) {
			if(countries[i].getCFR() < 0.1 && countries[i].getCFR() >= 0.05) // FAIR
				stack.push(countries[i]);
			else if(countries[i].getCFR() < 0.02 && countries[i].getCFR() >= 0.01) // VGOOD
				stack.push(countries[i]);
			else if(countries[i].getCFR() < 0.05 && countries[i].getCFR() >= 0.02) // GOOD
				stack.push(countries[i]);
		}// end for
		
		stack.printStack();
		
		// Remove and push each item from the stack onto a queue, alternating between front and end
		Queue queue = new Queue();
		boolean toggle = true;
		while(!stack.isEmpty()) {
			if (toggle) {
				queue.insertFront(stack.pop());
			} else {
				queue.insertEnd(stack.pop());
			}
			toggle = !toggle;
		}
		
		queue.printQueue();
		
		// Delete countries with CFR between 2.5% and 3.5%
		queue.intervalDelete(0.025, 0.035);
		
		queue.printQueue();
		
		// Refill the stack by removing items from the queue, alternating between front and end
		toggle = true;
		while(!queue.isEmpty()) {
			if (toggle) {
				stack.push(queue.removeFront());
			} else {
				stack.push(queue.removeEnd());
			}
			toggle = !toggle;
		}
		
		stack.printStack();
		
		// Exit the program
		userInputScan.close();
		System.out.println("\nHave a good day.");
		
	}
}