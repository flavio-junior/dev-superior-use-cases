package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private AuthService authService;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        Optional<Review> obj = reviewRepository.findById(id);
        Review entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ReviewDTO(entity);
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review entity = new Review();
        entity.setText(dto.getText());
        entity.setMovie(new Movie(dto.getMovieId()));
        entity.setUser(authService.authenticated());
        entity = reviewRepository.save(entity);
        return new ReviewDTO(entity);
    }
}
