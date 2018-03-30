package funmaths;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class Main {
	
	public static enum DifficultyLevel {ADVANCED, HIGH, MEDIUM, LOW}
	public static int numPossibleAnswers = 3;
	private static String username = "root";
	private static String password = "Sandra990!!";
	private static String serverName = "localhost";
	private static int portNo = 3306;
	private static String dbName = "mydb";  
	
	/*public static String currentTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());
        
        return time;
	}*/
	
	public static void main(String[] args) {
		
		List<Task> lstTasks = new ArrayList<Task>();
		List<Answer> lstAnswers = new ArrayList<Answer>();
		List<Child> lstChildren =  new ArrayList<Child>();
		String diffculty = "";
		int cntCorrectAns = 0;
		Child child = new Child();

		try (Scanner sc = new Scanner(System.in)) {
			char toQuit = 'N';
			readListOfAnswers(lstAnswers);
			readListOfTasks(lstTasks);
		
			String[] arrayAnswers = new String[lstTasks.size()];
			String [] arrayCorrectAnswers = new String[lstTasks.size()];
		
			int k = 0;
			// Storing only correct answers of the quiz
			for (int i = 0; i < lstAnswers.size(); i++) {
				
				if (lstAnswers.get(i).isCorrect() == true) {
					arrayCorrectAnswers[k] = lstAnswers.get(i).getPossibleAnswer().substring(0,1);
					k++;
				}
			}
			
			while(true) {
				System.out.println("========== QUIZ =========== ");
				System.out.println("Choose correct answer: A, B or C: ");
				int tmp = 0;
				int j = -1;
				long startTime =  System.currentTimeMillis();
				for (int i = 0; i < lstTasks.size(); i++) {
					System.out.println(i+1 + ". " + lstTasks.get(i).getDescTask());
					for ( j = tmp ; j < tmp + 3; j++) { // ima uvek 3 ponudjena
						System.out.print(lstAnswers.get(j).getPossibleAnswer() + " ");
					}
					tmp = j;
					System.out.println();
					System.out.print("Your answer is: ");
					arrayAnswers[i] = sc.next();
					if (arrayAnswers[i].equals(arrayCorrectAnswers[i])) {
						cntCorrectAns++;
					}
					else {
						diffculty = lstTasks.get(i).getDiffLevel().toString();
						System.out.println("Wrong!");
						break;
					}
				}
				long endTime = System.currentTimeMillis();
				NumberFormat formatter = new DecimalFormat("#0.00000");
				String testDuration = formatter.format((endTime - startTime) / 1000d);
				//System.out.print("Execution time is " + duration + " seconds");
				System.out.println("\n");
				System.out.println("Enter your name: ");
		
			    child.setName(sc.next());
			    child.setTestDuration(testDuration);
			    child.setNoCorrectAnswers(cntCorrectAns);
			    child.setReachedLevel(diffculty);
			    
			    
			    insertChildren(child);
			    
			    lstChildren.add(child);
			    
				System.out.println("\n");
				System.out.println("Another game? ");
				toQuit = sc.next().charAt(0);
				
				if (toQuit ==  'N' || toQuit == 'N') {
					break;
				}	
				
			}
			doStatistics();
		}
	}
	
	
	/*================= GET CONNECTION =================*/
	
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
	
	/*================= READ FROM TABLE TASK =================*/
	
	public static void readListOfTasks(List<Task> lstTasks) {
		Statement statement = null;
		ResultSet rs = null;
		String difficulty;
		Task task = new Task();
		Connection conn = getConnection();
		
		try {
			if (conn != null) {

				statement = conn.createStatement();

			    String sql = "SELECT DESC_TASK, DIFFICULTY_LEVEL  "
			    		+ "FROM mydb.task ";
			    
			    rs = statement.executeQuery(sql);
				
			    while (rs.next()) {
					task = new Task();
			    	task.setDescTask(rs.getString(1));
			    	difficulty = rs.getString(2);
	                task.setDiffLevel(DifficultyLevel.valueOf(difficulty));
	                lstTasks.add(task);
	            }
			    
			    /*for (int i = 0; i < lstTasks.size(); i++) {
			    	System.out.print("Level " + lstTasks.get(i).getDiffLevel() + ": ");
			    	System.out.println(lstTasks.get(i).getDescTask().toString() + " ");
			    }*/
				
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
	
	
	/*================= READ FROM TABLE TASK =================*/
	
	public static void readListOfAnswers(List<Answer> lstAnswers) {
		Statement statement = null;
		Statement statement2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		Answer answer = new Answer();
		Connection conn = getConnection();
		int id_task = -1;
		
		try {
			if (conn != null) {
				
				statement = conn.createStatement();
				statement2 = conn.createStatement();
				
			    String sql = "SELECT POSSIBLE_ANSWER, CORRECT, ID_TASK  "
			    		+ "FROM mydb.answer ";
			    
			    rs = statement.executeQuery(sql);
				
			    while (rs.next()) {
			    	
			    	answer = new Answer();
			    	answer.setPossibleAnswer(rs.getString(1));
			    	answer.setCorrect(rs.getBoolean(2));
			    	id_task = rs.getInt(3);
			       
			    	String sqlTask = "SELECT DESC_TASK, DIFFICULTY_LEVEL  "
		    			   + "FROM mydb.task WHERE id_task = " + id_task;
		    	   
			    	rs2 = statement2.executeQuery(sqlTask);
		    	   
			    	rs2.next();
			    	String description = rs2.getString(1);
			    	String difficulty = rs2.getString(2);
			    	Task task = new Task(description, DifficultyLevel.valueOf(difficulty));
		    	   
			    	answer.setTask(task);
			    	lstAnswers.add(answer);
	            }
			    
			    /*for (int i = 0; i < lstAnswers.size(); i++) {
			    	System.out.println("TASK is : " + lstAnswers.get(i).getTask().getDescTask() + " ");
			    	System.out.print("Possible answer " + lstAnswers.get(i).getPossibleAnswer() + ": ");
			    	System.out.println("Correct answer: " + lstAnswers.get(i).isCorrect() + " ");
			    }*/
				
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
	
	/*================= INSERT INTO TABLE CHILS =================*/
	public static void insertChildren (Child child) {
		
		Connection conn = getConnection(); 
		PreparedStatement preparedStatement = null;	
		
		try {
			
			if (conn != null) {
				System.out.println("Connection successful");
				//2. Create a statement
				//3. Execute SQL Query
				String insertSQL = "INSERT INTO CHILD (name, number_of_correct_answers, duration_of_test, reached_level) "
						+ "VALUES (?, ?, ?, ?)";
			    preparedStatement = conn.prepareStatement(insertSQL);
			    preparedStatement.setString(1, child.getName()); 
			    preparedStatement.setInt(2, child.getNoCorrectAnswers());
			    preparedStatement.setString(3, child.getTestDuration());
			    preparedStatement.setString(4, child.getReachedLevel());
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
	
	/*================= READ FROM TABLE CHILD =================*/
	
	//With procedure statistics, parents can follow the progress of their child 
	public static void doStatistics() {
		Statement statement = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		try {
			if (conn != null) {

				statement = conn.createStatement();

			    String sql = "SELECT name, number_of_correct_answers, duration_of_test, reached_level  "
			    		+ "FROM mydb.child ORDER BY number_of_correct_answers DESC";
			    
			    rs = statement.executeQuery(sql);
			    System.out.println("=========STATISTICS===========");
			    while (rs.next()) {
			    	System.out.println(rs.getString(1));
			    	System.out.println(rs.getInt(2));
			    	System.out.println(rs.getString(3));
			    	System.out.println(rs.getString(4));
			    	System.out.println("\n");
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
	

}
