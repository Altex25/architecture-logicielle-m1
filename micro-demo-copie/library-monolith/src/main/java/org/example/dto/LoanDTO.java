package org.example.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LoanDTO {

    private Long id;
    @NotNull(message = "bookId is required")
    private Long bookId;
    @NotNull(message = "userId is required")
    private Long userId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String status;

    public LoanDTO() {
    }

    public LoanDTO(Long id, Long bookId, Long userId, LocalDate loanDate, LocalDate returnDate, String status) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
