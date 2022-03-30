/**
 * COP 3530: Project 2 - Stacks and Priority Queues
 * <p>
 * This Priority class implements a priority queue of Country objects using a sorted array,
 * based on COVID-19 CFR, case fatality rate, the lowertheCFR, the higher the priority.
 * It supports the following methods:
 * a. Constructor that creates the stack array based on a size provided.
 * b. An insert method to insert a country into the queue.(This should be an O(N) method.)
 * c. A remove method to remove a country from the front of the queue and return it.(This should be an O(1) method.)
 * d. A printQueue method to print the queue in priority order.
 * e. An isEmptymethod that returns true if the priority queue is empty, false otherwise
 * f. An isFullmethod that returns true if the priority queue is full, false otherwise
 * 
 * @author Lucas Rendell 
 * @version 10/3/2020
 */

public class Priority {
	private int size; 
	private Country[] countryPQueue;
	private int quantity;
	
	public Priority(int length) {
		size = length;
		countryPQueue = new Country[size];
		quantity = 0;
	}//end Priority constructor
	
	public void insert(Country country) {
		int j;
		
		if(quantity == 0)
			countryPQueue[quantity++] = country;
		else {
			for(j = quantity-1; j >= 0; j--) {
				if (country.getCFR() < countryPQueue[j].getCFR())
					countryPQueue[j+1] = countryPQueue[j];
				else
					break;
			}//end for
			countryPQueue[j+1] = country;
			quantity++;
		}//end else (quantity > 0)
	}//end insert
	
	public Country remove() {
		return countryPQueue[--quantity];
	}//end remove
	
	public void printPQueue() {
		if (isEmpty()) {
			System.out.printf("Priority Queue was empty... printPQueue() aborted.\n");
			return;
		}
		System.out.printf("\nPriority Queue Contents:");
		System.out.printf("\nName                        Capitol         Population     GDP             Cases     Deaths");
		System.out.printf("\n-------------------------------------------------------------------------------------------\n");
		for (int i = 0; i < size; i++) {
			System.out.printf("%-28.27s", countryPQueue[i].getName());
			System.out.printf("%-16.15s", countryPQueue[i].getCapitol());
			System.out.printf("%-15.15s", countryPQueue[i].getPopulation());
			System.out.printf("%-16.16s", countryPQueue[i].getGDP());
			System.out.printf("%-10.10s", countryPQueue[i].getCases());
			System.out.printf("%-6.6s\n", countryPQueue[i].getDeaths());
		}
	}//end printPQueue
	
	public Country peek() {
		return countryPQueue[quantity-1];
	}//end peek
	
	public boolean isEmpty() {
		return (quantity == 0);
	}//end isEmpty
	
	public boolean isFull() {
		return (quantity == size);
	}//end isFull
}