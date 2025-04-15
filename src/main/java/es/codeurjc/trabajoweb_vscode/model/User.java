package es.codeurjc.trabajoweb_vscode.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String encodedPassword;

    @OneToMany(mappedBy = "user", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<BookList> bookLists = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;
    
    //PARA SEGURIDAD VAMOS A ASIGNAR ROLES CON UNA TABLA HASH



    //////////////CONSRTUCTORS///////////////


    public User() {}
    public User(String name, String encodedPassword, String... roles) {
		this.name = name;
		this.encodedPassword = encodedPassword;
		this.roles = List.of(roles);
        BookList bookList = new BookList(this, "Mis libros favoritos");
        bookLists.add(bookList);
	}


    ///////////GET AND SET/////////////
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }
    
    public void setEncodedPassword(String password) {
        this.encodedPassword = password;
    }
    
    public List<BookList> getBookLists() {
        return bookLists;
    }
    
    public void setBookLists(List<BookList> bookLists) {
        this.bookLists = bookLists;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }



}