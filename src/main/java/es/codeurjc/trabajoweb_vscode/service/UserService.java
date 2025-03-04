package es.codeurjc.trabajoweb_vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.model.*;
import es.codeurjc.trabajoweb_vscode.repository.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

  
  
  public Long login(String name, String psw) {
    Optional<User> userOpt = userRepository.findByName(name);


    
    if (userOpt.isPresent() && userOpt.get().getName().equals(name) && userOpt.get().getPsw().equals(psw)) {
        return userOpt.get().getId();  // Retorna el ID del usuario autenticado
    } else {
        throw new RuntimeException("Credenciales inválidas");
    }
  }

  public void singup(String name, String psw) {
    User user = new User(name, psw);
    userRepository.save(user);
  }



}