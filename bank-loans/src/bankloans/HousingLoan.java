package bankloans;

import bankloans.Main.ContractType;

public class HousingLoan implements Loan {

	@Override
	public String calculateAcceptance(Applicant applicant) {
		
		String status = "Rejected";
		
		if (applicant.getEmployment_status().equalsIgnoreCase("Y") && 
			applicant.getContractType()== ContractType.FULL_TIME && applicant.getSalary() >= 50000) {
				status = "Accepted";
		}
			return status;
	}

}
