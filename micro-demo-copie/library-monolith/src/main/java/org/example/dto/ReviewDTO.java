package org.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ReviewDTO {

    private Long id;
    @NotNull(message = "bookId is required")
    private Long bookId;
    @NotNull(message = "userId is required")
    private Long userId;
    @Min(value = 1, message = "rating must be >= 1")
    @Max(value = 5, message = "rating must be <= 5")
    private int rating;
    private String comment;
    private LocalDate createdAt;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, Long bookId, Long userId, int rating, String comment, LocalDate createdAt) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
