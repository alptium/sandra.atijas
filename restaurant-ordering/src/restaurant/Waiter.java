package restaurant;

public class Waiter {
	
	int id_waiter;
	String name;
	boolean isFree;
	
	public Waiter() {}

	public Waiter(int id_waiter, String name, boolean isFree) {
		super();
		this.id_waiter = id_waiter;
		this.name = name;
		this.isFree = isFree;
	}

	public int getId_waiter() {
		return id_waiter;
	}

	public void setId_waiter(int id_waiter) {
		this.id_waiter = id_waiter;
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
