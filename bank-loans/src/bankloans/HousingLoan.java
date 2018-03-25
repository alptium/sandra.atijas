package bankloans;

import bankloans.Main.ContractType;

public class HousingLoan implements Loan {

	private String typeOfLoan;
	
	@Override
	public String calculateAcceptance(Applicant applicant) {
		
		String status = "Rejected";
		
		if (applicant.getEmployment_status().equalsIgnoreCase("Y") && 
			applicant.getContractType()== ContractType.FULL_TIME && applicant.getSalary() >= 50000) {
				status = "Accepted";
		}
			return status;
	}

	@Override
	public String gettypeOfLoan() {
		return typeOfLoan;
	}

	@Override
	public void settypeOfLoan(String typeOfLoan) {
		typeOfLoan = "HOUSING LOAN";
	}

}
