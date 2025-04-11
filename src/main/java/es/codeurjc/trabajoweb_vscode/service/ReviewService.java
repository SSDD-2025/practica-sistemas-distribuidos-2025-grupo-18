package es.codeurjc.trabajoweb_vscode.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.model.Review;
import es.codeurjc.trabajoweb_vscode.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private final  ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository repository) {
        this.reviewRepository = repository;
    }
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
    public void save(Review review) {
        reviewRepository.save(review);
    }
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

}