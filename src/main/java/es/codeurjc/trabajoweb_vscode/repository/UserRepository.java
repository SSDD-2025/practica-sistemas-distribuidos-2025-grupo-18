package es.codeurjc.trabajoweb_vscode.repository;

import es.codeurjc.trabajoweb_vscode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}