package es.codeurjc.trabajoweb_vscode.controller;

import java.io.IOException;
import java.util.Base64;

import es.codeurjc.trabajoweb_vscode.model.*;
import es.codeurjc.trabajoweb_vscode.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//NO HE HECHO CAMBIOS

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService service;
    @Autowired
    private AuthorService authorService;

    public BookController(BookService service, AuthorService authorService) {
        this.service = service;
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        Book book = service.findById(id);
        /*
         * if (book == null) {
         * return "redirect:/error";
         * }
         */

        if (book.getImage() != null) {
            String imageBase64 = Base64.getEncoder().encodeToString(book.getImage());
            book.setImageBase64(imageBase64);
        }

        model.addAttribute("book", book);
        return "book-details";
    }

    @PostMapping("/add")
    public String saveBook(
            @RequestParam String nombre,
            @RequestParam String autor, 
            @RequestParam int año,
            @RequestParam String descripcion, 
            @RequestParam("imagen") MultipartFile imagen, 
            Model model) {

        // Buscar o crear el autor
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

    
        try {
            if (!imagen.isEmpty()) {
                book.setImage(imagen.getBytes()); 
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al procesar la imagen");
            return "add-book"; 
        }

     
        service.save(book);
        return "redirect:/adminLoggedIn"; 
    }

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
