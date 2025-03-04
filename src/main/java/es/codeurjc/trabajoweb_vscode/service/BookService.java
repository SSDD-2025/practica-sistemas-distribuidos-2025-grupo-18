package es.codeurjc.trabajoweb_vscode.service;


import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.model.*;
import es.codeurjc.trabajoweb_vscode.repository.*;


@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    

    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    


    public void createBook(String nombreLibro, int yearPub, Author autor,  Blob imagen, String descripcion) {
        
        Book newBook = new Book(nombreLibro, yearPub, autor, imagen, descripcion);
        bookRepository.save(newBook);
    }


    public Optional<Book> obtenerLibroPorId(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> actualizarLibro(Long id, Book bookInfo) {
        return bookRepository.findById(id).map(book -> {
            book.setName(bookInfo.getName());
            book.setYearPub(bookInfo.getYearPub());
           // book.setAuthor(bookInfo.getAuthor());
            book.setImage(bookInfo.getImage());
            book.setDescription(bookInfo.getDescription());
            return bookRepository.save(book);
        });
    }

    
    public boolean eliminarLibro(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Book saveBook(Book libro) {
        return bookRepository.save(libro);
    }


    
    public Optional<Book> findById(long id) {
		return bookRepository.findById(id);
	}

    

}
