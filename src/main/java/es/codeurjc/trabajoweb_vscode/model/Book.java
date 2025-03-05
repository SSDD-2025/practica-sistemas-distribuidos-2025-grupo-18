package es.codeurjc.trabajoweb_vscode.model;

import java.sql.Blob;
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
    @Lob
    private byte[] image;
    
    @ManyToOne
    private Author author;
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews;

    public Book (String name){
        this.name = name;
    }

    public Book (){}
    public Author getAuthor() {
        return author;
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
    public List<Review> getReviews() {
        return reviews;
    }
    public int getYearPub() {
        return yearPub;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public void setYearPub(int yearPub) {
        this.yearPub = yearPub;
    }

}