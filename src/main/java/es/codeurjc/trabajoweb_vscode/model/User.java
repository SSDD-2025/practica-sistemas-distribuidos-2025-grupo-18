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
    private String rol;
    private String psw;

    public User() {}

    public User(String rol, String psw) {
        this.rol = rol;
        this.psw = psw;
    }





    public String getPsw() {
        return psw;
    }
    public String getRol() {
        return rol;
    }
    public Long getUserId() {
        return userId;
    }
    public void setPsw(String psw) {
        this.psw = psw;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
