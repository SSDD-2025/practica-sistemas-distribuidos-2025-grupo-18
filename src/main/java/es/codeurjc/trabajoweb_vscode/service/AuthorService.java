package es.codeurjc.trabajoweb_vscode.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.model.*;
import es.codeurjc.trabajoweb_vscode.repository.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.repository.AuthorRepository;

import es.codeurjc.trabajoweb_vscode.model.*;


@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    
    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }

    public List<Author> findByName(String name) { 
        return authorRepository.findByNameIgnoreCase(name);
    }


    public List<Author> findByBook(Book book) {
        return authorRepository.findByBooksContainingOrderByBooksName(book);
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Author save(Author autor) {
        return authorRepository.save(autor);
    }


}
