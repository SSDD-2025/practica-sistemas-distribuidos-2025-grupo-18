package es.codeurjc.trabajoweb_vscode.RestController;

import java.net.URI;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.codeurjc.trabajoweb_vscode.DTO.UserDTO;
import es.codeurjc.trabajoweb_vscode.DTO.UserMapper;
import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.repository.UserRepository;
import es.codeurjc.trabajoweb_vscode.security.jwt.AuthResponse;
import es.codeurjc.trabajoweb_vscode.security.jwt.LoginRequest;
import es.codeurjc.trabajoweb_vscode.service.UserService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public User login(
            @RequestBody LoginRequest loginRequest,
            HttpServletResponse response) {

        return userService.login(response, loginRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(
            @CookieValue(name = "RefreshToken", required = false) String refreshToken, HttpServletResponse response) {

        return userService.refresh(response, refreshToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logOut(HttpServletResponse response) {
        return ResponseEntity.ok(userService.logout(response));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        if (userRepository.findByName(userDTO.name()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Ya existe un usuario con ese nombre");
        }

        User user = userMapper.toDomain(userDTO);
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDTO(savedUser));
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

        return userService.updateUserById(id, updatedUserDTO);
    }

}
