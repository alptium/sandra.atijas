package appointments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
		
		List<Reservation> lstReservations = new ArrayList<Reservation>();
		Date date1 = null;
		
		try (Scanner sc = new Scanner(System.in)) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			
			System.out.println("======= HOLIDAYS TIME========");
			System.out.println("Unesite datum pocetka Vaseg odmora: (u formatu dd-MM-yyyy)");
			String startVacationDate = sc.next();
			System.out.println("Unesite broj dana koliko ste na odmoru? ");
			int numberVacationDays = sc.nextInt();
			
			Date startDateVacation = formatter.parse(startVacationDate);
			Calendar vacationCalendar = Calendar.getInstance(); 
			vacationCalendar.setTime(startDateVacation);
			vacationCalendar.add(Calendar.DATE, numberVacationDays + 1);
			Date endDateVacation = vacationCalendar.getTime();
		    //System.out.println(formatter.format(endDateVacation));
				
			insertHolidays(res,startDateVacation,endDateVacation);
			
			System.out.println("Recite koji datum Vas zanima? (u formatu dd-MM-yyyy)");
			String datumUStringu = sc.next();
		
			
	
		    date1 = formatter.parse(datumUStringu);
		    System.out.println(formatter.format(date1));
		    Calendar c = Calendar.getInstance(); 
		    c.setTime(date1);
		    c.add(Calendar.DATE, 1);
		    date1 = c.getTime();
		    System.out.println(formatter.format(date1));
			
			
			if (date1 == res.getReservationDate()) {
				
			}
			
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
				System.out.println("Connection successful");
			}
			
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful");
			e.printStackTrace();
		}
		return conn;
	}
	
	/*=========================INSERT HOLIDAYS INTO RESERVATION TABLE=====================*/
	public static void insertHolidays (Reservation reservation, Date startDate, Date endDate) {
		
		Connection conn = getConnection(); 
		PreparedStatement preparedStatement = null;	
		
		try {
			
			if (conn != null) {
				System.out.println("Connection successful");
				//2. Create a statement
				//3. Execute SQL Query
				String insertSQL = "INSERT INTO Reservation (customer_name, reservation_date, time_slot, service_type, day_type) "
						+ "VALUES (?, ?, ?, ?, ?)";
			    preparedStatement = conn.prepareStatement(insertSQL);
			    preparedStatement.setString(1, reservation.getCustomerName());
			    preparedStatement.setString(2, reservation.getReservationDate().toString());
			    preparedStatement.setInt(3, reservation.getTimeSlot());
			    preparedStatement.setString(4, reservation.getServiceType());
			    preparedStatement.setString(5, reservation.getDayType().name());
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
	

}

