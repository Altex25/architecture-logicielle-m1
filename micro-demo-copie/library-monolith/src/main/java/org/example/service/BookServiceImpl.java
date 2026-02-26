package org.example.service;

import org.example.dto.BookDTO;
import org.example.exception.InvalidBookException;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.util.DTOMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(DTOMapper::toBookDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return DTOMapper.toBookDTO(book);
    }

    @Override
    public BookDTO create(BookDTO dto) {
        validateBook(dto);
        if (bookRepository.findByIsbn(dto.getIsbn()).isPresent()) {
            throw new InvalidBookException("A book with this ISBN already exists");
        }

        Book book = DTOMapper.toBook(dto);
        book.setId(null);
        book.setAvailable(true);
        return DTOMapper.toBookDTO(bookRepository.save(book));
    }

    @Override
    public BookDTO update(Long id, BookDTO dto) {
        validateBook(dto);
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        bookRepository.findByIsbn(dto.getIsbn())
                .filter(book -> !book.getId().equals(id))
                .ifPresent(book -> {
                    throw new InvalidBookException("A book with this ISBN already exists");
                });

        existing.setTitle(dto.getTitle());
        existing.setAuthor(dto.getAuthor());
        existing.setIsbn(dto.getIsbn());
        existing.setAvailable(dto.isAvailable());

        return DTOMapper.toBookDTO(bookRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
    }

    private void validateBook(BookDTO dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new InvalidBookException("Title is required");
        }
        if (dto.getAuthor() == null || dto.getAuthor().isBlank()) {
            throw new InvalidBookException("Author is required");
        }
        if (dto.getIsbn() == null || dto.getIsbn().isBlank()) {
            throw new InvalidBookException("ISBN is required");
        }
    }
}
