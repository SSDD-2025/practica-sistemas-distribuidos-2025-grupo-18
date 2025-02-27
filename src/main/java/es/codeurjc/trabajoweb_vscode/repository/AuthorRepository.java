package es.codeurjc.trabajoweb_vscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.trabajoweb_vscode.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    
}
