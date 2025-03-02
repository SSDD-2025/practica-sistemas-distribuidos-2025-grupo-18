package es.codeurjc.trabajoweb_vscode.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;



@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int yearPub;
    private byte[] image;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    private List<User> users;

    public  Book() {}

    public  Book(String nombreLibro, int yearPub, byte[] imagen) {
        this.name = nombreLibro;
        this.yearPub = yearPub;
        this.image = imagen;
    }

    public  Book(String nombreLibro, int yearPub) {
        this.name = nombreLibro;
        this.yearPub = yearPub;
    }

    public Book getInfoBook(String nombreLibro){

        Book newBook = new Book();
        //if newBook.nombreLibro = nombr
        return newBook;

    }



    
    public Long getId() {
        return id;
    }
    public byte[] getImage() {
        return image;
    }
  
    public String getName() {
        return name;
    }
    public int getYearPub() {
        return yearPub;
    }

    public Author getAuthor() {
        return author;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public List<User> getUsers() {
        return users;
    }


    public void setId(Long idLibro) {
        this.id = idLibro;
    }
    public void setImage(byte[] imagen) {
        this.image = imagen;
    }

    public void setName(String nombreLibro) {
        this.name = nombreLibro;
    }
    public void setYearPub(int yearPub) {
        this.yearPub = yearPub;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
