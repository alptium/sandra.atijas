package appointments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Main {
	
	/*Array of available slots for hair dressing*/
	public static int[] timeSlot = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
	
	private static String username = "root";
	private static String password = "Sandra990!!";
	private static String serverName = "localhost";
	private static int portNo = 3306;
	private static String dbName = "mydb";  
	public static enum DayType{PUBLIC_HOLIDAY, VACATION, WORK_DAY, SUNDAY, RESERVED};
	
	
	public static void main(String[] args)  {
		
		Reservation res = new Reservation();
		String treatment = null;
		String customerName = null;
		int wishedTimeSlot = 0;
		List<Reservation> lstReservations = new ArrayList<Reservation>();
		Queue<Reservation> waitingQueue = new LinkedList<Reservation>();  

		try (Scanner sc = new Scanner(System.in)) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			System.out.println("======= HOLIDAYS TIME========");
			System.out.println();
			System.out.println ("Do you want to fullfil your vacation in your agenda? (Y/N)");
			
			if (sc.next().equalsIgnoreCase("Y")) {
				
				System.out.println("Start date of your vacation: (in format yyyy-MM-dd)");
				String startVacationDate = sc.next();
				System.out.println("Number of days while you are in vacation is? ");
				int numberVacationDays = sc.nextInt();
				
				Date startDateVacation = formatter.parse(startVacationDate);
				Calendar vacationCalendar = Calendar.getInstance(); 
				vacationCalendar.setTime(startDateVacation);
				vacationCalendar.add(Calendar.DATE, numberVacationDays);
				Date endDateVacation = vacationCalendar.getTime();
			    //System.out.println(formatter.format(endDateVacation));
					
				// In this part, hair dresser puts her vacation into her agenda
				while (!startDateVacation.equals(endDateVacation)) {
					insertHolidays(startDateVacation);
					vacationCalendar.setTime(startDateVacation);
					vacationCalendar.add(Calendar.DATE, 1);
					startDateVacation = vacationCalendar.getTime();
				}
				
				System.out.println();
			}
		
			System.out.println("======= MAKING RESERVATIONS========");
			System.out.println("What date do you want to reserve? (in format yyyy-MM-dd)");
			String dateTreatment = sc.next();
	
		    System.out.println("Haircut/Styling/Washing/Hair coloring/All: (H/S/W/C/A)");
		    treatment = sc.next();
		    System.out.println("Your name: ");
		    customerName = sc.next();
			
		    Date treatmentDate = formatter.parse(dateTreatment);
		    //System.out.println(formatter.format(treatmentDate));
		    
		    
		    lstReservations = readReservations (dateTreatment);
		    
		    if (lstReservations.size() == 9) {
		    	System.out.println("For this day there is nothing free. I will put you in a queue.");
		    	System.out.println("Which hour is good for you (rounded on the exact hour)?");
		    	wishedTimeSlot = sc.nextInt();
		    	
		    	while (wishedTimeSlot < 8 || wishedTimeSlot > 19) {
		    		System.out.println("The time slot is not arount working hours. Please type again. ");
					wishedTimeSlot = sc.nextInt();
		    	}
		    	
		    	Reservation resInQueue = new Reservation();
		    	resInQueue.setCustomerName(customerName);
		    	resInQueue.setReservationDate(treatmentDate);
		    	resInQueue.setServiceType(treatment);
		    	resInQueue.setTimeSlot(wishedTimeSlot);
		    	waitingQueue.add(resInQueue);
		    	

		    	/*==================== Algorithm for waiting list ================*/
		    }
		    else {
			    if (lstReservations.isEmpty()) {
			    	System.out.println("For this date all time slots are available.");
			    	System.out.println("Possible time slots are from 8h until 19h (rounded on the exact hour):");
			    	wishedTimeSlot = sc.nextInt();
			    	
			    	while (wishedTimeSlot < 8 || wishedTimeSlot > 19) {
			    		System.out.println("The time slot is not arount working hours. Please type again. ");
						wishedTimeSlot = sc.nextInt();
					} 
			    }
			    else {
			    	/*Array of all taken time slots which are already occupied*/
			    	int [] takenSlots = new int[9]; //maximal number of taken slots can be 9
			    
			    	for (int i = 0; i< lstReservations.size(); i++) {
			    		takenSlots[i] = lstReservations.get(i).getTimeSlot();
			    	}
			    	
			    	System.out.println("Which hour is good for you (rounded on the exact hour)?");
			    	wishedTimeSlot = sc.nextInt();
			    	
			    	while (wishedTimeSlot < 8 || wishedTimeSlot > 19) {
			    		System.out.println("The time slot is not arount working hours. Please type again. ");
						wishedTimeSlot = sc.nextInt();
			    	}		
					boolean isThere = false;
					for (int i = 0; i < takenSlots.length; i++) {
						if (wishedTimeSlot == takenSlots[i]) {
							isThere = true;
							System.out.println("The time slot is already taken. Please type again. ");
							wishedTimeSlot = sc.nextInt();
							break;
						}
					} 
					System.out.println("You reserved your time slot!");
			    }
		    }
		    /*System.out.println("Haircut/Styling/Washing/Hair coloring/All: (H/S/W/C/A)");
		    treatment = sc.next();
		    System.out.println("Your name: ");
		    customerName = sc.next();*/
		    
		    insertReservations(customerName, wishedTimeSlot,treatmentDate, treatment);
			
		} catch (ParseException e1) {
			 //handle exception if date is not in "dd-MMM-yyyy" format
			e1.printStackTrace();
		}
		
	}
	
	/*=========================GET CONNECTION=====================*/
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
				//System.out.println("Connection successful");
			}
			
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful");
			e.printStackTrace();
		}
		return conn;
	}
	
	/*=========================INSERT HOLIDAYS INTO RESERVATION TABLE=====================*/
	public static void insertHolidays (Date startDate) {
		
		Connection conn = getConnection(); 
		PreparedStatement preparedStatement = null;	
		
		
		try {
			
			if (conn != null) {
				System.out.println("Connection successful");
				//2. Create a statement
				//3. Execute SQL Query
				String insertSQL = "INSERT INTO Reservation (reservation_date, day_type) "
						+ "VALUES (?, ?)";
			    preparedStatement = conn.prepareStatement(insertSQL);
				preparedStatement.setObject(1, startDate);
			    preparedStatement.setString(2, DayType.VACATION.toString());
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
	
	
	/*=========================INSERT RESERVATIONS INTO RESERVATION TABLE=====================*/
	public static void insertReservations (String customerName, int wishedTimeSlot, Date treatmentDate, String treatment) {
		
		Connection conn = getConnection(); 
		PreparedStatement preparedStatement = null;	
		
		
		try {
	
			if (conn != null) {
				//System.out.println("Connection successful");
				//2. Create a statement
				//3. Execute SQL Query
				String insertSQL = "INSERT INTO Reservation (customer_name, reservation_date, time_slot, service_type, day_type) "
						+ "VALUES (?, ?, ?, ?, ?)";
			    preparedStatement = conn.prepareStatement(insertSQL);
				preparedStatement.setString(1, customerName);
				preparedStatement.setObject(2, treatmentDate); 
				preparedStatement.setInt(3, wishedTimeSlot); 
				preparedStatement.setString(4, treatment);
			    preparedStatement.setString(5, DayType.RESERVED.toString());
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
	
	
	/*=========================READ RESERVATIONS FROM RESERVATION TABLE=====================*/
	public static List<Reservation> readReservations (String treatmentDate) {
		Statement statement = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		Reservation res = null;
		List<Reservation> lstReservations = new ArrayList<Reservation>();
		
		try {
			if (conn != null) {

				statement = conn.createStatement();
				java.sql.Date sqlDate = java.sql.Date.valueOf( treatmentDate );
			    String sql = "SELECT id_reservation, customer_name, reservation_date, time_slot, service_type, day_type  "
			    		+ "FROM mydb.reservation WHERE reservation_date = '" + sqlDate + "'";
			    rs = statement.executeQuery(sql);
				
			    while (rs.next()) {
			    	res = new Reservation();
	                res.setIdRreservation(rs.getInt(1));
	                res.setCustomerName(rs.getString(2));
	                res.setReservationDate(rs.getDate(3));
	                res.setTimeSlot(rs.getInt(4));
	              	res.setServiceType(rs.getString(5));
	              	DayType dt = DayType.valueOf(rs.getString(6));
	                res.setDayType(dt);
	                //System.out.println();
	                lstReservations.add(res);
	            }
			    
			    /*for (int i = 0; i < lstReservations.size(); i++) {
			    	System.out.println(lstReservations.get(i).getDayType());
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
		return lstReservations;
	}
	

}

