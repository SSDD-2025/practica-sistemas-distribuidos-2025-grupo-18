package es.codeurjc.trabajoweb_vscode.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.model.Author;
import es.codeurjc.trabajoweb_vscode.repository.AuthorRepository;
import jakarta.transaction.Transactional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author findByName(String name) {
        return authorRepository.findByName(name).orElse(null);
    }

    @Transactional
    public boolean updateAuthor(Long id, String newName) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setName(newName);
            authorRepository.save(author);
            return true;
        }
        return false;
    }

    public List<Author> findRandomAuthors(int size) {
        List<Author> authors = authorRepository.findAll();
        Collections.shuffle(authors);
        return authors.stream().limit(size).collect(Collectors.toList());
    }

}
