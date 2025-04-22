package es.codeurjc.trabajoweb_vscode.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.trabajoweb_vscode.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByNameContainingIgnoreCase(String name);
    Page<Book> findByNameContainingIgnoreCase(String query, PageRequest of);

}
