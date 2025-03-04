package es.codeurjc.trabajoweb_vscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.trabajoweb_vscode.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import es.codeurjc.trabajoweb_vscode.model.Author;
import es.codeurjc.trabajoweb_vscode.model.Book;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    
    List<Author> findByNameIgnoreCase (String name);

    List<Author> findByBooksContainingOrderByBooksName(Book book);

}
