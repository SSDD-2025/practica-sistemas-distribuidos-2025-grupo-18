package es.codeurjc.trabajoweb_vscode.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.trabajoweb_vscode.model.Author;
import es.codeurjc.trabajoweb_vscode.service.AuthorService;
import es.codeurjc.trabajoweb_vscode.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
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

    @GetMapping("/{id}")
    public String getAuthorDetails(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id);
        if (author == null) {
            return "redirect:/error";
        }

        model.addAttribute("author", author);
        return "author-details";
    }

    @PostMapping("/add")
    public String saveAuthor(
            @RequestParam String nombre,
            @RequestParam String bio,
            Model model) {

        Author author = new Author();
        author.setName(nombre);
        author.setBio(bio);
        authorService.save(author);

        return "redirect:/author-manager";
    }

    @PostMapping("/delete-author/{id}")
    public String delete(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/adminLoggedIn";
    }

    @PostMapping("/edit-author/{id}")
    public String editBook(@PathVariable Long id, @RequestParam String name, @RequestParam String bio
    ) {
        Author author = authorService.findById(id);
        if (author == null) {
            return "redirect:/error";
        }
        author.setName(name);
        author.setBio(bio);

        authorService.save(author);
        return "redirect:/adminLoggedIn";
    }

}
