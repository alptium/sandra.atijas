package funmaths;


public class Child {
	
	String name;
	int noCorrectAnswers;
	String testDuration;
	String reachedLevel;
	
	public Child (String name, int noCorrectAnswers,String testDuration) {
		this.name = name;
		this.noCorrectAnswers = noCorrectAnswers;
		this.testDuration = testDuration;
	}
	
	public Child () {
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
		this.noCorrectAnswers = noCorrectAnswers;
	}
	
	public String getTestDuration() {
		return testDuration;
	}
	
	public void setTestDuration(String testDuration) {
		this.testDuration = testDuration;
	}

	public String getReachedLevel() {
		return reachedLevel;
	}

	public void setReachedLevel(String reachedLevel) {
		this.reachedLevel = reachedLevel;
	}

}
