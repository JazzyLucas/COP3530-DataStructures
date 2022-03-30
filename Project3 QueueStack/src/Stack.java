/**
 * This Stack class will implement a stack of country objects using a double ended singly linked list. 
 * Supports the following methods:
 * a. Constructor that creates the stack.
 * b. A push method to push a country on the stack. (This must be an O(1) method.)
 * c. A pop method to pop a country off the stack and return it. (This must be an O(1) method.)
 * d. A printStack method to print the stack from top of the stack to bottom.
 * e. An isEmpty method that returns true if the stack is empty, false otherwise.
 * f. An isFull method that always returns false.
 * 
 * @author Lucas Rendell 
 * @version 10/25/2020
 */


public class Stack {

	private Link top;
	
	private class Link {
        Country data;
        Link link;
	}//end Link class
	
	public Stack() {
		this.top = null;
	}//end Stack constructor
	
	/**
	 * Push a country onto the top of the stack.
	 * 
	 * @param Country
	 * @return void
	 */
	public void push(Country country) {
		Link newLink = new Link();
		newLink.data = country;
		newLink.link = top;
		top = newLink;
	}//end push
	
	/**
	 * Pop a country off the top of the stack.
	 * 
	 * @param void
	 * @return Country
	 */
	public Country pop() {
		if (isEmpty()) {
			return null;
		}
		Country countryToReturn = top.data;
		top = top.link;
		return countryToReturn;
	}//end pop
	
	/**
	 * Print the entire stack.
	 * 
	 * @param void
	 * @return void
	 */
	public void printStack() {
		if (isEmpty()) {
			System.out.printf("Stack was empty... printStack() aborted.\n");
			return;
		}
		Link current = top;
		System.out.printf("\nStack Contents:");
		System.out.printf("\nName                        Capitol         Population     GDP             Cases     Deaths");
		System.out.printf("\n-------------------------------------------------------------------------------------------\n");
		while (current != null) {
			System.out.printf("%-28.27s", current.data.getName());
			System.out.printf("%-16.15s", current.data.getCapitol());
			System.out.printf("%-15.15s", current.data.getPopulation());
			System.out.printf("%-16.16s", current.data.getGDP());
			System.out.printf("%-10.10s", current.data.getCases());
			System.out.printf("%-6.6s\n", current.data.getDeaths());
			current = current.link;
		}
	}//end printStack

	/**
	 * Return if the stack is empty or not.
	 * 
	 * @param void
	 * @return boolean
	 */
	public boolean isEmpty() {
		return (top == null);
	}//end isEmpty
	
	/**
	 * Always return false.
	 * 
	 * @param void
	 * @return boolean
	 */
	public boolean isFull() {
		return false;
	}//end isFull
	
}
