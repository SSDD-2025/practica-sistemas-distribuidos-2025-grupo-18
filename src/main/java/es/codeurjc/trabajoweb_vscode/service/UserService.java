package es.codeurjc.trabajoweb_vscode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findByNameContainingIgnoreCase(String name){
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public boolean existsByName(String name){
        return userRepository.existsByName(name);
    }
}