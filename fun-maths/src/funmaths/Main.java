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
		
		try (Scanner sc = new Scanner(System.in)) {
		
			readListOfTasks(lstTasks);
		
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
	
	public static Task readListOfTasks(List<Task> lstTasks) {
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
			    	System.out.print("Level: " + lstTasks.get(i).getDiffLevel() + ": ");
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
		
		return task;
		
	}
	
	

}
