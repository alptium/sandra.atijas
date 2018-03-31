package restaurant;

public class Order {
	
	String order;
	boolean isServed;
	boolean isPaid;
	
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
