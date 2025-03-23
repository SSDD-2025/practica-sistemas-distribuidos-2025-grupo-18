/*package es.codeurjc.booknest.controller;

import es.codeurjc.booknest.model.Book;
import es.codeurjc.booknest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//NO HE HECHO CAMBIOS


@RestController
@RequestMapping("/book-images")
public class BookImageController {

/
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getBookImage(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent() && book.get().getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "image/jpeg"); // Cambia según el tipo de imagen
            return new ResponseEntity<>(book.get().getImage(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}*/