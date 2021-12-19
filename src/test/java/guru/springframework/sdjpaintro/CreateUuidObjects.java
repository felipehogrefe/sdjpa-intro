package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.AuthorUuid;
import guru.springframework.sdjpaintro.domain.BookUuid;
import guru.springframework.sdjpaintro.repositories.AuthorUuidRepository;
import guru.springframework.sdjpaintro.repositories.BookUuidRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreateUuidObjects {

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Test
    void testCreateAndRetrieveBook(){
        BookUuid newBook = new BookUuid();
        newBook.setTitle("new book");
        BookUuid savedBook = bookUuidRepository.save(newBook);

       assertThat(savedBook.getId());

        BookUuid retrievedBook = bookUuidRepository.getById(savedBook.getId());

        Assert.isTrue(Objects.equals(retrievedBook.getTitle(), savedBook.getTitle()));

    }

    @Test
    void testCreateAuthor(){
        AuthorUuid newAuthor = new AuthorUuid();
        newAuthor.setFirstName("New");
        newAuthor.setLastName("Author");
        AuthorUuid savedAuthor = authorUuidRepository.save(newAuthor);

        assertThat(savedAuthor.getId());



        Assert.isTrue(Objects.equals(authorUuidRepository.getById(savedAuthor.getId()).getFirstName(), savedAuthor.getFirstName()));
    }
}
