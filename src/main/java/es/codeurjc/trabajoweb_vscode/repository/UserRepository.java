package es.codeurjc.trabajoweb_vscode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.trabajoweb_vscode.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    List<User> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);
    Page<User> findByNameContainingIgnoreCase(String query, PageRequest of);

}