package funmaths;

import java.util.Date;

public class Child {
	
	String name;
	int noCorrectAnswers;
	Date testDuration;
	
	public Child (String name, int noCorrectAnswers,Date testDuration) {
		this.name = name;
		this.noCorrectAnswers = noCorrectAnswers;
		this.testDuration = testDuration;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNoCorrectAnswers() {
		return noCorrectAnswers;
	}
	
	public void setNoCorrectAnswers(int noCorrectAnswers) {
		noCorrectAnswers = noCorrectAnswers;
	}
	
	public Date getTestDuration() {
		return testDuration;
	}
	
	public void setTestDuration(Date testDuration) {
		this.testDuration = testDuration;
	}

}
