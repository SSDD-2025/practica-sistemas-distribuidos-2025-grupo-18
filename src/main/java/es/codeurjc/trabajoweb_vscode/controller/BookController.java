package es.codeurjc.trabajoweb_vscode.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.trabajoweb_vscode.model.Author;
import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.service.BookListService;
import es.codeurjc.trabajoweb_vscode.service.BookService;
import es.codeurjc.trabajoweb_vscode.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookListService bookListService;

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

    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Long id, Model model, Principal principal, HttpServletRequest request) {
        Book book = bookService.findById(id);

        if (book == null) {
            return "redirect:/error";
        }

        if (book.getImage() != null) {
            String imageBase64 = Base64.getEncoder().encodeToString(book.getImage());
            book.setImageBase64(imageBase64);
        }

        model.addAttribute("book", book);

        boolean alreadyExitsAReview = false;
        if (principal != null) {
            User user = userService.findByName(principal.getName());
            alreadyExitsAReview = userService.alreadyExitsAReview(book.getId(), user.getId());
        }
        model.addAttribute("alreadyExitsAReview", alreadyExitsAReview);

        boolean isUser = false;
        if (principal != null) {
            User user = userService.findByName(principal.getName());
            isUser = user.getRoles().contains("USER");
        }
        model.addAttribute("isUser", isUser);

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        if (csrfToken != null) {
            model.addAttribute("_csrf", csrfToken);
        }
        if (principal != null) {
            model.addAttribute("user", userService.findByName(principal.getName()));

        }

        return "book-details";
    }

    /*@GetMapping("/edit-book/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = service.findById(id);
        if (book == null) {
            return "redirect:/";
        }

        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "edit-book";
    }*/
    @PostMapping("/edit-book/{id}")
    public String editBook(@PathVariable Long id,
            @RequestParam String name,
            @RequestParam int yearPub,
            @RequestParam Author authorName,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile file,
            Model model) throws IOException {

        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/error";
        }

        book.setName(name);
        book.setYearPub(yearPub);
        book.setAuthor(authorName);
        book.setDescription(description);

        if (file != null && !file.isEmpty()) {
            book.setImage(file.getBytes());
        }

        bookService.save(book);
        return "redirect:/adminLoggedIn";
    }

    @PostMapping("/delete-book/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/adminLoggedIn";
    }

    @PostMapping("/{id}/add-to-list")
    public String addToList(@PathVariable Long id, @RequestParam Long listId, Principal principal) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/error";
        }
        User user = userService.findByName(principal.getName());
        if (!userService.userHasList(user.getId(), listId)) {
            return "redirect:/error";
        }
        bookListService.addBookToList(book, listId);
        return "redirect:/book/" + id;
    }

    /* @PostMapping("/adminLoggedIn/book-manager/add-book")
    public String saveBook(
            @RequestParam String nombre,
            @RequestParam String autor,
            @RequestParam int año,
            @RequestParam String descripcion,
            Model model) {

        System.out.println("Entro en el método saveBook() del BookController.\n");
        System.out.println("Nombre: " + nombre);
        System.out.println("Autor: " + autor);
        System.out.println("Año: " + año);
        System.out.println("Descripción: " + descripcion);

        Author author = authorService.findByName(autor);
        if (author == null) {
            author = new Author();
            author.setName(autor);
            authorService.save(author);
        }

        Book book = new Book();
        book.setName(nombre);
        book.setYearPub(año);
        book.setDescription(descripcion);
        book.setAuthor(author);
        service.save(book);
        return "redirect:/adminLoggedIn";
    }*/
 /*
     * @GetMapping("/edit-book/{id}")
     * public String showEditBookForm(@PathVariable("id") Long id, Model model) {
     * Book book = service.findById(id);
     * if (book == null) {
     * return "redirect:/";
     * }
     * 
     * model.addAttribute("book", book);
     * model.addAttribute("authors", authorService.findAll());
     * return "edit-book";
     * }
     * 
     * @PostMapping("/edit")
     * public String efditBook(@RequestParam Long id, @RequestParam String
     * name, @RequestParam int yearPub,
     * 
     * @RequestParam Long authorId) {
     * Book book = service.findById(id);
     * if (book == null) {
     * return "redirect:/";
     * }
     * 
     * Author author = authorService.findById(authorId);
     * if (author == null) {
     * return "redirect:/";
     * }
     * 
     * book.setName(name);
     * book.setYearPub(yearPub);
     * book.setAuthor(author);
     * 
     * service.save(book);
     * return "redirect:/";
     * }
     * 
     * @PostMapping("/update/{id}")
     * public String editBook(@RequestParam Long id, @RequestParam String
     * name, @RequestParam int yearPub,
     * 
     * @RequestParam Long authorId) {
     * Book book = service.findById(id);
     * if (book == null) {
     * return "redirect:/";
     * }
     * 
     * Author author = authorService.findById(authorId);
     * if (author == null) {
     * return "redirect:/";
     * }
     * 
     * book.setName(name);
     * book.setYearPub(yearPub);
     * book.setAuthor(author);
     * 
     * service.save(book);
     * return "redirect:/";
     * }
     * 
     * @PostMapping("/delete/{id}")
     * public String delete(@PathVariable Long id) {
     * service.delete(id);
     * return "redirect:/";
     * }
     */
}
