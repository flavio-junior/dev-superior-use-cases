package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Page<MovieCardDTO>> findAll(
            @RequestParam(value = "genreId", defaultValue = "0") String genreId,
            Pageable pageable) {
        Page<MovieCardDTO> list = movieService.findAllPaged(genreId, pageable);
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
        MovieDetailsDTO dto = movieService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<ReviewDTO> findReviews(@PathVariable Long id) {
        ReviewDTO dto = reviewService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

}
