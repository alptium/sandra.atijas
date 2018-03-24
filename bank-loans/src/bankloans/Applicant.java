package bankloans;

import bankloans.Main.ContractType;

public class Applicant {

	String name;
	String surname;
	int age;
	double salary; 
	String marital_status;
	String employment_status;
	ContractType contractType;
	int numberFamilyMembers;
	String loanAcceptance;

	/*public Applicant (String name, String surname, int age, double salary, String marital_status, String employment_status, int numberFamilyMembers, ContractType contractType) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.salary = salary;
		this.marital_status = marital_status;
		this.employment_status = employment_status;
		this.numberFamilyMembers = numberFamilyMembers;
		this.contractType = contractType;
	}*/
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getMarital_status() {
		return marital_status;
	}
	
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	
	public String getEmployment_status() {
		return employment_status;
	}
	
	public void setEmployment_status(String employment_status) {
		this.employment_status = employment_status;
	}
	
	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public int getNumberFamilyMembers() {
		return numberFamilyMembers;
	}

	public void setNumberFamilyMembers(int numberFamilyMembers) {
		this.numberFamilyMembers = numberFamilyMembers;
	}

	public String getLoanAcceptance() {
		return loanAcceptance;
	}

	public void setLoanAcceptance(String loanAcceptance) {
		this.loanAcceptance = loanAcceptance;
	}             
	
}
