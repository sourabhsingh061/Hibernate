package com.rays.association;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestOneToOne {


		public static void main(String args[]) {

			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();

			Student s = new Student();
			s.setStudentId(100);
			s.setStudentName("sourabh");

			Address ad = new Address();
			ad.setAddressId(1);
			ad.setCity("indore");
			ad.setState("mp");
			ad.setS(s);

			tx.commit();

			session.save(s);
			tx.commit();
			session.close();

			System.out.println("One to One is Done..!!");

		
	}
}
