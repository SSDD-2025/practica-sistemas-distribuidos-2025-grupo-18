package es.codeurjc.trabajoweb_vscode.RestController;

import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.trabajoweb_vscode.DTO.AuthorDTO;
import es.codeurjc.trabajoweb_vscode.DTO.AuthorMapper;
import es.codeurjc.trabajoweb_vscode.DTO.AuthorSimpleDTO;
import es.codeurjc.trabajoweb_vscode.DTO.BookDTO;
import es.codeurjc.trabajoweb_vscode.DTO.BookSimpleDTO;
import es.codeurjc.trabajoweb_vscode.model.Author;
import es.codeurjc.trabajoweb_vscode.repository.AuthorRepository;
import es.codeurjc.trabajoweb_vscode.service.AuthorService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

@Autowired
AuthorService authorService;

@Autowired
AuthorRepository authorRepository;

@Autowired
private AuthorMapper mapper;

@GetMapping("/")
public Page<AuthorDTO> getAuthors(Pageable pageable) {
	return authorRepository.findAll(pageable).map(mapper::toDTO);
}

@PostMapping("/")
public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
    authorService.save(author);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(author.getId())
        .toUri();

    return ResponseEntity.created(location).body(author);
}

@GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable Long id) {
        return mapper.toDTO(authorRepository.findById(id).orElseThrow());
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
