package restaurant;

public class Waiter {
	
	String name;
	boolean isFree;
	
	public Waiter() {}

	public Waiter(String name, boolean isFree) {
		super();
		this.name = name;
		this.isFree = isFree;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isFree() {
		return isFree;
	}
	
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	
	

}
