package org.example.service;

import org.example.dto.ReviewDTO;
import org.example.exception.InvalidBookException;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Book;
import org.example.model.Review;
import org.example.model.User;
import org.example.repository.BookRepository;
import org.example.repository.ReviewRepository;
import org.example.repository.UserRepository;
import org.example.util.DTOMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(DTOMapper::toReviewDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
        return DTOMapper.toReviewDTO(review);
    }

    @Override
    public ReviewDTO create(ReviewDTO dto) {
        if (dto.getBookId() == null || dto.getUserId() == null) {
            throw new InvalidBookException("bookId and userId are required");
        }
        if (dto.getRating() < 1 || dto.getRating() > 5) {
            throw new InvalidBookException("rating must be between 1 and 5");
        }

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + dto.getBookId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        Review review = new Review();
        review.setBook(book);
        review.setUser(user);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDate.now());

        return DTOMapper.toReviewDTO(reviewRepository.save(review));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> findByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId).stream().map(DTOMapper::toReviewDTO).toList();
    }

    @Override
    public void delete(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
        reviewRepository.delete(review);
    }
}
