package org.example.loan.service;

import org.example.loan.dto.LoanDTO;

import java.util.List;

public interface LoanService {

    List<LoanDTO> findAll();

    LoanDTO findById(Long id);

    LoanDTO create(LoanDTO dto);

    LoanDTO returnBook(Long id);

    void delete(Long id);
}
