package restaurant;

public class Order {
	
	int id_Order;
	String order;
	boolean isServed;
	boolean isPaid;
	
	public Order() {}

	public Order(int id_Order, String order, boolean isServed, boolean isPaid) {
		super();
		this.id_Order = id_Order;
		this.order = order;
		this.isServed = isServed;
		this.isPaid = isPaid;
	}

	public int getId_Order() {
		return id_Order;
	}

	public void setId_Order(int id_Order) {
		this.id_Order = id_Order;
	}

	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	
	public boolean isServed() {
		return isServed;
	}
	
	public void setServed(boolean isServed) {
		this.isServed = isServed;
	}
	
	public boolean isPaid() {
		return isPaid;
	}
	
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
}
