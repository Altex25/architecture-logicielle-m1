package org.example.book.util;

import org.example.book.dto.BookDTO;
import org.example.book.model.Book;

public final class BookMapper {

    private BookMapper() {
    }

    public static BookDTO toDto(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.isAvailable());
    }

    public static Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setAvailable(dto.isAvailable());
        return book;
    }
}
