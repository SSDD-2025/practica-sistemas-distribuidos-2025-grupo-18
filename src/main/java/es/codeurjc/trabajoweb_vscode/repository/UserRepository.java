package es.codeurjc.trabajoweb_vscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import es.codeurjc.trabajoweb_vscode.model.User;

public interface UserRepository extends JpaRepository<User,Long>  {
    
    Optional<User> findByName(String name); 


}
