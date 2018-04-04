import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Make input scanner
		Scanner reader = new Scanner(System.in); 
		// Prompt
		System.out.println("Enter a 2^n size from 1-11:");
		// Number to store to
		int n = -1;
		// Must get positive number
		while(n <= 0 || n > 11) {
			try {
				// Get input
				n = reader.nextInt();
				// Handle negative/zero
				if(n <= 0) {
					System.out.println("Please enter a positive value:");
				}
				// Handle too large
				if(n > 11) {
					System.out.println("Please enter a value below 12:");
				}
			}
			catch(Exception e) {
				// Handle non-number
				System.out.println("Please enter a number:");
			}
			// Clear buffer
			reader.nextLine();
		}
		// Find max coordinate
		int maxSize = (int) Math.pow(2,n);
		String maxSizeString =Integer.toString(maxSize);
		// Prompt for x
		System.out.println("Enter x coord for the missing tile from 0-"+Integer.toString(maxSize-1)+":");
		// Number to store to
		int x = -1;
		// Must get positive number
		while(x < 0 || x >= maxSize) {
			try {
				// Get input
				x = reader.nextInt();
				// Handle negative/zero
				if(x < 0) {
					System.out.println("Please enter a positive value:");
				}
				// Handle too large
				if(x >= maxSize) {
					System.out.println("Please enter a value below "+maxSizeString+":");
				}
			}
			catch(Exception e) {
				// Handle non-number
				System.out.println("Please enter a valid number:");
			}
			// Clear buffer
			reader.nextLine();
		}
		// Prompt for x
		System.out.println("Enter y coord for the missing tile from 0-"+Integer.toString(maxSize-1)+":");
		// Number to store to
		int y = -1;
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
					System.out.println("Please enter a value below "+maxSizeString+":");
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
