package es.codeurjc.trabajoweb_vscode.model;

import jakarta.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String nombreAutor;
    private String nacionalidad;

    public  Author() {}

    public  Author(String nombreAutor, String nacionalidad) {
        this.nombreAutor = nombreAutor;
        this.nacionalidad = nacionalidad;
    }
    



    


    public String getNacionalidad() {
        return nacionalidad;
    }
    public String getNombreAutor() {
        return nombreAutor;
    }
    public Long getUserId() {
        return userId;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
