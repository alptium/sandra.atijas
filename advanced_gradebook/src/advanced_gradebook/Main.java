package advanced_gradebook;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String[][] students;
		int[] grades; 
		int totalNumberofStudents = 0;
		int totalGrade = 0;
		double avgGrade = 0.0;
		
		try(Scanner sc = new Scanner(System.in)) {	 
			
			//Number of students needs to be grater than 0
			while (totalNumberofStudents <= 0) {
				try {
					
					System.out.println("Please enter the number of students in your class: ");
					totalNumberofStudents = sc.nextInt();
					
					if (totalNumberofStudents < 0) {
						System.out.println("The number of students has to be positive number!");
					}
				} 
				catch (Exception e) {
					System.out.println("The number of students cannot be a string!");
					sc.next(); // for not having endless loop
				}
				
			}
			
			students = new String[totalNumberofStudents][2]; //second dimension = 2 -> first and last name
			grades = new int[totalNumberofStudents];
			
			for(int i = 0 ; i < totalNumberofStudents ; i++){ 
				
				int j = i+1;
				System.out.println("Enter the name of your " + j + ". student: ");
				students[i][0] = sc.next();
				
				System.out.println("Enter the surname of your " + j + ". student: ");
				students[i][1] = sc.next();
				
				boolean isGradeValid = false;
				
				while (!isGradeValid) {
					try {
						System.out.println("Enter the grade: ");
						grades[i]= sc.nextInt();
						isGradeValid = true;
					} 
					catch (Exception e) {
						System.out.println("You have to write number!");
						sc.next(); // for not having endless loop
					}
				} 
			}
			
			for(int i = 0 ; i < totalNumberofStudents; i++){ 
				
				System.out.println("Student " + students[i][0] + " " + students[i][1] + " has grade " + grades[i]);
				totalGrade += grades[i];
			}
			
			System.out.println("Total grade of all students is " + totalGrade);
			
			System.out.println("Number of all students is " + totalNumberofStudents);
			
			avgGrade = (double)totalGrade/totalNumberofStudents;
					
			System.out.println("Average grade of all students is " + String.format("%.2g%n", avgGrade));
			
			//Minimal i maximal grade of all students
			int minGrade = grades[0];
			int maxGrade = grades[0];
			
			for(int i = 1 ; i < grades.length ; i++){
				
				if (grades[i] < minGrade) {
					minGrade = grades[i];
				}
				
				if (grades[i] > maxGrade) {
					maxGrade = grades[i];
				}		
			}
			
			System.out.println("Minimal grade of all students is " + minGrade);
			System.out.println("Maximal grade of all students is " + maxGrade);
				
		}

	}
	
}
