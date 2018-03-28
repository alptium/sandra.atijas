package funmaths;

import funmaths.Main.DifficultyLevel;

public class Task {
	String descTask;
	DifficultyLevel diffLevel;
	
	public Task (String descTask, DifficultyLevel diffLevel) {
		this.descTask = descTask;
		this.diffLevel = diffLevel;
	}

	public String getDescTask() {
		return descTask;
	}

	public void setDescTask(String descTask) {
		this.descTask = descTask;
	}

	public DifficultyLevel getDiffLevel() {
		return diffLevel;
	}

	public void setDiffLevel(DifficultyLevel diffLevel) {
		this.diffLevel = diffLevel;
	}
	
}
