package es.codeurjc.trabajoweb_vscode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLibro;

    private String nombreLibro;
    private int yearPub;
    private byte[] imagen;

    @OneToOne
    private String nombreAutor;

    public  Book() {}

    public  Book(String nombreLibro, int yearPub, byte[] imagen) {
        this.nombreLibro = nombreLibro;
        this.yearPub = yearPub;
        this.imagen = imagen;
    }



    public Book getInfoBook(String nombreLibro){

        Book newBook = new Book();
        if newBook.nombreLibro = nombr
        return newBook;

    }



    
    public Long getIdLibro() {
        return idLibro;
    }
    public byte[] getImagen() {
        return imagen;
    }
    public String getNombreAutor() {
        return nombreAutor;
    }
    public String getNombreLibro() {
        return nombreLibro;
    }
    public int getYearPub() {
        return yearPub;
    }
    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }
    public void setYearPub(int yearPub) {
        this.yearPub = yearPub;
    }

}
