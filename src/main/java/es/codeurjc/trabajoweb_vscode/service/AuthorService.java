package es.codeurjc.trabajoweb_vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.model.*;
import es.codeurjc.trabajoweb_vscode.repository.*;
import jakarta.transaction.Transactional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;
    public List<Author> findAll() { return repository.findAll(); }
    public void save(Author author) { repository.save(author); }
    public void delete(Long id) { repository.deleteById(id); }



    public Author findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public boolean updateAuthor(Long id, String newName) {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setName(newName);
            repository.save(author);
            return true; // Indica que se actualizó con éxito
        }
        return false; // Indica que el autor no existía
    }
}
