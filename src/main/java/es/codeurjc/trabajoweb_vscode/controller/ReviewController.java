package es.codeurjc.trabajoweb_vscode.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.model.Review;
import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.service.BookService;
import es.codeurjc.trabajoweb_vscode.service.ReviewService;
import es.codeurjc.trabajoweb_vscode.service.UserService;

@Controller
@RequestMapping("/book/{bookId}/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addReview(
            @PathVariable Long bookId,
            @RequestParam int rate,
            @RequestParam String textReview,
            @RequestParam(required = false) Long userId,
            Principal principal) {

        Book book = bookService.findById(bookId);
        User user = userId != null ? userService.findById(userId)
                : userService.findByName(principal.getName());

        if (book != null && user != null) {
            Review review = new Review(rate, textReview, book, user);
            reviewService.save(review);
        } else {
            System.out.println("Failed to save review");
        }

        return "redirect:/book/" + bookId;
    }
    /*@GetMapping("/edit-review/{id}")
    public String showEditReviewForm(@PathVariable("id") Long id, Model model) {
        Review review = service.findById(id);
        if (review == null) {
            return "redirect:/";
        }

        model.addAttribute("review", review);
        model.addAttribute("book", review.getBook());
        return "edit-review";
    }


    @PostMapping("/edit")
    public String efditReview(@RequestParam Long id, @RequestParam int rate, @RequestParam String textReview, @RequestParam Long bookId) {
        Review review = service.findById(id);
        if (review == null) {
            return "redirect:/";
        }

        Book book = bookService.findById(bookId);
        if (book == null) {
            return "redirect:/";
        }

        review.setRate(rate);
        review.setTextReview(textReview);
        review.setBook(book);

        service.save(review);
        return "redirect:/";
}



    @PostMapping("/update/{id}")
    public String editReview(@RequestParam Long id, @RequestParam int rate, @RequestParam String textReview, @RequestParam Long bookId) {
        Review review = service.findById(id);
        if (review == null) {
            return "redirect:/";
        }

        Book book = bookService.findById(bookId);
        if (book == null) {
            return "redirect:/";
        }

        review.setRate(rate);
        review.setTextReview(textReview);
        review.setBook(book);

        service.save(review);
        return "redirect:/";}

    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }*/
}
