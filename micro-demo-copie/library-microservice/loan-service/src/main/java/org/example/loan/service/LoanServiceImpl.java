package org.example.loan.service;

import org.example.loan.client.BookClient;
import org.example.loan.client.UserClient;
import org.example.loan.dto.LoanDTO;
import org.example.loan.exception.BusinessException;
import org.example.loan.exception.ResourceNotFoundException;
import org.example.loan.model.Loan;
import org.example.loan.model.LoanStatus;
import org.example.loan.repository.LoanRepository;
import org.example.loan.util.LoanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookClient bookClient;
    private final UserClient userClient;

    public LoanServiceImpl(LoanRepository loanRepository, BookClient bookClient, UserClient userClient) {
        this.loanRepository = loanRepository;
        this.bookClient = bookClient;
        this.userClient = userClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanDTO> findAll() {
        return loanRepository.findAll().stream().map(LoanMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LoanDTO findById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));
        return LoanMapper.toDto(loan);
    }

    @Override
    public LoanDTO create(LoanDTO dto) {
        if (dto.getBookId() == null || dto.getUserId() == null) {
            throw new BusinessException("bookId and userId are required");
        }

        BookClient.BookPayload book = bookClient.findById(dto.getBookId());
        userClient.findById(dto.getUserId());

        if (!book.isAvailable()) {
            throw new BusinessException("Book is currently unavailable");
        }

        bookClient.updateAvailability(dto.getBookId(), false);

        try {
            Loan loan = new Loan();
            loan.setBookId(dto.getBookId());
            loan.setUserId(dto.getUserId());
            loan.setLoanDate(dto.getLoanDate() != null ? dto.getLoanDate() : LocalDate.now());
            loan.setStatus(LoanStatus.ACTIVE);
            return LoanMapper.toDto(loanRepository.save(loan));
        } catch (RuntimeException ex) {
            try {
                bookClient.updateAvailability(dto.getBookId(), true);
            } catch (RuntimeException ignored) {
                // Best effort compensation.
            }
            throw ex;
        }
    }

    @Override
    public LoanDTO returnBook(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new BusinessException("Loan is already returned");
        }

        bookClient.updateAvailability(loan.getBookId(), true);

        try {
            loan.setStatus(LoanStatus.RETURNED);
            loan.setReturnDate(LocalDate.now());
            return LoanMapper.toDto(loanRepository.save(loan));
        } catch (RuntimeException ex) {
            try {
                bookClient.updateAvailability(loan.getBookId(), false);
            } catch (RuntimeException ignored) {
                // Best effort compensation.
            }
            throw ex;
        }
    }

    @Override
    public void delete(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));

        if (loan.getStatus() == LoanStatus.ACTIVE) {
            bookClient.updateAvailability(loan.getBookId(), true);
        }

        loanRepository.delete(loan);
    }
}
