package es.codeurjc.trabajoweb_vscode.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.trabajoweb_vscode.model.BookList;
import es.codeurjc.trabajoweb_vscode.service.BookListService;



@Controller
@RequestMapping("/book-list")
public class BookListController {


    @Autowired
    private BookListService bookListService;

    @GetMapping("/{id}")
    public String getBookListById(@PathVariable Long id, Model model, Principal principal) {
        BookList bookList = bookListService.findById(id);
        if (bookList == null) {
            return "error"; 
        }
        model.addAttribute("bookList", bookList);


        return "bookList-details"; 
    }
    
    
    
}
