package es.codeurjc.trabajoweb_vscode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.trabajoweb_vscode.service.AuthorService;
import es.codeurjc.trabajoweb_vscode.service.BookService;
import es.codeurjc.trabajoweb_vscode.service.ReviewService;



import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import es.codeurjc.trabajoweb_vscode.model.*;
import es.codeurjc.trabajoweb_vscode.repository.*;
import es.codeurjc.trabajoweb_vscode.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class ListController {
    @Autowired
    private  AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    public ListController(AuthorService authorService, BookService bookService, ReviewService reviewService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.reviewService = reviewService;
    }
    @GetMapping("add-user")
    public String addUser() {
        return "add-user";
    }

    @GetMapping("error")
    public String getError() {
        return "error";
    }
    
    

    @GetMapping("add-author")
    public String addAuthor() {
        return "add-author";
    }

    @GetMapping("add-book")
    public String addBook(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "add-book";
    }

    @GetMapping("add-review")
    public String addReview(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("users", userService.findAll());
        return "add-review";
    }

    

    @GetMapping
    public String showLists(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("reviews", reviewService.findAll());
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @PostMapping("/delete-author/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/delete-review/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/";
    }
    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/";
    }

}
