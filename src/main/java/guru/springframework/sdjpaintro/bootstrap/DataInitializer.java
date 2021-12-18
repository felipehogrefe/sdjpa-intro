package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book bookDDD = new Book("Domain Driven Desing", "123", "RandomHouse", null);
        if(bookRepository.findOne(Example.of(bookDDD)).isEmpty()){ bookRepository.save(bookDDD); }

        Book bookSIA = new Book("Spring In Action", "123456", "Oriely", null);
        if(bookRepository.findOne(Example.of(bookSIA)).isEmpty()){ bookRepository.save(bookSIA); }

        System.out.println(bookRepository.findByTitle("Domain Driven Desing").getTitle());

    }
}
