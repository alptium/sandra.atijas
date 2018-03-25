package bankloans;

public interface Loan {
	 
	public String gettypeOfLoan();
	public void settypeOfLoan(String typeOfLoan);
	public String calculateAcceptance(Applicant applicant);
}
