package ro.cognizant.coderun2023;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CodeRun2023Application {

	public static void main(String[] args) {
		Book book1 = new Book("The Lord of the Rings","J.R.R. Tolkien","George Allen & Unwin");
		Book book2 = new Book("The Ring of the Lords","Brr Jokien","George Allen & Unwin");
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(book1);
			session.save(book2);
			transaction.commit();
		}catch(Exception e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Book> books = session.createQuery("from Book", Book.class).list();
			books.forEach( s -> System.out.println(s.getAuthor()));
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		SpringApplication.run(CodeRun2023Application.class, args);
	}

}
