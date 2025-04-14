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
    public void updateReview(Long userId, Long bookId, int rate, String textReview) {
        Review  review = reviewRepository.findReviewByUserIdAndBookId(userId, bookId);
        if (review != null) {
            review.setRate(rate);
            review.setTextReview(textReview);
            reviewRepository.save(review); 
        }
    }
    public Review findReviewByUserIdAndBookId(Long userId, Long bookId) {
        return reviewRepository.findReviewByUserIdAndBookId(userId, bookId);
    }

    
    public void deleteReviewByUserIdAndBookId(Long userId, Long bookId) {
        Review review = reviewRepository.findReviewByUserIdAndBookId(userId, bookId);
        if (review != null) {
            System.out.println("Deleting review: " + review.getTextReview());
            System.out.println("User ID: " + review.getUser().getId() + ", Book ID: " + review.getBook().getId());
            reviewRepository.delete(review);
        }
    }

}