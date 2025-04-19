package es.codeurjc.trabajoweb_vscode.service;

import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.DTO.BookDTO;

import es.codeurjc.trabajoweb_vscode.DTO.BookMapper;
import es.codeurjc.trabajoweb_vscode.DTO.BookSimpleDTO;
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

    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existing -> {
            existing.setName(updatedBook.getName());
            existing.setYearPub(updatedBook.getYearPub());
            existing.setDescription(updatedBook.getDescription());
            existing.setImage(updatedBook.getImage());
            existing.setImageBase64(updatedBook.getImageBase64());
            
            if (updatedBook.getAuthor() != null) {
                existing.setAuthor(updatedBook.getAuthor());
            }
            return bookRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

     // LO NUEVO DE LUCI

    @Autowired
    BookMapper mapper;
    
    private BookDTO toDTO(Book book) {
		return mapper.toDTO(book);
	}

	private Book toDomain(BookDTO bookDTO) {
		return mapper.toDomain(bookDTO);
	}
    
public BookSimpleDTO replaceBook(long id, BookSimpleDTO updatedDTO) {
    Book oldBook = bookRepository.findById(id).orElseThrow();

    Book updatedBook = new Book();
    updatedBook.setId(id);
    updatedBook.setName(updatedDTO.name());

    updatedBook.setAuthor(oldBook.getAuthor());
    updatedBook.setYearPub(oldBook.getYearPub());
    updatedBook.setImage(oldBook.getImage());
    updatedBook.setImageBase64(oldBook.getImageBase64());
    updatedBook.setDescription(oldBook.getDescription());
    updatedBook.setReviews(oldBook.getReviews());

    bookRepository.save(updatedBook);

    return updatedDTO;
}

public void updateBookImage(Long id, String base64Image) {
    Book book = bookRepository.findById(id).orElseThrow();
    byte[] imageBytes = Base64.getDecoder().decode(base64Image);
    book.setImage(imageBytes);
    book.setImageBase64(base64Image);
    bookRepository.save(book);
}


}
