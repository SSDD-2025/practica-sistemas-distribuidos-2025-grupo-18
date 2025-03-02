package es.codeurjc.trabajoweb_vscode.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String nacionality;
    
    @OneToMany(mappedBy = "author")
	private List<Book> books = new ArrayList<>();

    public  Author() {}

    public  Author(String nombreAutor, String nacionalidad) {
        this.name = nombreAutor;
        this.nacionality = nacionalidad;
    }
    




    public List<Book> getBooks() {
        return books;
    }
    public String getNacionality() {
        return nacionality;
    }
    public String getName() {
        return name;
    }
    public Long getId() {
        return id;
    }
    public void setBooks(List<Book> comments) {
        this.books = comments;
    }
    public void setNacionality(String nacionalidad) {
        this.nacionality = nacionalidad;
    }
    public void setName(String nombreAutor) {
        this.name = nombreAutor;
    }
    public void setId(Long userId) {
        this.id = userId;
    }

}
