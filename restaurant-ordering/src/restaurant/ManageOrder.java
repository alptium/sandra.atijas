package restaurant;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class ManageOrder {
	
	private static SessionFactory factory; 
	public void lisOrders() {
		
		Session session = factory.openSession();
	    Transaction tx = null;
	    
	    try {
	    	tx = session.beginTransaction();
	    	List orders = session.createQuery("FROM Order").list(); 
	    	
	    	for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
	    		Order order = (Order) iterator.next(); 
	            System.out.print("Meal Name: " + order.getOrder());
	            
	            String isServed = "No";
	            if (order.isServed) 
	            		isServed = "Yes"; 
	            System.out.print("Is served: " + isServed); 
	            
	            String isPaid= "No";
	            if (order.isPaid) 
	            	isPaid = "Yes"; 
	            System.out.println("Is paid: " + isPaid); 
	        }
	        
	    	tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	

}
