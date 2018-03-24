package bankloans;

import bankloans.Main.ContractType;

public class CashLoan implements Loan {
	
	@Override
	public String calculateAcceptance(Applicant applicant) {
		
		String status = "Rejected";
		if (applicant.getEmployment_status().equalsIgnoreCase("Y") && !(applicant.getContractType()== ContractType.OTHER)) {
				status = "Accepted";
		}
			return status;
	}

}
