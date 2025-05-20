package es.codeurjc.trabajoweb_vscode.RestController;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.trabajoweb_vscode.DTO.AuthorDTO;
import es.codeurjc.trabajoweb_vscode.DTO.AuthorSimpleDTO;
import es.codeurjc.trabajoweb_vscode.repository.AuthorRepository;
import es.codeurjc.trabajoweb_vscode.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/")
    public ResponseEntity<Page<AuthorDTO>> getAuthors(Pageable pageable) {
        Page<AuthorDTO> authorsPage = authorService.getAllAuthors(pageable);
        return ResponseEntity.ok(authorsPage);
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorSimpleDTO authorDTO) throws SQLException {
        AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);
        return ResponseEntity.ok(createdAuthor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable long id) {
        AuthorDTO author = authorService.getAuthor(id);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable long id) {
        authorService.delete(id);
    }

    @PutMapping("/{id}")
    public AuthorSimpleDTO replaceAuthorSimpleDTO(@PathVariable long id, @RequestBody AuthorSimpleDTO updatedAuthorDTO) throws SQLException {

        return authorService.replaceAuthor(id, updatedAuthorDTO);
    }

}
