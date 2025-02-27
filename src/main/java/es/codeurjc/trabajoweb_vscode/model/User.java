package es.codeurjc.trabajoweb_vscode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String name;
    private String psw;
    

    public User() {}

    public User(int rol,String name, String psw) {
        this.rol = rol;
        this.name = name;
        this.psw = psw;
    }


    public String getName() {
        return name;
    }
    public String getPsw() {
        return psw;
    }

    public Long getUserId() {
        return userId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPsw(String psw) {
        this.psw = psw;
    }
    public void setRol(int rol) {
        this.rol = rol;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
