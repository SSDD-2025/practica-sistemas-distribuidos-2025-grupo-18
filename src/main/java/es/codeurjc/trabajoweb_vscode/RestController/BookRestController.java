package es.codeurjc.trabajoweb_vscode.RestController;

import java.net.URI;
import java.sql.SQLException;
import java.util.Base64;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.trabajoweb_vscode.DTO.BookDTO;
import es.codeurjc.trabajoweb_vscode.DTO.BookMapper;
import es.codeurjc.trabajoweb_vscode.DTO.BookSimpleDTO;
import es.codeurjc.trabajoweb_vscode.DTO.UserDTO;
import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.repository.BookRepository;
import es.codeurjc.trabajoweb_vscode.service.AuthorService;
import es.codeurjc.trabajoweb_vscode.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper mapper;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/")
    public Page<BookDTO> getBooks(Pageable pageable) {

        return bookRepository.findAll(pageable).map(mapper::toDTO);
    }
    
    @GetMapping("/search")
    public Page<BookDTO> searchUsers(@RequestParam String query, @RequestParam int page) {
        return bookRepository.findByNameContainingIgnoreCase(query, PageRequest.of(page, 10)).map(mapper::toDTO);
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return mapper.toDTO(bookRepository.findById(id).orElseThrow());
    }

    @PostMapping("/")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {

        bookDTO = bookService.createBook(bookDTO);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(bookDTO.id()).toUri();

        return ResponseEntity.created(location).body(bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    public BookSimpleDTO replaceBook(@PathVariable long id, @RequestBody BookSimpleDTO updatedBookDTO) throws SQLException {

        return bookService.replaceBook(id, updatedBookDTO);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getBookImage(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));

        if (book.getImage() != null && book.getImage().length > 0) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(book.getImage());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Void> updateBookImage(@PathVariable Long id, @RequestBody String base64Image) {
        Book book = bookRepository.findById(id).orElseThrow();

        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            book.setImage(imageBytes);
            book.setImageBase64(base64Image);
            bookRepository.save(book);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    
    @DeleteMapping("/{id}/image")
    public ResponseEntity<Void> deleteBookImage(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book != null && book.getImage() != null && book.getImage().length > 0) {
            book.setImage(null);
            bookRepository.save(book);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
