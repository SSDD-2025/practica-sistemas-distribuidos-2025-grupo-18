package es.codeurjc.trabajoweb_vscode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.trabajoweb_vscode.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    List<User> findByNameContainingIgnoreCase(String name);

}