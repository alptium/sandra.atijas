package bankloans;

import bankloans.Main.ContractType;

public class CashLoan implements Loan {
	
	private String loanType;
	
	@Override
	public String calculateAcceptance(Applicant applicant) {
		
		String status = "Rejected";
		if (applicant.getEmployment_status().equalsIgnoreCase("Y") && !(applicant.getContractType()== ContractType.OTHER)) {
				status = "Accepted";
		}
			return status;
	}

	@Override
	public String getloanType() {
		return loanType;
	}

	@Override
	public void setloanType(String loanType) {
		this.loanType = loanType;
		
	}

}
