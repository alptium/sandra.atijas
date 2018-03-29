package funmaths;

public class Answer {
	String possibleAnswer;
	boolean correct;
	Task task;
	
	public Answer (String possibleAnswer, boolean correct) {
		this.possibleAnswer = possibleAnswer;
		this.correct = correct;
	}
	
	public Answer () {
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

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
