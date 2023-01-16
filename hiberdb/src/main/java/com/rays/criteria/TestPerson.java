package com.rays.criteria;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.rays.User;

public class TestPerson {
	public static void main(String[] args) {
		// TestAdd();
		// TestSelect();
		 TestRestriction();
		// Testcritprojection();
		// testcritrestproj();
		// TestOrderBy();
		//TestGroupBy();

	}

	private static void TestGroupBy() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("select p.fname,count(*) from PersonDto p group by p.fname");
		List list = q.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			System.out.println(obj[0]);
			System.out.println(obj[1]);

		}
		session.close();
	}

	private static void TestOrderBy() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Criteria crit = session.createCriteria(PersonDto.class);

		crit.addOrder(Order.asc("fname"));
		// crit.addOrder(Order.desc("fname"));

		/*
		 * ProjectionList proj = Projections.projectionList();
		 * proj.add(Projections.property("id"));
		 * proj.add(Projections.property("fname"));
		 * 
		 * crit.setProjection(proj);
		 */
		PersonDto dto = null;
		List list = crit.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (PersonDto) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFname());
			System.out.println(dto.getLname());
			System.out.println(dto.getAddress());
			System.out.println(dto.getPhoneno());
		}
	}

	private static void testcritrestproj() {
		// select id, fname from persondto
		// where-------------criteria+projection+restriction

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Criteria crit = session.createCriteria(PersonDto.class);
		crit.add(Restrictions.like("fname", "%bh%"));

		ProjectionList proj = Projections.projectionList();
		proj.add(Projections.property("id"));
		proj.add(Projections.property("fname"));

		crit.setProjection(proj);

		PersonDto dto = null;
		List list = crit.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			System.out.println(obj[0]);
			System.out.println(obj[1]);

			/*
			 * System.out.println(dto.getLname()); System.out.println(dto.getAddress());
			 * System.out.println(dto.getPhoneno());
			 */
		}
	}

//select id, fname from persondto use------------- criteria+projection
	private static void Testcritprojection() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Criteria crit = session.createCriteria(PersonDto.class);
		ProjectionList proj = Projections.projectionList();
		proj.add(Projections.property("id"));
		proj.add(Projections.property("fname"));

		crit.setProjection(proj);

		PersonDto dto = null;
		List list = crit.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			System.out.println(obj[0]);
			System.out.println(obj[1]);

			/*
			 * System.out.println(dto.getLname()); System.out.println(dto.getAddress());
			 * System.out.println(dto.getPhoneno());
			 */
		}
	}
	// select id, fname ,lname,address,phonr from persondto where fname
	// like-------------restriction

	private static void TestRestriction() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Criteria crit = session.createCriteria(PersonDto.class);

		// (Restriction Strings)
		// crit.add(Restrictions.like("fname", "%bh%"));

		// (Restriction int)
		crit.add(Restrictions.eq("id", 3));

		PersonDto dto = null;
		List list = crit.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (PersonDto) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFname());
			System.out.println(dto.getLname());
			System.out.println(dto.getAddress());
			System.out.println(dto.getPhoneno());

		}

		crit.add(Restrictions.eq("id", 2));
		List list1 = crit.list();
		Iterator it1 = list.iterator();
		while (it.hasNext()) {
			dto = (PersonDto) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFname());
			System.out.println(dto.getLname());
			System.out.println(dto.getAddress());
			System.out.println(dto.getPhoneno());

		}

session.close();
	}

	private static void TestSelect() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Criteria crit = session.createCriteria(PersonDto.class);

		PersonDto dto = null;
		List list = crit.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (PersonDto) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFname());
			System.out.println(dto.getLname());
			System.out.println(dto.getAddress());
			System.out.println(dto.getPhoneno());

		}

	}

	private static void TestAdd() {

		PersonDto dto = new PersonDto();
		dto.setFname("sourabh");
		dto.setLname("Rajput");
		dto.setAddress("indore");
		dto.setPhoneno("9669635650");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(dto);
		tx.commit();
		session.close();

		System.out.println("Inserted");
	}

}
