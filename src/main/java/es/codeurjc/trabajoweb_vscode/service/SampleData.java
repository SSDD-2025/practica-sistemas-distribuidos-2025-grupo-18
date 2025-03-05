package es.codeurjc.trabajoweb_vscode.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import es.codeurjc.trabajoweb_vscode.model.*;

import es.codeurjc.trabajoweb_vscode.repository.*;


import jakarta.annotation.PostConstruct;

@Service
public class SampleData {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @PostConstruct
    public void init(){
        Author author = new Author("Juan");
        authorRepository.save(author);

    }


    
}
