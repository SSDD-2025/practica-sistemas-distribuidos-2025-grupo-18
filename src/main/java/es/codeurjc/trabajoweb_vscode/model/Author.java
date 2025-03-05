package es.codeurjc.trabajoweb_vscode.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String nacionality;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Book> books;

    public Author (String name){
        this.name = name;
    }

    public Author()
    {}

    public List<Book> getBooks() {
        return books;
    }
    public Long getId() {
        return id;
    }
    public String getNacionality() {
        return nacionality;
    }
    public String getName() {
        return name;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }
    public void setName(String name) {
        this.name = name;
    }
}