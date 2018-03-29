package funmaths;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class Main {
	
	public static enum DifficultyLevel {ADVANCED, HIGH, MEDIUM, LOW}
	private static String username = "root";
	private static String password = "Sandra990!!";
	private static String serverName = "localhost";
	private static int portNo = 3306;
	private static String dbName = "mydb";  
	
	public static void main(String[] args) {
		
		List<Task> lstTasks = new ArrayList<Task>();
		List<Answer> lstAnswers = new ArrayList<Answer>();
		
		try (Scanner sc = new Scanner(System.in)) {
			
			readListOfAnswers(lstAnswers);
			readListOfTasks(lstTasks);
			
			System.out.println("========== QUIZ =========== ");
			System.out.println("Choose correct answer: A, B or C: ");
			
			for (int i = 0; i < lstTasks.size(); i++) {
				System.out.println(i+1 + ". " + lstTasks.get(i).getDescTask());
				for (int j = 0; j < 3; j++) { // ima uvek 3 ponudjena
					System.out.print(lstAnswers.get(j).getPossibleAnswer() + " ");
				}
				System.out.println("\n");	
			}
		
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
			    
			   /* for (int i = 0; i < lstAnswers.size(); i++) {
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
	
	

}
