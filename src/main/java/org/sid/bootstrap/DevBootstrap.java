package org.sid.bootstrap;

import org.sid.model.Author;
import org.sid.model.Book;
import org.sid.model.Publisher;
import org.sid.repositories.AuthorRepository;
import org.sid.repositories.BookRepository;
import org.sid.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData(){
        Publisher publisher = new Publisher();
        publisher.setName("foo");
        publisherRepository.save(publisher);

        //Eric
        Author eric = new Author("Eric","Events");
        Book dd = new Book("Domain Driven Design","1234",publisher);
        eric.getBooks().add(dd);
        dd.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(dd);

        //Road
        Author road = new Author("Road","Johnson");
        Book noEJB = new Book("Java EE Development without EJB","23444",publisher);
        road.getBooks().add(noEJB);
        authorRepository.save(road);
        bookRepository.save(noEJB);
    }
}
