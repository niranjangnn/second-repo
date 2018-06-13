package com.controller.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernzateUtil {
private final static SessionFactory fac;
	
	static {
		System.out.println("initializing Sf");
		Configuration con = new Configuration();
		con.configure();
		fac = con.buildSessionFactory();
		System.out.println("fac created\t" + fac);
	}

	public static SessionFactory getFactory() {
		System.out.println("calling getfactory");
		return fac;
	}
}
