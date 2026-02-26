package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.LoanDTO;
import org.example.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<LoanDTO> findAll() {
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public LoanDTO findById(@PathVariable Long id) {
        return loanService.findById(id);
    }

    @PostMapping
    public ResponseEntity<LoanDTO> create(@Valid @RequestBody LoanDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.create(dto));
    }

    @PostMapping("/{id}/return")
    public LoanDTO returnBook(@PathVariable Long id) {
        return loanService.returnBook(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
