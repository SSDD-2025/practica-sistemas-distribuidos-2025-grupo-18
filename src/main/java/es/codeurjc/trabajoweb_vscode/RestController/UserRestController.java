package es.codeurjc.trabajoweb_vscode.RestController;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.trabajoweb_vscode.DTO.BookDTO;
import es.codeurjc.trabajoweb_vscode.DTO.BookSimpleDTO;
import es.codeurjc.trabajoweb_vscode.DTO.UserDTO;
import es.codeurjc.trabajoweb_vscode.DTO.UserMapper;
import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.repository.UserRepository;
import es.codeurjc.trabajoweb_vscode.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper mapper;

    UserRestController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	@GetMapping("/")
	public Page<UserDTO> getUsers(Pageable pageable) {
	
		return userRepository.findAll(pageable).map(mapper::toDTO);
	}

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return mapper.toDTO(userRepository.findById(id).orElseThrow());
    }

@PostMapping("/")
public ResponseEntity<User> createUser(@RequestBody User user) {
    user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword())); 
    userRepository.save(user);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(user.getId())
        .toUri();

    return ResponseEntity.created(location).body(user);
}

@DeleteMapping("/{id}")
public void deleteUser(@PathVariable long id) {
    userService.delete(id);
}

@PutMapping("/{id}")
public UserDTO replaceUser(@PathVariable long id, @RequestBody UserDTO updatedUserDTO) throws SQLException {

	return userService.replaceUser(id, updatedUserDTO);
}

}
