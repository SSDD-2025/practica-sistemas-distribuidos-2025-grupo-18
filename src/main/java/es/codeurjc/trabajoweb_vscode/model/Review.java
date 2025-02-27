package es.codeurjc.trabajoweb_vscode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReview;
    private Long idLibro;
    private int valoracion;
    private String textReview;

    public Review() {}

    public Review(Long idLibro, int valoracion, String textReview) {
        this.idLibro = idLibro;
        this.valoracion = valoracion;
        this.textReview = textReview;

    }




    
    public Long getIdLibro() {
        return idLibro;
    }
    public Long getIdReview() {
        return idReview;
    }
    public String getTextReview() {
        return textReview;
    }
    public int getValoracion() {
        return valoracion;
    }
    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }
    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }
    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

}
