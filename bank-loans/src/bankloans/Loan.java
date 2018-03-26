package bankloans;

public interface Loan {
	 
	public String getloanType();
	public void setloanType(String loanType);
	public String calculateAcceptance(Applicant applicant);
}
