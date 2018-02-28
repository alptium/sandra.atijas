package advancedgradebookcmd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
	
private static final String FILENAME = "C:\\Users\\Sandra\\Documents\\GitHub\\sandra.atijas\\advanced-gradebook-file-io\\src\\advancedgradebookfile\\gradebook.txt";	

private static String username = "root";
private static String password = "Sandra990!!";
private static String serverName = "localhost";
private static int portNo = 3306;
private static String dbName = "mydb";  

public static Connection getConnection() {
	
	Connection conn = null;
	MysqlDataSource dataSource = new MysqlDataSource();
	
	//1. Get a connection to database
	
	dataSource.setUser(username);
	dataSource.setPassword(password);
	dataSource.setServerName(serverName);
	dataSource.setPortNumber(portNo);
	dataSource.setDatabaseName(dbName);
	
	try {
		conn = dataSource.getConnection();
		
		if (conn != null) {
			System.out.println("Connection successful");
		}
		
	} catch (SQLException e) {
		System.out.println("Connection unsuccessful");
		e.printStackTrace();
	}
	return conn;
}

public static void readStudents () {
	String name;
	String surname;
	int grade;
	Statement statement = null;
	ResultSet rs = null;
	Connection conn = getConnection();
	try {
		if (conn != null) {

			statement = conn.createStatement();

		    String sql = "SELECT name, surname, grade FROM mydb.student ";
		    rs = statement.executeQuery(sql);
			
		    while (rs.next()) {
		    	name = rs.getString(1);
                System.out.print(name);
                System.out.print(" ");
                surname = rs.getString(2);
                System.out.println(surname);
                System.out.print(" ");
                grade = rs.getInt(3);
                System.out.println(grade);
            }
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			if(statement!=null) {			
				statement.close();
			}
			
			if(rs!=null) {			
				rs.close();
			}
			
			if(conn!=null) {			
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

public static void insertStudents (String name, String surname, int grade) {
	
	Connection conn = getConnection(); 
	PreparedStatement preparedStatement = null;	
	
	try {
		
		if (conn != null) {
			System.out.println("Connection successful");
			//2. Create a statement
			//3. Execute SQL Query
			String insertSQL = "INSERT INTO Student (name, surname, grade) VALUES (?, ?, ?)";
		    preparedStatement = conn.prepareStatement(insertSQL);
		    preparedStatement.setString(1, name);
		    preparedStatement.setString(2, surname);
		    preparedStatement.setInt(3, grade);
		    preparedStatement.executeUpdate();
		}
		
	} catch (SQLException e) {
		System.out.println("Connection unsuccessful");
		e.printStackTrace();
	}
	finally {
		try {
			if(preparedStatement!=null) {			
				preparedStatement.close();
			}
			
			if(conn!=null) {			
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

public static void insertGradebookReport (int totalNumberStudents, double avgGrade, int minGrade, int maxGrade) {
	
	Connection conn = getConnection(); 
	PreparedStatement preparedStatement = null;	
	LocalDate localDate = LocalDate.now();
	
	try {
		
		if (conn != null) {
			System.out.println("Connection successful");
			//2. Create a statement
			//3. Execute SQL Query
			String sql = "INSERT INTO Gradebook_report (numberStudents, avgGrade, minGrade, maxGrade, date) VALUES (?, ?, ?, ?, ?)";
		    preparedStatement = conn.prepareStatement(sql);
		    preparedStatement.setInt(1, totalNumberStudents);
		    preparedStatement.setDouble(2, avgGrade);
		    preparedStatement.setInt(3, minGrade);
		    preparedStatement.setInt(4, maxGrade);
		    preparedStatement.setString(5, DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));
		    preparedStatement.executeUpdate();
		}
		
	} catch (SQLException e) {
		System.out.println("Connection unsuccessful");
		e.printStackTrace();
	}
	finally {
		try {
			if(preparedStatement!=null) {			
				preparedStatement.close();
			}
			
			if(conn!=null) {			
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}



public static void main(String[] args) throws IOException {
		
		String[] names;
		String[] surnames;
		int[] grades; 
		int totalNumberStudents;
		int totalGrade;
		double avgGrade = 0.0;
		
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
		String writeInFile;
		
		readStudents();
		
		try (Scanner sc = new Scanner(System.in)) {
		
			while (true) {
				try {
					
					if (!file.exists()) {
						file.createNewFile();
					}
					
					fw = new FileWriter(file.getAbsoluteFile(), true); // argument true is to append the file
					bw = new BufferedWriter(fw);
					
					//bw.write(data);
					
					totalNumberStudents = 0;
					totalGrade = 0;
					
					while (totalNumberStudents <= 0) {
						try {
							
							System.out.println("Please enter the number of students in your class: ");
							totalNumberStudents = sc.nextInt();
							
							if (totalNumberStudents < 0) {
								System.out.println("The number of students has to be positive number!");
							}
						} catch (Exception e) {
							
							System.out.println("The number of students cannot be a string!");
							sc.next(); // for not having endless loop
						}	
					}
					
					System.out.println("File-based system or a Database system? (FB/DB)");
					writeInFile = sc.next(); // for not having endless loop

					names = new String[totalNumberStudents];
					surnames = new String[totalNumberStudents];
					grades = new int[totalNumberStudents];
					
					for(int i = 0 ; i < totalNumberStudents; i++){ 
						
						int j = i + 1;
						System.out.println("Enter the name of your " + j + ". student: ");
						names[i] = sc.next();
						
						System.out.println("Enter the surname of your " + j + ". student: ");
						surnames[i] = sc.next();
						
						isGradeValid = false;
						
						while (!isGradeValid) {
							try {
								
								grades[i] = 0;

								while (grades[i] <= 0) {
									System.out.println("Enter the grade: ");
									grades[i] = sc.nextInt();
									totalGrade += grades[i];
									
									if (grades[i] < 0) {
										System.out.println("The student's grade has to be a positive number.");
									}	
								}
								
								isGradeValid = true;
								
							} catch (Exception e) {
								
								System.out.println("You have to write number!");
								sc.next();
							}
						}
					}
					
					avgGrade = (double)totalGrade/totalNumberStudents;
					
					if (writeInFile.equals("FB")) { 
						
						bw.newLine();
						bw.write ("Total Number of all students is: " + totalNumberStudents);
						bw.newLine();
						
						for(int i = 0 ; i < totalNumberStudents; i++){ 
							bw.write(names[i]);
							bw.write(" ");
							bw.write(surnames[i]);
							bw.write(" ");
							bw.write(Integer.toString(grades[i]));
							bw.newLine();
						}
						
						bw.newLine();
						bw.write("Total grade of all students is " + totalGrade);
						bw.newLine();
						
						bw.write("Average grade of all students is " + String.format("%.2g%n", avgGrade));
						bw.newLine();
					}
					else {
						
						for(int i = 0 ; i < totalNumberStudents; i++) {
							insertStudents(names[i], surnames[i], grades[i]);
						}
					
					}
					
					System.out.println("Total grade of all students is " + totalGrade);
					System.out.println("Average grade of all students is " + String.format("%.2g%n", avgGrade));	
					
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
					
					if (writeInFile.equals("FB")) { 
						
						bw.write("Minimal grade of all students is " + minGrade);
						bw.newLine();
						bw.write("Maximal grade of all students is " + maxGrade);
						bw.newLine();
						
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						sCurrentLine = null;
						
						while ((sCurrentLine = br.readLine()) != null) {
							System.out.println(sCurrentLine);
						}
					}
					else {
						
						insertGradebookReport (totalNumberStudents, avgGrade, minGrade, maxGrade);
					}
					
					System.out.println("Minimal grade of all students is " + minGrade);
					System.out.println("Maximal grade of all students is " + maxGrade + "\n");
							
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


}
