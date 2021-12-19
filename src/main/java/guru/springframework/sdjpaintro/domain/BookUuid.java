package guru.springframework.sdjpaintro.domain;

import com.mysql.cj.protocol.ColumnDefinition;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class BookUuid {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARBINARY(16)", updatable = false, nullable = false)
    private UUID id;

    private String title;
    private String isbn;
    private String publisher;

    @Column(length = 36, columnDefinition = "VARCHAR(36)")
    private UUID authorUuidId;

    public BookUuid() {
    }

    public BookUuid(String title, String isbn, String publisher, UUID authorUuidId) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.authorUuidId = authorUuidId;
    }

    public BookUuid(UUID id, String title, String isbn, String publisher) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookUuid book = (BookUuid) o;

        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public UUID getAuthorUuidId() {
        return authorUuidId;
    }

    public void setAuthorUuidId(UUID authorUuidId) {
        this.authorUuidId = authorUuidId;
    }
}
