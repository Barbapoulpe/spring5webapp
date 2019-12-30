package guru.springframework.spring5webapp.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.entities.Author;
import guru.springframework.spring5webapp.entities.Book;
import guru.springframework.spring5webapp.entities.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}

	private void initData() {
		{
			Author ees = new Author("Eric", "Evans");
			Publisher hcs = new Publisher("Harper Collins", "22, Harper Rd, Collins");
			Book ddd = new Book("Domain Driven Design", "1234", hcs);
			ees.getBooks().add(ddd);
			ddd.getAuthors().add(ees);
			authorRepository.save(ees);
			publisherRepository.save(hcs);
			bookRepository.save(ddd);
		}
		{
			Author rjn = new Author("Rod", "Johnson");
			Publisher worx = new Publisher("Worx", "22, Worx Rd, Worcs");
			Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
			rjn.getBooks().add(noEJB);
			authorRepository.save(rjn);
			publisherRepository.save(worx);
			bookRepository.save(noEJB);
		}
	}
}
