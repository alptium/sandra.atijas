package elitesportsoop;

import elitesportsoop.Questionnaire;

public class Candidate {
	
	private String name;
	private String surname;
	private double age;
	private double weight;
	private double height;
	private Questionnaire guestionnaire;
	
	// Constructor for the class Candidate
	
	public Candidate (String name, String surname, double age, double weight, double height) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.weight = weight;
		this.height = height;
	}
	
	// Methods - GETTER and SETTER
	
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
	
	public double getAge() {
		return age;
	}
	
	public void setAge(double age) {
		this.age = age;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	 
}

