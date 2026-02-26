package org.example.service;

import org.example.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findAll();

    ReviewDTO findById(Long id);

    ReviewDTO create(ReviewDTO dto);

    List<ReviewDTO> findByBookId(Long bookId);

    void delete(Long id);
}
