package es.codeurjc.trabajoweb_vscode.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

  
    @ManyToOne
    private Author author;

    @Column(name = "yearPub")
    private int yearPub;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private Blob image;
  

    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    private List<User> users;

    public Book() {
    }



    public Book(String nombreLibro, int yearPub, Author autor, Blob imagen, String descripcion) {
        this.name = nombreLibro;
        this.yearPub = yearPub;
        this.image = imagen;
        this.description = descripcion;
        this.author = autor;

    }

    public Book(String nombreLibro, int yearPub) {
        this.name = nombreLibro;
        this.yearPub = yearPub;
    }

    public Book getInfoBook(String nombreLibro) {

        Book newBook = new Book();
        // if newBook.nombreLibro = nombr
        return newBook;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombreLibro) {
        this.name = nombreLibro;
    }


    public int getYearPub() {
        return yearPub;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public void setYearPub(int yearPub) {
        this.yearPub = yearPub;
    }


    public Blob getImage() {
        return image;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }
    public String getDescription() {
        return description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<User> getUsers() {
        return users;
    }


    public void setImage(Blob imagen) {
        this.image = imagen;
    }



    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
