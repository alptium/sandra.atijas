package bankloans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
		LocalDate localDate = LocalDate.now();
		List<Applicant> lstApplicant= new ArrayList<Applicant>();
		
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
				
				System.out.println("Enter applicant's employment status: (Y/N)");
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
				
				System.out.println("Enter applicant's marital status (S-single, M-married, D - Divorced, W- Widower):");
				applicant.setMarital_status(sc.next());
				
				System.out.println("Enter applicant's number of family members:");
				applicant.setNumberFamilyMembers(sc.nextInt());
		
				System.out.println("Cash loan or Housing Loan (C or H)?");
				String typeLoan = sc.next();
		
				if (typeLoan.equalsIgnoreCase("C")) {
					
					Loan cashLoan = new CashLoan();
					
					cashLoan.setloanType("C");
					
					applicant.setLoanAcceptance(cashLoan.calculateAcceptance(applicant));
					
					portfolio = new BankPortfolio(applicant, cashLoan, localDate, cashLoan.getloanType(), applicant.loanAcceptance);
				}
				
				if (typeLoan.equalsIgnoreCase("H")) {
					
					Loan housingLoan = new HousingLoan();
					
					housingLoan.setloanType("H");
					
					applicant.setLoanAcceptance(housingLoan.calculateAcceptance(applicant));
					
					portfolio = new BankPortfolio(applicant, housingLoan, localDate, housingLoan.getloanType(), applicant.loanAcceptance);
				}
			
				insertApplicants (applicant);
				
				insertBankPortfolio(portfolio,applicant); 
				
				readApplicants ();

				lstApplicant.add(applicant);
				
				
				
				System.out.println("Do you want to finish with applicant: ");
				toQuit = sc.next().charAt(0);
				
				if (toQuit ==  'Y' || toQuit == 'y') {
					break;
				}	
			
			}
			
			doStatists(lstApplicant);
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
				String insertSQL = "INSERT INTO Applicant (name, surname, age, jmbg, employment_status, salary, marital_status, loan_acceptance, n_family_members, contract_type) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			    preparedStatement = conn.prepareStatement(insertSQL);
			    preparedStatement.setString(1, applicant.getName());
			    preparedStatement.setString(2, applicant.getSurname());
			    preparedStatement.setInt(3, applicant.getAge());
			    preparedStatement.setString(4, applicant.getJmbg());
			    preparedStatement.setString(5, applicant.getEmployment_status());
			    preparedStatement.setDouble(6, applicant.getSalary());
			    preparedStatement.setString(7, applicant.getMarital_status());
			    preparedStatement.setString(8, applicant.getLoanAcceptance());
			    preparedStatement.setInt(9, applicant.getNumberFamilyMembers());
			    preparedStatement.setString(10, applicant.getContractType().name());
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

			    String sql = "SELECT name, surname, age, jmbg, employment_status, salary, contract_type, marital_status, n_family_members, loan_acceptance  "
			    		+ "FROM mydb.applicant ";
			    rs = statement.executeQuery(sql);
				
			    while (rs.next()) {
	                System.out.print("Name of applIcant: " + rs.getString(1));
	                System.out.print(" ");
	                System.out.println(rs.getString(2));
	                System.out.print(" ");
	                System.out.println("Age: " + rs.getInt(3));
	                System.out.print(" ");
	                System.out.println("JMBG: " + rs.getString(4));
	                System.out.print(" ");
	                System.out.print("Employment status: " + rs.getString(5));
	                System.out.print(" ");
	                System.out.println(" with salary: " + rs.getDouble(6));
	                System.out.print(" ");
	                System.out.println("Contract type: " + rs.getString(7));
	                System.out.print(" ");
	                System.out.println("Marital status: " + rs.getString(8));
	                System.out.print(" ");
	                System.out.println("Number of family members: " + rs.getInt(9));
	                System.out.print(" ");
	                System.out.println("The bank give response to an applicant: " + rs.getString(10));
	                System.out.println();
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
				
			    String selectSQL = "SELECT id_applicant "
			    		+ "FROM mydb.applicant WHERE jmbg = " + jmbg;
			   
			    rs = statement.executeQuery(selectSQL);
	
		    	rs.next();
		    	id_applicant = rs.getInt("id_applicant");
			    
				String insertSQL = "INSERT INTO Bank_Portfolio (created_date_time, loan_type, status, id_applicant) "
						+ "VALUES (?, ?, ?, ?)";
				
			    preparedStatement = conn.prepareStatement(insertSQL);
			    
			    preparedStatement.setString(1, DateTimeFormatter.ofPattern("yyy/MM/dd").format(portfolio.getCreatedDateTime()));
			    preparedStatement.setString(2, portfolio.getLoanType());
			    preparedStatement.setString(3, portfolio.getStatus());
			    preparedStatement.setInt(4, id_applicant);
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
	
	//and number of people employed as well as unemployed, as well as percentages.
	public static void doStatists (List<Applicant> lstapplicants) {
		
		int cntAccepted = 0;
		int cntMarried = 0;
		int cntEmployed = 0;
		double totalSalary = 0;
		
		System.out.println("Total number of applicants is: " + lstapplicants.size());
		
		for (int j = 0; j < lstapplicants.size(); j++) {
			System.out.println(lstapplicants.get(j).getMarital_status());
		}
		
		for (int j = 0; j < lstapplicants.size(); j++) {
			
			totalSalary += lstapplicants.get(j).getSalary();
			
			if (lstapplicants.get(j).getLoanAcceptance().equalsIgnoreCase("Accepted")) {
				cntAccepted++;
			}
			
			if (lstapplicants.get(j).getMarital_status().equalsIgnoreCase("M")) {
				cntMarried++;
			}
			
			if (lstapplicants.get(j).getEmployment_status().equalsIgnoreCase("Y")) {
				cntEmployed++;
			}
		}
		
		System.out.println("Total number of accepted applicants is: " + cntAccepted);
		System.out.println("Total number of rejected applicants is: " + (lstapplicants.size()-cntAccepted));
		System.out.println("Percentage of accepted applicants is: " + cntAccepted/lstapplicants.size()*100 + "%");
		System.out.println("Percentage of rejected applicants is: " + ((lstapplicants.size()-cntAccepted))/lstapplicants.size()*100 + "%");
		System.out.println("Average salary of all applicants: " + totalSalary/lstapplicants.size());
		System.out.println("Total number of married applicants is: " + cntMarried);
		System.out.println("Total number of applicants who are not married is: " + (lstapplicants.size()-cntMarried));
		System.out.println("Total number of employed applicants is: " + cntEmployed);
		System.out.println("Total number of unemployed applicants is: " + (lstapplicants.size()-cntEmployed));
	}
	
	
}
