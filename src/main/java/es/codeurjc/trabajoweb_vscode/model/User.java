package es.codeurjc.trabajoweb_vscode.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity (name = "UserTable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String psw;
    
    @ManyToMany(mappedBy = "users")
	private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    public User() {}

    public User(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }


    public String getName() {
        return name;
    }
    public String getPsw() {
        return psw;
    }

    public Long getId() {
        return id;
    }
    public List<Book> getBooks() {
        return books;
    }
    public List<Review> getReviews() {
        return reviews;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setPsw(String psw) {
        this.psw = psw;
    }
  
    public void setId(Long userId) {
        this.id = userId;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
