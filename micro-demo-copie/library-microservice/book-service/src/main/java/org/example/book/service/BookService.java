package org.example.book.service;

import org.example.book.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> findAll();

    BookDTO findById(Long id);

    BookDTO create(BookDTO dto);

    BookDTO update(Long id, BookDTO dto);

    BookDTO updateAvailability(Long id, boolean available);

    void delete(Long id);
}
