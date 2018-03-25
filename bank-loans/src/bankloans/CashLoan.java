package bankloans;

import bankloans.Main.ContractType;

public class CashLoan implements Loan {
	
	private String typeOfLoan;
	
	@Override
	public String calculateAcceptance(Applicant applicant) {
		
		String status = "Rejected";
		if (applicant.getEmployment_status().equalsIgnoreCase("Y") && !(applicant.getContractType()== ContractType.OTHER)) {
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
		this.typeOfLoan = typeOfLoan;
		
	}

}
