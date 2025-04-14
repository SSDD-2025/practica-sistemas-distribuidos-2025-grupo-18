package es.codeurjc.trabajoweb_vscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.trabajoweb_vscode.model.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{
    Review findReviewByUserIdAndBookId(Long userId, Long bookId);
}
