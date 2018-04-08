package restaurant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	
	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		Session session = null;
		try {
			// This step will read hibernate.cfg.xml and prepare hibernate for use
			sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
			} 
		catch (Throwable ex) { 
				System.err.println("Failed to create sessionFactory object." + ex);
				throw new ExceptionInInitializerError(ex);
		}
		finally{
			session.flush();
			session.close();
							
		}
	
		ManageOrder MO = new ManageOrder();
		MO.lisOrders();

	}
	
	

}
