package org.example.book.service;

import org.example.book.dto.BookDTO;
import org.example.book.exception.BusinessException;
import org.example.book.exception.ResourceNotFoundException;
import org.example.book.model.Book;
import org.example.book.repository.BookRepository;
import org.example.book.util.BookMapper;
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
        return bookRepository.findAll().stream().map(BookMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return BookMapper.toDto(book);
    }

    @Override
    public BookDTO create(BookDTO dto) {
        if (bookRepository.findByIsbn(dto.getIsbn()).isPresent()) {
            throw new BusinessException("A book with this ISBN already exists");
        }

        Book book = BookMapper.toEntity(dto);
        book.setId(null);
        book.setAvailable(true);
        return BookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDTO update(Long id, BookDTO dto) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        bookRepository.findByIsbn(dto.getIsbn())
                .filter(book -> !book.getId().equals(id))
                .ifPresent(book -> {
                    throw new BusinessException("A book with this ISBN already exists");
                });

        existing.setTitle(dto.getTitle());
        existing.setAuthor(dto.getAuthor());
        existing.setIsbn(dto.getIsbn());
        existing.setAvailable(dto.isAvailable());

        return BookMapper.toDto(bookRepository.save(existing));
    }

    @Override
    public BookDTO updateAvailability(Long id, boolean available) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        existing.setAvailable(available);
        return BookMapper.toDto(bookRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(existing);
    }
}
