package appointments;

import java.util.Date;
import appointments.Main.DayType;

public class Reservation {
	
	int idRreservation;
	String customerName;
	Date reservationDate;
	int timeSlot;
	String serviceType;
	DayType dayType;
	
	public Reservation (int idRreservation, String customerName, Date reservationDate, int timeSlot, String serviceType, DayType dayType) {
		this.idRreservation = idRreservation;
		this.customerName = customerName;
		this.reservationDate = reservationDate;
		this.timeSlot = timeSlot;
		this.serviceType = serviceType;
		this.dayType = dayType;
	}
	
	public Reservation () {
		super();
	}
	
	
	public int getIdRreservation() {
		return idRreservation;
	}

	public void setIdRreservation(int idRreservation) {
		this.idRreservation = idRreservation;
	}

	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Date getReservationDate() {
		return reservationDate;
	}
	
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	public int getTimeSlot() {
		return timeSlot;
	}
	
	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public DayType getDayType() {
		return dayType;
	}
	
	public void setDayType(DayType dayType) {
		this.dayType = dayType;
	}
	
}
