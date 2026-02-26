package org.example.loan.repository;

import org.example.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByBookId(Long bookId);

    List<Loan> findByUserId(Long userId);
}
