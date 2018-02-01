package caluclator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int a = 0;
		int b = 0;
		int c = 0;
		
		try(Scanner sc = new Scanner(System.in)) { 
		
			System.out.println("Enter your first number: ");
			
			a = sc.nextInt();
			
			System.out.println("Enter your second number: ");
			
			b = sc.nextInt();
			
			System.out.println("Write the operation you want (* / + -): ");
			
			String op = sc.next();
			
			if (op.equals("/") && b == 0) {
				
				System.out.println("You cannot divide with 0.");
				System.out.println("Write again enter your second number: ");
	
				b = sc.nextInt();
		}

		switch (op) {
        case "+":  c = a + b;
                 break;
        case "-":   c = a - b;
        		break;
        case "*":   c = a * b;
				break;
        case "/":   c = a / b;
			    break;
		} 
		
		System.out.println("First number is: " + a);
		System.out.println("Second number is: " + b);
		System.out.println("The result is: " + c);
		}		
	}

}
