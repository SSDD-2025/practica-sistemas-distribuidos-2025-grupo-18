package es.codeurjc.trabajoweb_vscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.trabajoweb_vscode.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
    
}
