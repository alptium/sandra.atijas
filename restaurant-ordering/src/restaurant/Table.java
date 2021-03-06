package restaurant;

public class Table {

	int numberOfTable;
	boolean isOccupied;
	Order order;
	
	public Table() {}

	public Table(int numberOfTable, boolean isOccupied, Order order) {
		super();
		this.numberOfTable = numberOfTable;
		this.isOccupied = isOccupied;
		this.order = order;
	}

	public int getNumberOfTable() {
		return numberOfTable;
	}
	
	public void setNumberOfTable(int numberOfTable) {
		this.numberOfTable = numberOfTable;
	}
	
	public boolean isOccupied() {
		return isOccupied;
	}
	
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
