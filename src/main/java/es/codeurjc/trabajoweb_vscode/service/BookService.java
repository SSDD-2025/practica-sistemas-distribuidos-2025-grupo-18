package es.codeurjc.trabajoweb_vscode.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.repository.BookRepository;


@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public void save(Book book) {
        repository.save(book);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Book findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // FUNCIÓN CREADA PARA MOSTRAR EN "/" LIBROS ALEATORIOS
    public List<Book> findRandomBooks(int size) {
        List<Book> books = repository.findAll();
        Collections.shuffle(books);
        return books.stream().limit(size).collect(Collectors.toList());

    }


}
