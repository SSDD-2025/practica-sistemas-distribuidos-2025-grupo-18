package es.codeurjc.trabajoweb_vscode.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int rate;
    private String textReview;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;


    public Review() {}

    public Review( int valoracion, String textReview) {
        this.rate = valoracion;
        this.textReview = textReview;

    }





    @Override
    public String toString(){
        return textReview;
    }



    public Long getId() {
        return id;
    }
    public String getTextReview() {
        return textReview;
    }
    public int getRate() {
        return rate;
    }

    public Book getBook() {
        return book;
    }
    public User getUser() {
        return user;
    }

    public void setId(Long idReview) {
        this.id = idReview;
    }
    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }
    public void setRate(int valoracion) {
        this.rate = valoracion;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
