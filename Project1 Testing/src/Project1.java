import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 1 - Array Searches and Sorts
 * <p>
 * This Project1 class prompts the user to enter a CSV
 * file name and parses it to create an array of country
 * objects filled with their respective data. It offers
 * the user 6 options: Print a countries report, Sort by
 * Name, Sort by COVID-19 CFR, Sort by GDP per capita, 
 * Find and print a country for a given name, and Quit.
 * There will be a loop running to prompt when invalid
 * choices occur or after certain prompts.
 * 
 * @author Lucas Rendell 
 * @version 9/11/2020
 */
public class Project1 {
	
	public static void main(String[] args) {
		
		boolean askForFile = true;
		Scanner userInputScan = new Scanner(System.in);
		Scanner rowScan = null;
		Scanner dataScan = null;
		String userInput = "";
		
		// Prompt the user.
		System.out.printf("Array Searches and Sorts\nEnter the file name: ");
		
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
		}
		
		// Scan the CSV file just for row count (to specify the countries[] length)
		int rows = -1; // Starting lower than 0 to negate the first row.
		while (rowScan.findInLine(",") != null) {
			rowScan.nextLine();
			rows++;
		}
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
		}
		System.out.println("\nThere were " + rows + " records read.");
		
		String input = "";
		boolean nameSorted = false;
		String promptMessage = "\n1. Print a countries report\n2. Sort by Name\n3. Sort by COVID-19 CFR\n4. Sort by GDP per capita\n5. Find and print a given country\n6. Quit\nEnter your choice: ";
		System.out.printf(promptMessage);
		Scanner stringScan = new Scanner(System.in);
		while(true) {
			input = userInputScan.nextLine();
			switch(input) {
				case "1":
					printCountriesReport(countries);
					System.out.printf(promptMessage);
					break;
				case "2":
					countries = nameSort(countries);
					nameSorted = true;
					System.out.printf("\nCountries sorted by Name.\n");
					System.out.printf(promptMessage);
					break;
				case "3":
					countries = cfrSort(countries);
					nameSorted = false;
					System.out.printf("\nCountries sorted by COVID-19 CFR.\n");
					System.out.printf(promptMessage);
					break;
				case "4":
					countries = gdpSort(countries);
					nameSorted = false;
					System.out.printf("\nCountries sorted by GDP per capita.\n");
					System.out.printf(promptMessage);
					break;
				case "5":
					System.out.printf("\nEnter a country name: ");
					userInput = stringScan.nextLine();
					find(countries, userInput, nameSorted);
					System.out.printf(promptMessage);
					break;
				case "6":
					userInputScan.close();
					stringScan.close();
					System.out.printf("\nHave a good day!");
					return;
				default:
					System.out.printf("Invalid choice enter 1-6: ");
					break;
			}
		}		
	}//end main

	/**
	 * Print a report of what is currently
	 * stored in a country array.
	 * 
	 * @param Country[]
	 * @return void
	 */
	static void printCountriesReport(Country[] countries) {
		System.out.printf("\nName          Capitol      Population     GDP             Cases     Deaths");
		System.out.printf("\n--------------------------------------------------------------------------\n");
		for (int i = 0; i < countries.length; i++) {
			System.out.printf("%-14.13s", countries[i].getName());
			System.out.printf("%-13.12s", countries[i].getCapitol());
			System.out.printf("%-15.15s", countries[i].getPopulation());
			System.out.printf("%-16.16s", countries[i].getGDP());
			System.out.printf("%-10.10s", countries[i].getCases());
			System.out.printf("%-6.6s\n", countries[i].getDeaths());
		}
	}//end printCountriesReport
	
	/**
	 * Sort a country array by name using
	 * Insertion alphabetically.
	 * Only sorts them in ascending
	 * A to Z.
	 * 
	 * @param Country[]
	 * @return Country[]
	 */
	static Country[] nameSort(Country[] countries) {
		for (int j = 1; j < countries.length; j++) {
			Country c = countries[j];
			int i = j-1;
			while ((i > -1) && (countries[i].compare(c) > 1)) {
				countries[i+1] = countries[i];
				i--;
			}
			countries[i+1] = c;
		}
		return countries;
	}//end nameSort
	
	/**
	 * Sort a country array by COVID-19 CFR using
	 * Selection sort.
	 * Only sorts them in ascending
	 * smallest to largest.
	 * 
	 * @param Country[]
	 * @return Country[]
	 */
	static Country[] cfrSort(Country[] countries) {
		
		for (int i = 0; i < countries.length - 1; i++) {
			int c = i;
			for (int j = i+1; j < countries.length; j++) {
				if (countries[j].getCases() < countries[c].getCases()) {
					c = j;
				}
			}
			Country temp = countries[c];
			countries[c] = countries[i];
			countries[i] = temp;
		}		
		return countries;
	}//end cfrSort
	
	/**
	 * Sort a country array by GDP per capita
	 * using Bubble sort.
	 * Only sorts them in descending
	 * largest to smallest.
	 * 
	 * @param Country[]
	 * @return Country[]
	 */
	static Country[] gdpSort(Country[] countries) {
		for (int i = 0; i < countries.length; i++) {
			for (int j = 0; j < countries.length-i-1; j++) {
				if (Double.parseDouble(countries[j].getGDP()) < Double.parseDouble(countries[j+1].getGDP())) {
					Country temp = countries[j];
					countries[j] = countries[j+1];
					countries[j+1] = temp;
				}
			}
		}
		return countries;
	}//end gdpSort
	
	/**
	 * Find an print a country report from
	 * a country array given a name.
	 * Uses binary seach if the data
	 * is sorted by name and sequential
	 * searching if it isn't sorted.
	 * 
	 * @param Country[], String, boolean
	 * @return void
	 */
	static void find(Country[] countries, String name, boolean sorted) {
		if (sorted) {
			System.out.printf("Binary Search\n");
			int b = 0, t = countries.length - 1;
			while(b <= t) {
				int m = 1 + (t-1) / 2;
				if (countries[m].getName().compareTo(name) == 0) {
					System.out.printf("\n");
					countries[m].print();
					return;
				}
				if (countries[m].getName().compareTo(name) < 0) {
					b = m + 1;
				} else {
					t = m - 1;
				}
			}
			System.out.printf("\nError: country " + name + " not found\n");
		} else {
			System.out.printf("Sequential Search\n");
			for (int i = 0; i < countries.length; i++) {
				if (countries[i].getName().compareTo(name) == 0) {
					System.out.printf("\n");
					countries[i].print();
					return;
				}
			}
			System.out.printf("\nError: country " + name + " not found\n");
		}
	}//end find
}
