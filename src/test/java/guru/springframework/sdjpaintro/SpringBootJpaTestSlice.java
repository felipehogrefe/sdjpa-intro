package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class SpringBootJpaTestSlice {

    @Autowired
    BookRepository bookRepository;

//    @Rollback(value = false)
    @Commit
    @Order(1)
    @Test
    void testJpaTestSlice(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(0);

        bookRepository.save(new Book("Test Book", "000", "Test Publisher"));

        long countAfter = bookRepository.count();

        assertThat(countAfter).isEqualTo(1);
        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSliceTransaction(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(1);

        bookRepository.save(new Book("Test Book", "000", "Test Publisher"));

        long countAfter = bookRepository.count();

        assertThat(countAfter).isEqualTo(2);
        assertThat(countBefore).isLessThan(countAfter);
    }
}
