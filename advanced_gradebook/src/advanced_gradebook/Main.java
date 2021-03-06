package advanced_gradebook;

import java.util.Scanner;
import java.util.logging.*;


public class Main {
	
	private static Logger logger = Logger.getLogger("Main"); 

	public static void main(String[] args) {
		
		String[][] students;
		int[] grades; 
		int totalGrade = 0;
		double avgGrade = 0.0;
		
		logger.info("Logger name: " + logger.getName()); //name of a LOGGER
		
		try(Scanner sc = new Scanner(System.in)) {	 
				
				int totalNumberStudents = 0;
				//Number of students needs to be grater than 0
				System.out.println("Please enter the number of students in your class: ");
				
				totalNumberStudents = sc.nextInt();
		
				students = new String[totalNumberStudents][2]; //second dimension = 2 -> first and last name
				grades = new int[totalNumberStudents];
				
				for(int i = 0 ; i < totalNumberStudents; i++){ 
					
					int j = i + 1;
					System.out.println("Enter the name of your " + j + ". student: ");
					students[i][0] = sc.next();
					
					System.out.println("Enter the surname of your " + j + ". student: ");
					students[i][1] = sc.next();
					
					System.out.println("Enter the grade: ");
					grades[i]= sc.nextInt();
				}
	
			
				for(int i = 0 ; i < totalNumberStudents; i++){ 
					
					System.out.println("Student " + students[i][0] + " " + students[i][1] + " has grade " + grades[i]);
					totalGrade += grades[i];
				}
				
				System.out.println("Total grade of all students is " + totalGrade);
				
				System.out.println("Number of all students is " + totalNumberStudents);
				
				avgGrade = (double)totalGrade / totalNumberStudents;
						
				System.out.println("Average grade of all students is " + String.format("%.2g%n", avgGrade));
				
				//Minimal i maximal grade of all students
				int minGrade = grades[0];
				int maxGrade = grades[0];
				
				for(int i = 1 ; i < grades.length; i++){
					
					if (grades[i] < minGrade) {
						minGrade = grades[i];
					}
					
					if (grades[i] > maxGrade) {
						maxGrade = grades[i];
					}		
				}
				
				System.out.println("Minimal grade of all students is " + minGrade);
				System.out.println("Maximal grade of all students is " + maxGrade + "\n");
				
				char toQuit = 'N';
				
				System.out.println("Do you want to quit the program? Y/N ");
				toQuit = sc.next().charAt(0);
				
				if (toQuit ==  'Y' || toQuit == 'y') {
					System.exit(0);
				}
					
			}

		}
			
			
}
