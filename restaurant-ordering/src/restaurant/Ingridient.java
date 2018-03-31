package restaurant;

public class Ingridient {
	
	String name;
	double quantity;
	
	public Ingridient() {}
	
	public Ingridient(String name, double quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	
	
}
