package bankloans;

import java.time.LocalDate;
import java.util.Date;

public class BankPortfolio {
	Applicant applicant;
	Loan loan;
	String status;
	LocalDate createdDateTime;
	String loanType;
	
	
	public BankPortfolio (Applicant applicant, Loan loan, LocalDate createdDateTime, String loanType, String status) {
		
		this.applicant = applicant;
		this.loan = loan;
		this.createdDateTime = createdDateTime;
		this.loanType = loanType;
		this.status = status;
	}
	

	public Applicant getApplicant() {
		return applicant;
	}
	
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	
	public Loan getLoan() {
		return loan;
	}
	
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDate createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	
}
