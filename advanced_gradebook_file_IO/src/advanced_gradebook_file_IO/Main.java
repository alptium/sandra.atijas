package advanced_gradebook_file_IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
private static final String FILENAME = "C:\\Users\\Sandra\\Documents\\GitHub\\sandra.atijas\\advanced_gradebook_file_IO\\src\\advanced_gradebook_file_IO\\gradebook.txt";	

public static void main(String[] args) throws IOException {
		
		int[] grades; 
		int totalGrade;
		double avgGrade = 0.0;
		int totalNumberofStudents = 0;
		
		Scanner sc = new Scanner(System.in);
		boolean isGradeValid = false;
		
		// I/O
		File file = new File (FILENAME);
		//write
		BufferedWriter bw = null; 
		FileWriter fw = null;
		//read
		BufferedReader br = null;
		FileReader fr = null;
		String sCurrentLine;
		
		
		while (true) {
			try {
				
				if (!file.exists()) {
					file.createNewFile();
				}
				
				fw = new FileWriter(file.getAbsoluteFile(), true); // argument true is to append the file
				bw = new BufferedWriter(fw);
				
				//bw.write(data);
				
				totalNumberofStudents = 0;
				totalGrade = 0;
				
				
				while (totalNumberofStudents <= 0) {
					try {
						
						System.out.println("Please enter the number of students in your class: ");
						totalNumberofStudents = sc.nextInt();
						
						if (totalNumberofStudents < 0) {
							System.out.println("The number of students has to be positive number!");
						}
					} catch (Exception e) {
						
						System.out.println("The number of students cannot be a string!");
						sc.next(); // for not having endless loop
					}
					
				}
				
				bw.newLine();
				bw.write ("Total Number of all students is: " + totalNumberofStudents);
				bw.newLine();
				
				grades = new int[totalNumberofStudents];
				
				for(int i = 0 ; i < totalNumberofStudents ; i++){ 
					
					int j = i+1;
					System.out.println("Enter the name of your " + j + ". student: ");
					bw.write(sc.next());
					bw.write(" ");
					
					System.out.println("Enter the surname of your " + j + ". student: ");
					bw.write(sc.next());
					bw.write(" ");
					
					isGradeValid = false;
					
					while (!isGradeValid) {
						try {
							System.out.println("Enter the grade: ");
							
							grades[i] = sc.nextInt();
							bw.write(Integer.toString(grades[i]));
							totalGrade += grades[i];
							bw.newLine();
							isGradeValid = true;
							
						} catch (Exception e) {
							
							System.out.println("You have to write number!");
							sc.next();
						}
					}
				}
				
				bw.newLine();
				bw.write("Total grade of all students is " + totalGrade);
				bw.newLine();
				
				avgGrade = (double)totalGrade/totalNumberofStudents;
				
				bw.write("Average grade of all students is " + String.format("%.2g%n", avgGrade));
				bw.newLine();
				
				int minGrade = grades[0];
				int maxGrade = grades[0];
				
				//Minimal i maximal grade of all students
				for(int i = 1 ; i < grades.length ; i++){	
					
					if (grades[i] < minGrade) {
						minGrade = grades[i];
					}
					
					if (grades[i] > maxGrade) {
						maxGrade = grades[i];
					}		
				}

				bw.write("Minimal grade of all students is " + minGrade);
				bw.newLine();
				bw.write("Maximal grade of all students is " + maxGrade);
				bw.newLine();
				
				System.out.println("Done!");
				
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				sCurrentLine = null;
				
				while ((sCurrentLine = br.readLine()) != null) {
					System.out.println(sCurrentLine);
				}
						
				} catch (IOException e) {
					
					e.printStackTrace();
				} finally {
					try {
						
						char toQuit = 'N';
						
						if (bw != null)
							bw.close();
						
						if (fw != null)
							fw.close();
						
						if (br != null)
							br.close();
						
						if (fr != null)
							fr.close();
						
						System.out.println("Do you want to quit the program? Y/N ");
						toQuit = sc.next().charAt(0);
						
						if (toQuit ==  'Y' || toQuit == 'y') {
							System.exit(0);
						}
						
						
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			
		}

	}		

}
