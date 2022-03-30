/**
 * This Queue class implements a double-ended queue of country objects
 * using a double-ended doubly linked list. Supports the following methods:
 * a. Constructor that creates the queue.
 * b. An insertEnd method to insert a country at the end of the queue.
 * c. An insertFront method to insert a country at the front of the queue.
 * d. A removeEnd method to remove a country from the end of the queue and return it.
 * e. A removeFront method to remove a country from the front of the queue and return
 * it.
 * f. An intervalDelete method to delete from the queue country objects with COVID-19
 * CFR that belong to the input interval. So this method takes two input parameters
 * representing this interval. Return true if anything was found and deleted, and false
 * otherwise.
 * g. A printQueue method to print the queue in order front to end
 * h. An isEmpty method that returns true if the queue is empty, false otherwise
 * i. An isFull method that always returns false
 * 
 * @author Lucas Rendell 
 * @version 10/25/2020
 */

public class Queue {
	
	// Global fields
	private Link front;
	private Link end;
	
	private class Link {
        public Country data;
        public Link next;
        public Link previous;
	}//end Link class
	
	public Queue() {
		this.front = null;
		this.end = null;
	}//end Queue constructor
	
	/**
	 * Insert a country at the front of the queue.
	 * 
	 * @param Country
	 * @return void
	 */
	public void insertFront(Country country) {
		Link newLink = new Link();
		newLink.data = country;
		if (isEmpty()) {
			end = newLink;
		} else {
			front.previous = newLink;
		}
		newLink.next = front;
		front = newLink;
	}//end insertFront
	
	/**
	 * Insert a country at the end of the queue.
	 * 
	 * @param Country
	 * @return void
	 */
	public void insertEnd(Country country) {
		Link newLink = new Link();
		newLink.data = country;
		if (isEmpty()) {
			front = newLink;
		} else {
			end.next = newLink;
		}
		newLink.previous = end;
		end = newLink;
	}//end insertEnd
	
	/**
	 * Remove and return the country at the front of the queue.
	 * 
	 * @param void
	 * @return Country
	 */
	public Country removeFront() {
		if (isEmpty()) {
			return null;
		}
		Country countryToReturn = front.data;
		if (front.next != null)
		front.next.previous = null;
		front = front.next;
		return countryToReturn;
	}//end removeFront
	
	/**
	 * Remove and return the country at the end of the queue.
	 * 
	 * @param void
	 * @return Country
	 */
	public Country removeEnd() {
		if (isEmpty()) {
			return null;
		}
		Country countryToReturn = end.data;
		if (end.previous != null)
		end.previous.next = null;
		end = end.previous;
		return countryToReturn;
	}//end removeEnd
	
	/**
	 * Delete a country at the given CFR interval from the queue and return true if it did so.
	 * 
	 * @param float, float
	 * @return boolean
	 */
	public boolean intervalDelete(double minPercent, double maxPercent) {
		boolean foundSomething = false;
		if (isEmpty()) {
			return foundSomething;
		}
		Link current = front;
		while (current != null) {
			if (current.data.getCFR() < maxPercent && current.data.getCFR() >= minPercent) {
				if (current == end) {
					removeEnd();
				} else if (current == front) {
					removeFront();
				} else {
					current.previous.next = current.next;
					current.next.previous = current.previous;
				}
				if (!foundSomething)
					foundSomething = true;
			}
			current = current.next;
		}//end while current != null
		return foundSomething;
	}//end intervalDelete
	
	/**
	 * Print the entire Queue.
	 * 
	 * @param void
	 * @return void
	 */
	public void printQueue() {
		if (isEmpty()) {
			System.out.printf("Queue was empty... printQueue() aborted.\n");
			return;
		}
		Link current = front;
		System.out.printf("\nQueue Contents:");
		System.out.printf("\nName                        Capitol         Population     GDP             Cases     Deaths");
		System.out.printf("\n-------------------------------------------------------------------------------------------\n");
		while (current != null) {
			System.out.printf("%-28.27s", current.data.getName());
			System.out.printf("%-16.15s", current.data.getCapitol());
			System.out.printf("%-15.15s", current.data.getPopulation());
			System.out.printf("%-16.16s", current.data.getGDP());
			System.out.printf("%-10.10s", current.data.getCases());
			System.out.printf("%-6.6s\n", current.data.getDeaths());
			current = current.next;
		}
	}//end printQueue

	/**
	 * Return true if the queue is empty.
	 * 
	 * @param void
	 * @return boolean
	 */
	public boolean isEmpty() {
		return (front == null || end == null);
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
