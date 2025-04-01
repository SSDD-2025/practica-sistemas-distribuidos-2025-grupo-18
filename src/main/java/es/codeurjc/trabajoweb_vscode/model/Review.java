package es.codeurjc.trabajoweb_vscode.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Review {

        //SIMPLIFICADO

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private int rate;
    
    private String textReview;
    
    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    
    //////////////CONSRTUCTORS///////////////

    public Review(){}
    
    public Review(int rate, String textReview, Book book, User user) {
        if (rate >= 0 && rate <= 5)
        this.rate = rate;
        this.textReview = textReview;
        this.book = book;
        this.user = user;
    }

    ///////////GET AND SET/////////////
    public Book getBook() {
        return book;
    }
    public Long getId() {
        return id;
    }
    public int getRate() {
        return rate;
    }
    public String getTextReview() {
        return textReview;
    }
    public User getUser() {
        return user;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setRate(int rate) {
        if (rate >= 0 && rate <= 5)
        this.rate = rate;
    }
    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


