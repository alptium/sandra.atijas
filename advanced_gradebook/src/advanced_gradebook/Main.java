package advanced_gradebook;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String[] students;
		int[] grades; 
		int totalNumberofStudents = 0;
		int totalGrade = 0;
		double avgGrade = 0.0;
		
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
			
			System.out.println("Number of all students is " + totalNumberofStudents);
			
			avgGrade = (double)totalGrade/(double)totalNumberofStudents;
			System.out.println("Average grade of all students is " + avgGrade);
			
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
