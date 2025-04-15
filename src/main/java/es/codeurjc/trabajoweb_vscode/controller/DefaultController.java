package es.codeurjc.trabajoweb_vscode.controller;

import java.security.Principal;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.trabajoweb_vscode.model.Author;
import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.service.AuthorService;
import es.codeurjc.trabajoweb_vscode.service.BookService;
import es.codeurjc.trabajoweb_vscode.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorService authorService;

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
        }
    }

    public String convertImageToBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        List<Book> books = bookService.findRandomBooks(4);
        books.forEach(book -> {
            if (book.getImage() != null) {
                book.setImageBase64(convertImageToBase64(book.getImage()));
            }
        });
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/search")
    public String buscar(@RequestParam String query, Model model) {
        List<User> usuarios = userService.findByNameContainingIgnoreCase(query);
        List<Book> libros = bookService.findByNameContainingIgnoreCase(query);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("libros", libros);
        model.addAttribute("query", query);

        return "search";
    }

    @GetMapping("/login")
    public String login() {
        return "log_in";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String processSignUp(@RequestParam String username,
            @RequestParam String password,
            Model model) {
        if (userService.existsByName(username)) {
            model.addAttribute("error", "Username already exists.");
            return "sign_up";
        }

        String encodedPassword = passwordEncoder.encode(password);
        userService.save(new User(username, encodedPassword, "USER"));
        return "redirect:/login";
    }

    // @GetMapping("/login")
    // public String login(Model model) {
    //     List<Book> books = bookService.findRandomBooks(4);
    //     books.forEach(book -> {
    //         if (book.getImage() != null) {
    //             book.setImageBase64(convertImageToBase64(book.getImage()));
    //         }
    //     });
    //     model.addAttribute("books", books);
    //     return "index";
    // }
    // @GetMapping("/log-in-admin")
    // public String loginFalso() {
    //     return "log-in-admin";
    // }

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












    
    @GetMapping("/adminLoggedIn/author-manager")
    public String gestionLibros(Model model) {
        List<Author> authors = authorService.findRandomAuthors(4);
        model.addAttribute("authors", authors);
        
        return "author-manager";
    }



    @PostMapping("/adminLoggedIn/book-manager/add-author")
    public String saveaAuthor(
            @RequestParam String nombre,
            @RequestParam String bio,
            Model model) {

        Author author = new Author();
        author.setName(nombre);
        author.setBio(bio);        
        authorService.save(author);
        System.out.println("Libro guardado correctamente.\n");
        return "redirect:/adminLoggedIn";
    }




















    @GetMapping("/adminLoggedIn/add-book")
    public String añadirLibro(Model model, HttpServletRequest request) {
        model.addAttribute("authors", authorService.findAll());

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", csrfToken.getToken());

        return "add-book";
    }

    @GetMapping("/adminLoggedIn/edit-book/{id}")
    public String modificarLibro(@PathVariable Long id, Model model, HttpServletRequest request) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/error";
        }

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        if (csrfToken != null) {
            model.addAttribute("_csrf", csrfToken);
        }

        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("book", book);

        return "edit-book";
    }

    @GetMapping("/adminLoggedIn/delete-book/{id}")
    public String eliminarLibro(@PathVariable Long id, Model model, HttpServletRequest request) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/error";
        }
        model.addAttribute("book", book);

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("_csrf", csrfToken);

        return "delete-book";
    }

    @GetMapping("/adminLoggedIn/add-author")
    public String añadirAutor() {
        return "add-author";
    }

    @GetMapping("/adminLoggedIn/edit-author/{id}")
    public String modificarAutor(@PathVariable Long id, Model model, HttpServletRequest request) {
        Author author = authorService.findById(id);
        if (author == null) {
            return "redirect:/error";
        }
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        if (csrfToken != null) {
            model.addAttribute("_csrf", csrfToken);
        }

        model.addAttribute("author", author);
        return "edit-author";
    }

    @GetMapping("/adminLoggedIn/delete-author/{id}")
    public String eliminarAutor(@PathVariable Long id, Model model, HttpServletRequest request) {
        Author author = authorService.findById(id);
        if (author == null) {
            return "redirect:/error";
        }
        model.addAttribute("author", author);

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("_csrf", csrfToken);

        return "delete-author";
    }

    @PostMapping("/adminLoggedIn/book-manager/add-book")
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
        /*if (author == null) {
            author = new Author();
            author.setName(autor);
            authorService.save(author);
        }*/

        Book book = new Book();
        book.setName(nombre);
        book.setYearPub(año);
        book.setDescription(descripcion);
        book.setAuthor(author);
        bookService.save(book);

        System.out.println("Libro guardado correctamente.\n");
        return "redirect:/adminLoggedIn";
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
