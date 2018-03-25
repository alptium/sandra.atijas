package bankloans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import bankloans.Applicant;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Main {
	
	public static enum ContractType{FULL_TIME, PART_TIME, FREELANCE, OTHER}
	private static String username = "root";
	private static String password = "Sandra990!!";
	private static String serverName = "localhost";
	private static int portNo = 3306;
	private static String dbName = "mydb";  
	
	public static void main(String[] args) throws IOException {
		
		Applicant applicant  = new Applicant();
		BankPortfolio portfolio = new BankPortfolio(null, null, null, null, null);
		Date now = new Date();
		
		try (Scanner sc = new Scanner(System.in)) {
		//We don't know in advance how many people there will be.
			char toQuit = 'N';
			
			while(true) {
				
				System.out.println("===========BANK LOANS===========");
						
				System.out.println("Enter applicant's name?");
				applicant.setName(sc.next());
				
				System.out.println("Enter applicant's surname?");
				applicant.setSurname(sc.next());
				
				System.out.println("Enter applicant's age?");
				applicant.setAge(sc.nextInt());
				
				System.out.println("Enter applicant's JMBG?");
				applicant.setJmbg(sc.next());
				
				System.out.println("Enter applicant's employment status:");
				applicant.setEmployment_status(sc.next());
				
				System.out.println("Enter applicant's contract (FULL - full-time, PART - part-time, FREE - freelance; OTHER");
				
				switch (sc.next()) {
	            	case "FULL":  applicant.setContractType(ContractType.FULL_TIME);
	                     break;
		            case "PART":  applicant.setContractType(ContractType.PART_TIME);
		                     break;
		            case "FREE":  applicant.setContractType(ContractType.FREELANCE);
		                     break;
		            case "OTHER": applicant.setContractType(ContractType.OTHER);
		                     break;
		            default: applicant.setContractType(ContractType.OTHER);
		            		break;
		            		
				}
						
				System.out.println("Enter applicant's salary");
				applicant.setSalary(sc.nextDouble());
				
				System.out.println("Enter applicant's marital status (S-single, M-married, D - Divorced, W- Widower)");
				applicant.setMarital_status(sc.next());
				
				System.out.println("Enter applicant's number of family members");
				applicant.setNumberFamilyMembers(sc.nextInt());
		
				
				System.out.println("Cash loan or Housing Loan (C or H)?");
		
				
				if (sc.next().equalsIgnoreCase("C")) {
					
					Loan cashLoan = new CashLoan();
					
					cashLoan.settypeOfLoan("C");
					
					applicant.setLoanAcceptance(cashLoan.calculateAcceptance(applicant));
					
					portfolio = new BankPortfolio(applicant, cashLoan, now, cashLoan.gettypeOfLoan(), applicant.loanAcceptance);
				}
				
				if (sc.next().equalsIgnoreCase("H")) {
					
					Loan housingLoan = new HousingLoan();
					
					housingLoan.settypeOfLoan("H");
					
					applicant.setLoanAcceptance(housingLoan.calculateAcceptance(applicant));
					
					portfolio = new BankPortfolio(applicant, housingLoan, now, housingLoan.gettypeOfLoan(), applicant.loanAcceptance);
				}
			
				insertApplicants (applicant);
				
				insertBankPortfolio(portfolio,applicant);
				
				readApplicants ();
				
				System.out.println("Do you want to quit the program? Y/N ");
				toQuit = sc.next().charAt(0);
				
				if (toQuit ==  'Y' || toQuit == 'y') {
					System.exit(0);
				}	
			
			}
		}
	}
	
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
	
	
	
	/*========================================= METHODS FOR APPLICANTS ==============================================*/
	
	public static void insertApplicants (Applicant applicant) {
		
		Connection conn = getConnection(); 
		PreparedStatement preparedStatement = null;	
		
		try {
			
			if (conn != null) {
				System.out.println("Connection successful");
				//2. Create a statement
				//3. Execute SQL Query
				String insertSQL = "INSERT INTO Applicant (name, surname, age, employment_status, salary, marital_status, loan_acceptance, n_family_members, jmbg) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			    preparedStatement = conn.prepareStatement(insertSQL);
			    preparedStatement.setString(1, applicant.getName());
			    preparedStatement.setString(2, applicant.getSurname());
			    preparedStatement.setString(3, applicant.getEmployment_status());
			    preparedStatement.setDouble(4, applicant.getSalary());
			    preparedStatement.setString(5, applicant.getMarital_status());
			    preparedStatement.setInt(6, applicant.getAge());
			    preparedStatement.setString(7, applicant.getLoanAcceptance());
			    preparedStatement.setInt(8, applicant.getNumberFamilyMembers());
			    //preparedStatement.setString(9, applicant.getContractType());
			    preparedStatement.setString(9, applicant.getJmbg());
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
	
	public static void readApplicants () {
		Statement statement = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		try {
			if (conn != null) {

				statement = conn.createStatement();

			    String sql = "SELECT name, surname, age, employment_status, salary, marital_status, loan_acceptance, n_family_members, contract_type, jmbg  "
			    		+ "FROM mydb.applicant ";
			    rs = statement.executeQuery(sql);
				
			    while (rs.next()) {
	                System.out.print(rs.getString(1));
	                System.out.print(" ");
	                System.out.println(rs.getString(2));
	                System.out.print(" ");
	                System.out.println(rs.getInt(3));
	                System.out.print(" ");
	                System.out.print(rs.getString(4));
	                System.out.print(" ");
	                System.out.println(rs.getDouble(5));
	                System.out.print(" ");
	                System.out.println(rs.getString(6));
	                System.out.print(" ");
	                System.out.print(rs.getString(7));
	                System.out.print(" ");
	                System.out.println(rs.getInt(8));
	                System.out.print(" ");
	                System.out.println(rs.getString(9));
	                System.out.print(" ");
	                System.out.println(rs.getString(10));
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
	
	/*========================================= METHODS FOR BANK PORTFOLIO ==============================================*/
	
	public static void insertBankPortfolio (BankPortfolio portfolio, Applicant applicant) {
		
		Connection conn = getConnection(); 
		PreparedStatement preparedStatement = null;	
		Statement statement = null;	
		ResultSet rs = null;
		
		String jmbg = applicant.getJmbg();
		int id_applicant = -1;
		
		try {
			
			if (conn != null) {
				System.out.println("Connection successful");
				//2. Create a statement
				//3. Execute SQL Query
				
				statement = conn.createStatement();
				
			    String selectSQL = "SELECT id_applicant  "
			    		+ "FROM mydb.applicant WHERE jmbg = " + jmbg;
			   
			    rs = statement.executeQuery(selectSQL);
				
			    while (rs.next()) {
			    	id_applicant = rs.getInt(1);
			    }
				
				String insertSQL = "INSERT INTO Bank_Portfolio (created_date_time, loan_type, status, id_applicant) "
						+ "VALUES (?, ?, ?)";
			    preparedStatement = conn.prepareStatement(insertSQL);
			    preparedStatement.setDate(1, (java.sql.Date) portfolio.getCreatedDateTime());
			    preparedStatement.setString(2, portfolio.getLoanType());
			    preparedStatement.setString(3, portfolio.getStatus());
			    preparedStatement.setInt(3, id_applicant);
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
