package gradebook;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String studentA;
		String studentB;
		String studentC;
		String studentD;
		String studentE;
		
		int gradeA = 0;
		int gradeB = 0;
		int gradeC = 0;
		int gradeD = 0;
		int gradeE = 0;
		int totalGrade = 0;
		
		try(Scanner sc = new Scanner(System.in)) {	 
		
			System.out.println("Enter the name and surname of your first student and his grade: ");
			
			studentA = sc.next();
			gradeA = sc.nextInt();

			System.out.println("Enter the name and surname of your second student and his grade: ");
			
			studentB = sc.next();
			gradeB = sc.nextInt();
			
			System.out.println("Enter the name and surname of your third student and his grade: ");
			
			studentC = sc.next();
			gradeC = sc.nextInt();
			
			System.out.println("Enter the name and surname of your fourth student and his grade: ");
			
			studentD = sc.next();
			gradeD = sc.nextInt();
			
			System.out.println("Enter the name and surname of your fifth student and his grade: ");
			
			studentE = sc.next();
			gradeE = sc.nextInt();
			
			totalGrade = gradeA + gradeB + gradeC + gradeD + gradeE;
			
	
			System.out.println("Student " + studentA + " has grade " + gradeA);
			System.out.println("Student " + studentB + " has grade " + gradeB);
			System.out.println("Student " + studentC + " has grade " + gradeC);
			System.out.println("Student " + studentD + " has grade " + gradeD);
			System.out.println("Student " + studentE + " has grade " + gradeE);
			
			System.out.println("Total grade of all students is " + totalGrade);
				
		}

	}

}
