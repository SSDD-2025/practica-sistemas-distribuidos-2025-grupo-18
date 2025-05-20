package es.codeurjc.trabajoweb_vscode.DTO;

import es.codeurjc.trabajoweb_vscode.model.Review;

public record ReviewDTO(
    Long id,
    int rate,
    String textReview,
    String bookTitle,
    String username
) {
    public ReviewDTO(Review review) {
        this(
            review.getId(),
            review.getRate(),
            review.getTextReview(),
            review.getBook().getName(),
            review.getUser().getName()
        );
    }
}
