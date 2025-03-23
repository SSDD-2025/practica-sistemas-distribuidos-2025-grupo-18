package es.codeurjc.trabajoweb_vscode.model;


import java.util.List;

import jakarta.persistence.*;

@Entity
public class Author {

    //SIMPLIFICADO
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bio;

    @Column(unique = true, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Book> books;



    //////////////CONSRTUCTORS///////////////



    public Author()
    {}


    ///////////GET AND SET////////////
    

    public List<Book> getBooks() {
        return books;
    }
    public Long getId() {
        return id;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String nationality) {
        this.bio = nationality;
    }

}