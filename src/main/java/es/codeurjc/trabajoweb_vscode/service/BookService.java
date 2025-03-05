package es.codeurjc.trabajoweb_vscode.service;


import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.trabajoweb_vscode.model.*;
import es.codeurjc.trabajoweb_vscode.repository.*;


@Service
public class BookService {
    @Autowired
    private BookRepository repository;
    public BookService(BookRepository repository) { this.repository = repository; }
    public List<Book> findAll() { return repository.findAll(); }
    public void save(Book book) { 

 
        repository.save(book);
    }
    public void delete(Long id) { repository.deleteById(id);}

    
    public Book findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
