package com.tp.complet.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

public class BookBuilderTest {
    @Test
    void shouldBuildWithALlAttributes() {
        BookBuilder book = BookBuilder.builder()
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .publicationYear("1925")
                .publisher("Scribner")
                .isbn("978-0743273565")
                .tags(List.of("Classic", "Novel", "American Literature"))
                .build();

        assertEquals("The Great Gatsby", book.getTitle());
        assertEquals("F. Scott Fitzgerald", book.getAuthor());
        assertEquals("1925", book.getPublicationYear());
        assertEquals("Scribner", book.getPublisher());
        assertEquals("978-0743273565", book.getIsbn());
        assertEquals(List.of("Classic", "Novel", "American Literature"), book.getTags());
    }
}
