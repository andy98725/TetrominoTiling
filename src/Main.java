import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Make input scanner
		Scanner reader = new Scanner(System.in); 
		// Size of 2^n
		int n = -1;
		// Prompt for size input
		System.out.println("Enter a 2^n size from 1-10:");
		// Must get positive number. Limiting to 10 and below for performance purposes.
		while(n <= 0 || n > 10) {
			try {
				// Get input
				n = reader.nextInt();
				// Handle negative/zero
				if(n <= 0) {
					System.out.println("Please enter a positive value:");
				}
				// Handle too large
				if(n > 10) {
					System.out.println("Please enter a value below 11:");
				}
			}
			catch(Exception e) {
				// Handle non-number
				System.out.println("Please enter a number:");
			}
			// Clear buffer
			reader.nextLine();
		}
		// Find board size
		int maxSize = (int) Math.pow(2,n);
		// Coordinates to get
		int x = -1, y = -1;
		// Prompt for x coordinate
		System.out.println("Enter x coord for the missing tile from 0-"+Integer.toString(maxSize-1)+":");
		// Must get positive number
		while(x < 0 || x >= maxSize) {
			try {
				// Get input
				x = reader.nextInt();
				// Handle negative/zero
				if(x < 0) {
					System.out.println("Please enter a positive value:");
				}
				// Handle above max
				if(x >= maxSize) {
					System.out.println("Please enter a value below "+Integer.toString(maxSize)+":");
				}
			}
			catch(Exception e) {
				// Handle non-number
				System.out.println("Please enter a valid number:");
			}
			// Clear buffer
			reader.nextLine();
		}
		// Prompt for y
		System.out.println("Enter y coord for the missing tile from 0-"+Integer.toString(maxSize-1)+":");
		// Must get positive number
		while(y < 0 || y >= maxSize) {
			try {
				// Get input
				y = reader.nextInt();
				// Handle negative/zero
				if(y < 0) {
					System.out.println("Please enter a positive value:");
				}
				// Handle too large
				if(y >= maxSize) {
					System.out.println("Please enter a value below "+Integer.toString(maxSize)+":");
				}
			}
			catch(Exception e) {
				// Handle non-number
				System.out.println("Please enter a valid number:");
			}
			// Clear buffer
			reader.nextLine();
		}
		// Close the reader
		reader.close();
		// Create the application
		new Application(n,x,y);
	}
}
