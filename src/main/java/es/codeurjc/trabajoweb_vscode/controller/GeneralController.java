package es.codeurjc.trabajoweb_vscode.controller;

import java.sql.Blob;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import es.codeurjc.trabajoweb_vscode.model.*;
import es.codeurjc.trabajoweb_vscode.repository.*;
import es.codeurjc.trabajoweb_vscode.service.*;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class GeneralController {

	@Autowired
	private UserService userService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;


   @GetMapping("/")
	public String getPosts(Model model){
		model.addAttribute("books", bookService.findAll());
		return "notLoggedIn";
	}

   @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            Long userId = userService.login(username, password);
            return "redirect:user/"+userId;
        } catch (RuntimeException e) {
            model.addAttribute("error", "Credenciales incorrectas");
            return "error";  // Vuelve a la página de login si falla
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "log_in";
    }



   @PostMapping("/signUp")
    public String singup(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            userService.singup(username, password);
            return "redirect:";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Credenciales incorrectas");
            return "index";  // Vuelve a la página de login si falla
        }
    }

    @GetMapping("/signUp")
    public String showSingupPage() {
        return "sign_up";
    }



	@GetMapping("/user/addBook")
	public String mostrarFormularioAgregarLibro(Model model) {
		return "addBook";
	}

	@PostMapping("/user/addBook")
	public String agregarLibro(
			@RequestParam String name,
			@RequestParam int yearPub,
			@RequestParam String author,
			@RequestParam String description,
			@RequestParam MultipartFile image,
			Model model) {

		try {
		
			Author newAuthor = new Author(author);

			newAuthor = authorService.save(newAuthor); 
			Blob imageBlob = new SerialBlob(image.getBytes());

			bookService.createBook(name, yearPub, newAuthor, imageBlob, description);

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}

		return "redirect:/";
	}





	@GetMapping("/user/{id}")
	public String getUser(Model model, @PathVariable long id) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			model.addAttribute("books", bookService.findAll());
			model.addAttribute("reviews", user.get().getReviews());
			model.addAttribute("mybooks", user.get().getBooks());
			return "mainLoggedIn";
		} else {
			return "error";
		}
	}
	
	@GetMapping("/books/{id}")
	public String showPost(Model model, @PathVariable long id) {
		Optional<Book> op = bookService.findById(id);
		if (op.isPresent()) {
			Book post = op.get();
			model.addAttribute("post", post);
			return "show_books";
		} else {
			return "post_not_found";
		}
	}
	
	

	@GetMapping("/posts/{id}")
	public String getPost(Model model, @PathVariable long id) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			model.addAttribute("user", user.get());
			//String likedText = userService.isPostLikedByCurrentUser(post.get()) ? "Unlike" : "Like";
			//model.addAttribute("likedText", likedText);
			return "show_user";
		} else {
			return "user_not_found";
		}
	}


    @GetMapping("/error")
	public String ADIOS(){
		return "error";
	}
    
    
}
