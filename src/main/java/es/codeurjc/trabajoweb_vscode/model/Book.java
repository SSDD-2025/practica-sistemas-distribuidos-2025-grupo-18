package es.codeurjc.trabajoweb_vscode.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private byte[] image; 

    private String imageBase64;

    private String name;

    private int yearPub;

    @ManyToOne
    private Author author;

    private String description;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews;


    ////////////// CONSRTUCTORS///////////////

    public Book() {
    }

    public Book(String name, int yearPub, Author author, String description) {
        this.name = name;
        this.yearPub = yearPub;
        this.author = author;
        this.description = description;
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


    public boolean hasImageBlob() {
        return image != null && image.length > 0;
    }

    public Book orElseThrow(Object object) {
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }

}

