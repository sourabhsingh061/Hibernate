package com.rays;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestCrud {
	public static void main(String[] args) {
		// testadd();
		// testupdate();
		// testdelete();
		// getdata();
		// testList();
		// TestGetList();
		testGet();
	}

	private static void testGet() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		User dto = (User) session.get(User.class, 1);

		System.out.println(dto.getId());
		System.out.println(dto.getFname());
		System.out.println(dto.getLname());
		System.out.println(dto.getUsername());
		System.out.println(dto.getPassword());

		session.close();
		
		Session session1 = factory.openSession();

		User dto1= (User) session1.get(User.class, 2);

		System.out.println(dto1.getId());
		System.out.println(dto1.getFname());
		System.out.println(dto1.getLname());
		System.out.println(dto1.getUsername());
		System.out.println(dto1.getPassword());
		
		session1.close();

	}

	private static void TestGetList() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("from User where fname like'%abhishek%'");
		List list = q.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			User user = (User) it.next();
			System.out.println(user.getId());
			System.out.println(user.getFname());
			System.out.println(user.getLname());
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
		}
		session.close();
	}

	private static void testList() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("from User");
		List list = q.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			User user = (User) it.next();
			System.out.println(user.getId());
			System.out.println(user.getFname());
			System.out.println(user.getLname());
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
		}
		session.close();
	}

	private static void getdata() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		User user = (User) session.get(User.class, 1);

		System.out.println(user.getId());
		System.out.println(user.getFname());
		System.out.println(user.getLname());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());

		session.close();
	}

	private static void testdelete() {
		User user = new User();
		user.setId(2);
		user.setFname("aakash");
		user.setLname("rathid");
		user.setUsername("aakash061");
		user.setPassword("aakash123");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}

	private static void testupdate() {
		User user = new User();
		user.setId(2);
		user.setFname("aakash");
		user.setLname("rathid");
		user.setUsername("aakash061");
		user.setPassword("aakash123");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();

		System.out.println("updated");
	}

	private static void testadd() {
		User user = new User();
		user.setFname("abhishek");
		user.setLname("verma");
		user.setUsername("abhishek061");
		user.setPassword("abhishek123");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();

		System.out.println("Inserted");

	}

}
