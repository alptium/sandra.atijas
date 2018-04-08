package numberguessing;

import java.util.Scanner;

public class Main {

	public static int scopeNumbers = 100;
	
	public static void main(String[] args) {
		
		int [] numbersGuessing; 
		String name;
		int low  = 0;
		int high = 99;
		int mid;
		int key;
		int counter = 0;
		char toQuit;
		
		try (Scanner sc = new Scanner(System.in)) {
			
			numbersGuessing = new int[scopeNumbers];
			System.out.println ("=================NUMBER GUESSING GAME=======================");
			System.out.println();
			
			/*Inserting scope of numbers for guessing*/
			for (int i = 0; i < numbersGuessing.length; i++) {
				numbersGuessing[i] = i + 1;
			}
			
			System.out.println ("What is your name?");
			name = sc.next();
			
			System.out.println ("Welcome " + name + "!");
			System.out.println ("We will now start the number guessng game. Please think of a number between 1 and 100 and "
					+ "I will try to guess your number.");
			System.out.println ("Your answers can be: Y - YES; H - HIGHER; L - LOWER");
			System.out.println();
			
			while (true) {
				
				System.out.println ("Did you think of a number (Y/N)");
				
				if (sc.next().equalsIgnoreCase("Y")) {
					
					while (low <= high) {
						
						counter++;
						mid = (low + high)/2;
						System.out.println ("Is it your number " + numbersGuessing[mid] + "(Y/H/L)");
						String answer = sc.next();	
						
						if (answer.equalsIgnoreCase("Y")) {
							key = numbersGuessing[mid];
							System.out.println ("Thanks " + name + ", this means that I've guessed that your number is " + key);
							System.out.println ("I was able to guess your number with just " + counter + " questions!");
							break;
						}
							
						if (answer.equalsIgnoreCase("L")) {
							high = mid - 1;
						}
						else {
							low = mid + 1;
						}
					}	
					
				}
				
				System.out.println("Would you like to play again? Y/N ");
				toQuit = sc.next().charAt(0);
				
				if (toQuit ==  'N' || toQuit == 'n') {
					System.exit(0);
				}
			}
		}
	
	}

}
