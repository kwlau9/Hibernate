import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Main Started");
		Author a1 = new Author();
		a1.setFirstName("JK");
		a1.setLastName("Rowling");
		
		Author a2 = new Author();
		a2.setFirstName("Daron");
		a2.setLastName("Acemoglu");
		
		Author a3 = new Author();
		a3.setFirstName("James A.");
		a3.setLastName("Robinson");
		
		Book b1 = new Book();
		b1.setName("The Christmas Pig");
		
		Book b2 = new Book();
		b2.setName("Harry Potter");
		
		Book b3 = new Book();
		b3.setName("Why Nations Fail");
		
		List<Book> books_1 = a1.getBooks();
		books_1.add(b1);
		books_1.add(b2);
		a1.setBooks(books_1);
		
		List<Book>books_2 = a2.getBooks();
		books_2.add(b3);
		a2.setBooks(books_2);
		
		List<Book>books_3 = a3.getBooks();
		books_3.add(b3);
		a3.setBooks(books_3);
		
		
		Transaction tx = null;
		Session session = null;
		 
		  try {
			  Configuration cfg=new Configuration(); 
			  cfg.addAnnotatedClass(Author.class);
			  cfg.addAnnotatedClass(Book.class);
			  SessionFactory factory =cfg.configure().buildSessionFactory();
			  
			  session = factory.openSession();
			  tx = session.beginTransaction();

			  session.save(a1);
			  session.save(a2);
			  session.save(a3);
			  
			  
			  tx.commit();
			  System.out.println("Main finished");
	      } catch (HibernateException e) { 
	    	  if(tx!=null) tx.rollback();
		         e.printStackTrace(); 
	      } catch (Throwable e) {
		         System.err.println("Failed to create sessionFactory object." + e);
		         throw new ExceptionInInitializerError(e); 
		  } finally {
		        if(session!=null)session.close(); 
		  }
	}

}
