package restaurant;

public class Ingredient {
	
	int id_ingredient;
	String name;
	double quantity;
	
	public Ingredient() {}
	
	public Ingredient(int id_ingredient, String name, double quantity) {
		super();
		this.id_ingredient = id_ingredient;
		this.name = name;
		this.quantity = quantity;
	}
	
	
	public int getId_ingridient() {
		return id_ingredient;
	}

	public void setId_ingridient(int id_ingredient) {
		this.id_ingredient = id_ingredient;
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
