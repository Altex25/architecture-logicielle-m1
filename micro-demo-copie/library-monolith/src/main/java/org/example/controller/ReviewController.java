package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.ReviewDTO;
import org.example.service.ReviewService;
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
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDTO> findAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ReviewDTO findById(@PathVariable Long id) {
        return reviewService.findById(id);
    }

    @GetMapping("/book/{bookId}")
    public List<ReviewDTO> findByBookId(@PathVariable Long bookId) {
        return reviewService.findByBookId(bookId);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> create(@Valid @RequestBody ReviewDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
