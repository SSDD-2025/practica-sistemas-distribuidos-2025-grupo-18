package es.codeurjc.trabajoweb_vscode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import es.codeurjc.trabajoweb_vscode.model.BookList;
import es.codeurjc.trabajoweb_vscode.repository.BookListRepository;

public class BookListservice {
    @Autowired
    private BookListRepository repository;

    
    public List<BookList> findAll() {
        return repository.findAll();
    }

    public void save(BookList booklist) {
        repository.save(booklist);
    }

    public void delete(Long id) {
        repository.deleteById(id); 
    }
    
    public BookList findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    
}
