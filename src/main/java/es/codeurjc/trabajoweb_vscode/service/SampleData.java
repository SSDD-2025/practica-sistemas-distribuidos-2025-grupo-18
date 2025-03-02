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

    @PostConstruct
    public void init(){
        Book HP = new Book("Harry Potter", 2019);
        bookRepository.save(HP);

    }


    
}
