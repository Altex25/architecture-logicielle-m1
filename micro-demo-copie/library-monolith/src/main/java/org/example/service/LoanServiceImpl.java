package org.example.service;

import org.example.dto.LoanDTO;
import org.example.exception.InvalidBookException;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Book;
import org.example.model.Loan;
import org.example.model.User;
import org.example.repository.BookRepository;
import org.example.repository.LoanRepository;
import org.example.repository.UserRepository;
import org.example.util.DTOMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanServiceImpl(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanDTO> findAll() {
        return loanRepository.findAll().stream().map(DTOMapper::toLoanDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LoanDTO findById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));
        return DTOMapper.toLoanDTO(loan);
    }

    @Override
    public LoanDTO create(LoanDTO dto) {
        if (dto.getBookId() == null || dto.getUserId() == null) {
            throw new InvalidBookException("bookId and userId are required");
        }

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + dto.getBookId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        if (!book.isAvailable()) {
            throw new InvalidBookException("Book is currently unavailable");
        }

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(dto.getLoanDate() != null ? dto.getLoanDate() : LocalDate.now());
        loan.setStatus(Loan.LoanStatus.ACTIVE);

        book.setAvailable(false);
        bookRepository.save(book);

        return DTOMapper.toLoanDTO(loanRepository.save(loan));
    }

    @Override
    public LoanDTO returnBook(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));

        if (loan.getStatus() == Loan.LoanStatus.RETURNED) {
            throw new InvalidBookException("Loan is already returned");
        }

        loan.setStatus(Loan.LoanStatus.RETURNED);
        loan.setReturnDate(LocalDate.now());

        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return DTOMapper.toLoanDTO(loanRepository.save(loan));
    }

    @Override
    public void delete(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));

        if (loan.getStatus() == Loan.LoanStatus.ACTIVE) {
            Book book = loan.getBook();
            book.setAvailable(true);
            bookRepository.save(book);
        }

        loanRepository.delete(loan);
    }
}
