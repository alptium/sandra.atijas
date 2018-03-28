package funmaths;

public class Answer {
	String possibleAnswer;
	boolean correct;
	
	public Answer (String possibleAnswer, boolean correct) {
		this.possibleAnswer = possibleAnswer;
		this.correct = correct;
	}

	public String getPossibleAnswer() {
		return possibleAnswer;
	}

	public void setPossibleAnswer(String possibleAnswer) {
		this.possibleAnswer = possibleAnswer;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	
}
