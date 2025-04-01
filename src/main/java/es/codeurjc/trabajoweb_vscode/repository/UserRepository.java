package es.codeurjc.trabajoweb_vscode.repository;

import es.codeurjc.trabajoweb_vscode.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    List<User> findByNameContainingIgnoreCase(String name);

}