package es.codeurjc.trabajoweb_vscode.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.trabajoweb_vscode.model.BookList;


public interface BookListRepository extends JpaRepository<BookList,Long> {
    
}
