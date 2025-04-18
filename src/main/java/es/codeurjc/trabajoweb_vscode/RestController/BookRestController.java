package es.codeurjc.trabajoweb_vscode.RestController;

import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.trabajoweb_vscode.DTO.BookDTO;
import es.codeurjc.trabajoweb_vscode.DTO.BookMapper;
import es.codeurjc.trabajoweb_vscode.DTO.BookSimpleDTO;
import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.repository.BookRepository;
import es.codeurjc.trabajoweb_vscode.service.BookService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper mapper;

	@GetMapping("/")
	public Page<BookDTO> getBooks(Pageable pageable) {
	
		return bookRepository.findAll(pageable).map(mapper::toDTO);
	}

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return mapper.toDTO(bookRepository.findById(id).orElseThrow());
    }

    @PostMapping("/")
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        bookService.save(book);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(book.getId())
            .toUri();

        return ResponseEntity.created(location).body(book);
    }

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable long id) {
        bookService.delete(id);
	}

    @PutMapping("/{id}")
	public BookSimpleDTO replaceBook(@PathVariable long id, @RequestBody BookSimpleDTO updatedBookDTO) throws SQLException {

		return bookService.replaceBook(id, updatedBookDTO);
	}

}
