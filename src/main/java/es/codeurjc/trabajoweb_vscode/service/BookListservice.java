package es.codeurjc.trabajoweb_vscode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.model.BookList;
import es.codeurjc.trabajoweb_vscode.repository.BookListRepository;


@Service
public class BookListService {
    @Autowired
    private BookListRepository bookListRepository;
 

    
    public List<BookList> findAll() {
        return bookListRepository.findAll();
    }

    public void save(BookList booklist) {
        bookListRepository.save(booklist);
    }

    public void delete(Long id) {
        bookListRepository.deleteById(id); 
    }
    
    public BookList findById(Long id) {
        return bookListRepository.findById(id).orElse(null);
    }

    public void addBookToList(Book book, Long listId) {
        BookList bookList = findById(listId);
        if (bookList != null) {
            bookList.getBooks().add(book);
            save(bookList);
        }
    }

}
