package es.codeurjc.trabajoweb_vscode.model;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class BookList {

        //SIMPLIFICADO

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    //////////////CONSRTUCTORS///////////////


    public BookList(){}


    ///////////GET AND SET////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
