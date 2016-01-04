package com.prov.hrm.utility;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

 	private static final SessionFactory sessionFactory;

	    static {
	        try {
    	          sessionFactory = new Configuration().configure("com/prov/hrm/utility/hibernate.cfg.xml").buildSessionFactory();
	        } catch (Throwable ex) {

	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

}