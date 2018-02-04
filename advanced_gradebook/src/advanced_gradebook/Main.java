package advanced_gradebook;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String[] students;
		int[] grades; 
		int totalNumberofStudents = 0;
		int totalGrade = 0;
		
		try(Scanner sc = new Scanner(System.in)) {	 
			
			System.out.println("Enter the number of students in your class: ");
			totalNumberofStudents = sc.nextInt();
			
			students = new String[totalNumberofStudents];
			grades = new int[totalNumberofStudents];
			
			for(int i = 0 ; i < totalNumberofStudents ; i++){ 
				
				int j = i+1;
				System.out.println("Enter the name and surname of your " + j + ". student and his grade: ");
				students[i] = sc.next();
				grades[i]= sc.nextInt();
			}
			
			for(int i = 0 ; i < totalNumberofStudents ; i++){ 
				
				System.out.println("Student " + students[i] + " has grade " + grades[i]);
				totalGrade += grades[i];
			}
			
			System.out.println("Total grade of all students is " + totalGrade);
			
				
		}

	}

}
