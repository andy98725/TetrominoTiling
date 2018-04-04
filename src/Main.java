import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Make input scanner
		Scanner reader = new Scanner(System.in); 
		// Prompt
		System.out.println("Enter a number 1-11:");
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
		// Close the reader
		reader.close();
		// Create the application
		new Application(n);
	}
}
