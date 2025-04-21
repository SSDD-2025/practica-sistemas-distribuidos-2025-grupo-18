package es.codeurjc.trabajoweb_vscode.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/book/{bookId}/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;


          @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("user", userService.findByName(principal.getName()));

        } else {
            model.addAttribute("logged", false);
            model.addAttribute("admin", false);
        }
    }

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

        if (book != null && user != null && !userService.alreadyExitsAReview(bookId, user.getId()) && rate > 0 && rate <= 5) {
            Review review = new Review(rate, textReview, book, user);
            reviewService.save(review);
        } else if (book != null && user != null && userService.alreadyExitsAReview(bookId, user.getId())) {
            reviewService.updateReview( user.getId(),bookId, rate, textReview);
        } else {
            System.out.println("Failed to save review");
        }

        return "redirect:/book/" + bookId;
    }

    @PostMapping("/delete")
    public String delete(@PathVariable Long bookId,
            @RequestParam(required = false) Long userId, Principal principal) {
        Book book = bookService.findById(bookId);
        User user = userId != null ? userService.findById(userId)
                : userService.findByName(principal.getName());

        if (book != null && user != null) {
            reviewService.deleteReviewByUserIdAndBookId(user.getId(), bookId);
        }
        return "redirect:/book/" + bookId;

    }


}
