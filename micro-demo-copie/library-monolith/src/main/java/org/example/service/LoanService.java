package org.example.service;

import org.example.dto.LoanDTO;

import java.util.List;

public interface LoanService {

    List<LoanDTO> findAll();

    LoanDTO findById(Long id);

    LoanDTO create(LoanDTO dto);

    LoanDTO returnBook(Long id);

    void delete(Long id);
}
