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

import es.codeurjc.trabajoweb_vscode.model.BookList;
import es.codeurjc.trabajoweb_vscode.service.BookListService;
import es.codeurjc.trabajoweb_vscode.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/book-list")
public class BookListController {

    @Autowired
    private BookListService bookListService;

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
    public String getBookListById(@PathVariable Long id, Model model, Principal principal) {
        BookList bookList = bookListService.findById(id);
        if (bookList == null) {
            return "error";
        }
        model.addAttribute("bookList", bookList);

        return "bookList-details";
    }

    @PostMapping("/add")
    public String addBookList(@RequestParam String listName, Principal principal, HttpServletRequest request) {
        BookList bookList = new BookList(userService.findByName(principal.getName()), listName);
        bookListService.save(bookList);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteBookList(@RequestParam Long listId) {
        System.out.println("Deleting book list with ID: " + listId);
        bookListService.delete(listId);
        return "redirect:/";
    }

    @PostMapping("/{listId}/remove-book")
    public String removeBookFromList(@PathVariable Long listId,
            @RequestParam Long bookId) {
        bookListService.removeBookFromList(listId, bookId);
        return "redirect:/book-list/" + listId;
    }

}
