package es.codeurjc.trabajoweb_vscode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLibro;

    private String nombreLibro;
    private String nombreAutor;
    private int yearPub;
    private byte[] imagen;

    public  Book() {}

    public  Book(String nombreLibro, String nombreAutor, int yearPub, byte[] imagen) {
        this.nombreLibro = nombreLibro;
        this.nombreAutor = nombreAutor;
        this.yearPub = yearPub;
        this.imagen = imagen;
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
