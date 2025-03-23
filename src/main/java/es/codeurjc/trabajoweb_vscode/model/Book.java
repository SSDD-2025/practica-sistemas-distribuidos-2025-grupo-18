package es.codeurjc.trabajoweb_vscode.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Book {

    // SIMPLIFICADO

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int yearPub;

    @Lob
    private byte[] image;

    @ManyToOne
    private Author author;

    private String description;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    private String imageBase64;


    ////////////// CONSRTUCTORS///////////////

    public Book() {
    }

    /////////// GET AND SET////////////

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

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}