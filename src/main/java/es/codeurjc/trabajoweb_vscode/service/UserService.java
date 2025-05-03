package es.codeurjc.trabajoweb_vscode.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.DTO.UserDTO;
import es.codeurjc.trabajoweb_vscode.DTO.UserMapper;
import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.model.BookList;
import es.codeurjc.trabajoweb_vscode.model.Review;
import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.repository.UserRepository;
import es.codeurjc.trabajoweb_vscode.security.jwt.AuthResponse;
import es.codeurjc.trabajoweb_vscode.security.jwt.JwtTokenProvider;
import es.codeurjc.trabajoweb_vscode.security.jwt.LoginRequest;
import es.codeurjc.trabajoweb_vscode.security.jwt.TokenType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findByNameContainingIgnoreCase(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    public boolean alreadyExitsAReview(Long bookId, Long userId) {
        Book book = bookService.findById(bookId);
        User user = findById(userId);
        List<Review> reviews = user.getReviews();
        for (Review review : reviews) {
            if (Objects.equals(review.getBook().getId(), book.getId())) {
                return true;
            }
        }
        return false;

    }

    public boolean userHasList(Long id, Long listId) {
        User user = findById(id);
        if (user == null) {
            return false;
        }
        for (BookList bookList : user.getBookLists()) {
            if (Objects.equals(bookList.getId(), listId)) {
                return true;
            }
        }
        return false;
    }

    @Autowired
    UserMapper mapper;

    private UserDTO toDTO(User user) {
        return mapper.toDTO(user);
    }

    private User toDomain(UserDTO userDTO) {
        return mapper.toDomain(userDTO);
    }

    private Collection<UserDTO> toDTOs(Collection<User> users) {
        return mapper.toDTOs(users);
    }

    public UserDTO replaceUser(Long id, UserDTO userDTO) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(userDTO.name());
        existingUser.setRoles(userDTO.roles());

        User updatedUser = userRepository.save(existingUser);

        return new UserDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getRoles());
    }

    public User updateUserById(long id, User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow();

        user.setName(updatedUser.getName());
        User updated = userRepository.save(user);

        return updated;
    }

    public ResponseEntity<AuthResponse> logout(HttpServletResponse response) {
        try {

            Cookie refreshCookie = new Cookie(TokenType.REFRESH.cookieName, null);
            refreshCookie.setHttpOnly(true);
            refreshCookie.setPath("/");
            refreshCookie.setMaxAge(0);
            response.addCookie(refreshCookie);

            Cookie accessCookie = new Cookie(TokenType.ACCESS.cookieName, null);
            accessCookie.setHttpOnly(true);
            accessCookie.setPath("/");
            accessCookie.setMaxAge(0);
            response.addCookie(accessCookie);

            SecurityContextHolder.clearContext();

            return ResponseEntity.ok(new AuthResponse(AuthResponse.Status.SUCCESS, "Logout successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse(AuthResponse.Status.FAILURE, "Logout failed", e.getMessage()));
        }
    }

    /*public String  login(HttpServletResponse response, LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = userRepository.findByName(username);

        if (user != null && passwordEncoder.matches(password, user.getEncodedPassword())) {
            return user;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }*/
    public String login(HttpServletResponse response, LoginRequest loginRequest) {
      
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = jwtTokenProvider.generateAccessToken(userDetails);

        return jwt;
    }

    private static class jwtUtil {

        public jwtUtil() {
        }
    }

}
