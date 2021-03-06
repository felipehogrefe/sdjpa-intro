package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.AuthorUuid;
import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.domain.BookUuid;
import guru.springframework.sdjpaintro.repositories.AuthorUuidRepository;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import guru.springframework.sdjpaintro.repositories.BookUuidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;
    private final BookUuidRepository bookUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository, BookUuidRepository bookUuidRepository) {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
        this.bookUuidRepository = bookUuidRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book bookDDD = new Book("Domain Driven Desing", "123", "RandomHouse", null);
        if(bookRepository.findOne(Example.of(bookDDD)).isEmpty()){ bookRepository.save(bookDDD); }

        Book bookSIA = new Book("Spring In Action", "123456", "Oriely", null);
        if(bookRepository.findOne(Example.of(bookSIA)).isEmpty()){ bookRepository.save(bookSIA); }

        System.out.println(bookRepository.findByTitle("Domain Driven Desing").getTitle());

        authorUuidRepository.deleteAll();

        AuthorUuid authorUuid = new AuthorUuid();
        authorUuid.setFirstName("Joe");
        authorUuid.setLastName("Buck");
        AuthorUuid savedAuthorUuid = authorUuidRepository.save(authorUuid);
        System.out.println("Saved Author UUID: " + savedAuthorUuid.getId());

        bookUuidRepository.deleteAll();

        BookUuid bookUuid = new BookUuid();
        bookUuid.setTitle("All About UUIDs");
//        bookUuid.setAuthorUuidId(savedAuthorUuid.getId());
        BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        System.out.println("Saved Book UUID: " + savedBookUuid.getId());

    }
}
