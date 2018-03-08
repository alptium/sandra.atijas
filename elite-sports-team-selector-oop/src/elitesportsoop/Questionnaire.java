package elitesportsoop;

import java.util.ArrayList;
import java.util.List;

public class Questionnaire {
	
	public static double minAge = 20;
	public static int minTeams = 3;

	public static List<String> questions = new ArrayList<String>();
	
	List<Object> answers;
	boolean result;
	
	// Constructor for the class Questionnaire
	
	public Questionnaire () {
		List<Object> questions = new ArrayList<Object>();
		List<Object> answers = new ArrayList<Object>();
		result = false;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	public List<Object> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Object> answers) {
		this.answers = answers;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	// here is applied the logic which candidate will be accepted or rejected
	public boolean calculateResult () {
		
		
		if ((int)answers.get(0) > minAge && (int)answers.get(3) >= minTeams) {
			
			// Candidate cannot have health problems, need t have sport's spirit, to focus even with presure, to share statistics with teammates
			if (
				(answers.get(4).equals("N") || answers.get(4).equals("n")) && 	
			    (answers.get(6).equals("Y") || answers.get(6).equals("y")) &&
				(answers.get(7).equals("C") || answers.get(7).equals("SG") || answers.get(7).equals("c") || answers.get(7).equals("sg")) &&
			    (answers.get(8).equals("Y") || answers.get(8).equals("y")) &&
				(answers.get(9).equals("Y") || answers.get(9).equals("y"))
				) {
				result = true;

			}
		}
		return result;
	} // end of method calculateResult
	
	
	
	
}
