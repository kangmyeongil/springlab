package com.springlab.hibernatetest.demo;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.springlab.hibernatetest.entities.Student;

public class HibernateDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Student student1 = new Student("Chulsu", "Kim", "a@gmail.com", 
				(new GregorianCalendar(2000, 1, 1)).getTime());
		Student student2 = new Student("Gildong", "Hong", "b@gmail.com", 
				(new GregorianCalendar(1900, 1, 1)).getTime());

		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			session.persist(student1); // save -> persist, update -> merge, delete
			System.out.println(">>> Student 레코드 추가, ID: " + student1.getId());
			session.persist(student2);
			System.out.println(">>> Student 레코드 추가, ID: " + student2.getId());
			transaction.commit();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		    if(transaction != null) {	
		    	transaction.rollback();
		    }
		}
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
		
			List<Student> students = session.createQuery("from Student", Student.class).list();
			students.forEach(s -> System.out.println(">>> " + s.toString()));
		} catch(Exception ex) {
			ex.printStackTrace();
		    if(transaction != null) {	
		    	transaction.rollback();
		    }
		}
		
		
	}

}
