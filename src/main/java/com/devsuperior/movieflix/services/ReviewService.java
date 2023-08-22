package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewDTO insert(ReviewDTO dto) {
        Review entity = new Review();
        entity.setId(dto.getId());
        entity.setText(dto.getText());
        entity = reviewRepository.save(entity);
        return new ReviewDTO(entity);
    }
}
