package es.codeurjc.trabajoweb_vscode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Base64;
import java.util.List;

//import es.codeurjc.booknest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.trabajoweb_vscode.model.Author;
import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.service.AuthorService;
import es.codeurjc.trabajoweb_vscode.service.BookService;


@Controller
@RequestMapping("/")
public class DefaultController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    // @Autowired
    // private ReviewService reviewService;
    // @Autowired
    // private UserService userService;
    // Los comentados todavía no se usan, pero supongo que los tendremos que acabar
    // usando

    public String convertImageToBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Book> books = bookService.findRandomBooks(4);
        books.forEach(book -> {
            if (book.getImage() != null) {
                book.setImageBase64(convertImageToBase64(book.getImage()));
            }
        });
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/log-in-admin")
    public String loginFalso() {
        return "log-in-admin";
    }

    @GetMapping("/adminLoggedIn")
    public String mainLoggedIn(Model model) {
        List<Book> books = bookService.findRandomBooks(4);
        books.forEach(book -> {
            if (book.getImage() != null) {
                book.setImageBase64(convertImageToBase64(book.getImage()));
            }
        });
        model.addAttribute("books", books);
        return "adminLoggedIn";
    }

    @GetMapping("/book-manager")
    public String gestionLibros() {
        return "book-manager"; 
    }

    @GetMapping("/author-manager")
    public String gestionAutores() {
        return "author-manager"; 
    }

    @GetMapping("/add-book")
    public String añadirLibro() {
        return "add-book";
    }

    @GetMapping("/edit-book/{id}")
    public String modificarLibro(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/error";
        }
        model.addAttribute("book", book);
        return "edit-book";
    }

    @GetMapping("/delete-book/{id}")
    public String eliminarLibro(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/error"; 
        }
        model.addAttribute("book", book);
        return "delete-book";
    }

    @GetMapping("/add-author")
    public String añadirAutor() {
        return "add-author";
    }

    @GetMapping("/edit-author/{id}")
    public String modificarAutor(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id);
        if (author == null) {
            return "redirect:/error";
        }
        model.addAttribute("author", author);
        return "edit-author";
    }

    @GetMapping("/delete-author/{id}")
    public String eliminarAutor(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id);
        if (author == null) {
            return "redirect:/error"; 
        }
        model.addAttribute("author", author);
        return "delete-author";
    }

    /*
     * @GetMapping
     * public String showLists(Model model) {
     * model.addAttribute("books", bookService.findRandomBooks(6));
     * return "index";
     * }
     */

    // EN LA PÁGINA PRINCIPAL SERÁ COMO EN LETTERBOX, SE MOSTRARÁN UNOS LIBROS
    // ALEATORIAMENTE
    // TENEMOS QUE SEGUIR EL MISMO SISTEMA DE URLS QUE LETTERBOX O UNO MUY SIMILAR
    // PARA QUE NO NOS LIEMOS
    // CUANDO UNO HAGA CAMBIOS

    /*
     * @GetMapping("add-user")
     * public String addUser() {
     * return "add-user";
     * }
     * 
     * @GetMapping("error")
     * public String getError() {
     * return "error";
     * }
     * 
     * 
     * 
     * @GetMapping("add-author")
     * public String addAuthor() {
     * return "add-author";
     * }
     * 
     * @GetMapping("add-book")
     * public String addBook(Model model) {
     * model.addAttribute("authors", authorService.findAll());
     * return "add-book";
     * }
     * 
     * @GetMapping("add-review")
     * public String addReview(Model model) {
     * model.addAttribute("books", bookService.findAll());
     * model.addAttribute("users", userService.findAll());
     * return "add-review";
     * }
     * 
     * 
     * 
     * 
     * 
     * @PostMapping("/delete-author/{id}")
     * public String deleteAuthor(@PathVariable Long id) {
     * authorService.delete(id);
     * return "redirect:/";
     * }
     * 
     * @PostMapping("/delete-review/{id}")
     * public String deleteReview(@PathVariable Long id) {
     * reviewService.delete(id);
     * return "redirect:/";
     * }
     * 
     * @PostMapping("/delete-book/{id}")
     * public String deleteBook(@PathVariable Long id) {
     * bookService.delete(id);
     * return "redirect:/";
     * }
     * 
     * @PostMapping("/delete-user/{id}")
     * public String deleteUser(@PathVariable Long id) {
     * userService.delete(id);
     * return "redirect:/";
     * }
     */
}
