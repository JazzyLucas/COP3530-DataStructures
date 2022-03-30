/**
 * COP 3530: Project 2 - Stacks and Priority Queues
 * <p>
 * This Stack class will implement a stack of Country objects using an array.
 * Support the following methods.
 * a. Constructor that creates the stack array based on a size provided.
 * b. A push method to push a country on the stack.
 * c. A pop method to pop a country off the stack and return it.
 * d. A printStack method to print the stack from top down to bottom of it.
 * e. An isEmpty method that returns true if the stack is empty, false otherwise
 * f. An isFull method that returns true if the stack is full, false otherwise
 * 
 * @author Lucas Rendell 
 * @version 10/3/2020
 */

public class Stack {
	
	private int size;
	private Country[] countryStack;
	private int top;
	
	public Stack(int length) {
		size = length;
		countryStack = new Country[size];
		top = -1;
	}//end Stack constructor
	
	public void push(Country country) {
		countryStack[++top] = country;
	}//end push
	
	public Country pop() {
		return countryStack[top--];
	}//end pop
	
	
	
	public void printStack() {
		if (isEmpty()) {
			System.out.printf("Stack was empty... printStack() aborted.\n");
			return;
		}
		System.out.printf("\nStack Contents:");
		System.out.printf("\nName                        Capitol         Population     GDP             Cases     Deaths");
		System.out.printf("\n-------------------------------------------------------------------------------------------\n");
		for (int i = 0; i < size; i++) {
			System.out.printf("%-28.27s", countryStack[i].getName());
			System.out.printf("%-16.15s", countryStack[i].getCapitol());
			System.out.printf("%-15.15s", countryStack[i].getPopulation());
			System.out.printf("%-16.16s", countryStack[i].getGDP());
			System.out.printf("%-10.10s", countryStack[i].getCases());
			System.out.printf("%-6.6s\n", countryStack[i].getDeaths());
		}
	}//end printStack
	
	public Country peek() {
		return countryStack[top];
	}//end peek
	
	public boolean isEmpty() {
		return (top == -1);
	}//end isEmpty
	
	public boolean isFull() {
		return (top == size-1);
	}//end isFull
	
}
