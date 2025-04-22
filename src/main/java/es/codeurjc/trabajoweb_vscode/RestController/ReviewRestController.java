package es.codeurjc.trabajoweb_vscode.RestController;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import es.codeurjc.trabajoweb_vscode.DTO.ReviewDTO;
import es.codeurjc.trabajoweb_vscode.DTO.ReviewSimpleDTO;
import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.model.Review;
import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.repository.BookRepository;
import es.codeurjc.trabajoweb_vscode.repository.UserRepository;
import es.codeurjc.trabajoweb_vscode.repository.ReviewRepository;
import es.codeurjc.trabajoweb_vscode.service.ReviewService;


@RestController
@RequestMapping("/api/reviews")
public class ReviewRestController {
    
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public Page<ReviewDTO> getReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(ReviewDTO::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        Review review = reviewService.findById(id);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ReviewDTO(review));
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewSimpleDTO input) {
        Book book = bookRepository.findById(input.bookId()).orElse(null);
        User user = userRepository.findById(input.userId()).orElse(null);

        if (book == null || user == null) {
            return ResponseEntity.badRequest().build();
        }

        Review review = new Review(input.rate(), input.textReview(), book, user);
        reviewService.save(review);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ReviewDTO(review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        Review review = reviewService.findById(id);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }

        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDTO updateReview(@PathVariable long id, @RequestBody ReviewDTO updatedDTO) {
        return reviewService.replaceReview(id, updatedDTO);
    }

}
