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
    private final BookRepository bookRepository;

    public BookService(BookRepository repository) {
        this.bookRepository = repository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findRandomBooks(int size) {
        List<Book> books = bookRepository.findAll();
        Collections.shuffle(books);
        return books.stream().limit(size).collect(Collectors.toList());
    }

    public List<Book> findByNameContainingIgnoreCase(String name){
        return bookRepository.findByNameContainingIgnoreCase(name);
    }


}
