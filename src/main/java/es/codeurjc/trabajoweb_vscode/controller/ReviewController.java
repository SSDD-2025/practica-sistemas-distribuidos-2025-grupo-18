/*package es.codeurjc.booknest.controller;

//marta



import java.util.Optional;


import es.codeurjc.booknest.model.*;
import es.codeurjc.booknest.repository.*;
import es.codeurjc.booknest.service.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//NO HE HECHO CAMBIOS


@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserRepository userRepository;
  
    @PostMapping("/save")
    public String save(@RequestParam int rate, @RequestParam String textReview, @RequestParam Long bookId, @RequestParam Long userId) {
        Optional <Book> book = bookRepository.findById(bookId);
        Optional <User> user = userRepository.findById(userId);
        if(book.isPresent()){
        Review review = new Review();
        review.setRate(rate);
        review.setTextReview(textReview);
        review.setBook(book.get());
        review.setUser(user.get());
        service.save(review);
        return "redirect:/";
    }
        else{
            return"error";
        }
    }

    @GetMapping("/edit-review/{id}")
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
    }
}*/
