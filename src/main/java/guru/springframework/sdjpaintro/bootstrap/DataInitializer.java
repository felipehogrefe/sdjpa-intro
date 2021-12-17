package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book bookDDD = new Book("Domain Driven Desing", "123", "RandomHouse");
        System.out.println("ID: "+ bookDDD.getId());
        Book savedDDD = bookRepository.save(bookDDD);
        System.out.println("ID: "+ savedDDD.getId());

        Book bookSIA = new Book("Spring In Action", "123456", "Oriely");
        System.out.println("ID: "+ bookSIA.getId());
        Book savedSIA = bookRepository.save(bookSIA);
        System.out.println("ID: "+ savedSIA.getId());

        bookRepository.findAll().forEach(book ->{
            System.out.println("Book ID: "+ book.getId());
            System.out.println("Book Title: "+ book.getTitle());
        });
    }
}
