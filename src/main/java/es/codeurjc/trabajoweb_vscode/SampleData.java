package es.codeurjc.trabajoweb_vscode;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.model.Author;
import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.model.Review;
import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.repository.AuthorRepository;
import es.codeurjc.trabajoweb_vscode.repository.BookRepository;
import es.codeurjc.trabajoweb_vscode.repository.ReviewRepository;
import es.codeurjc.trabajoweb_vscode.repository.UserRepository;
import jakarta.annotation.PostConstruct;

@Service
public class SampleData {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        try {
            // Autores
            Author author1 = authorRepository.findByName("J.K. Rowling")
                .orElseGet(() -> authorRepository.save(new Author(
                    "J.K. Rowling",
                    "Es una escritora conocida por ser la autora de la serie Harry Potter, que ha superado los 500 millones de ejemplares vendidos"
                )));

            Author author2 = authorRepository.findByName("Suzanne Collins")
                .orElseGet(() -> authorRepository.save(new Author(
                    "Suzanne Collins",
                    "Es una escritora y guionista estadounidense, creadora de la famosa serie de Los juegos del hambre."
                )));

            // Libros
            if (!bookRepository.existsByName("Harry Potter 1")) {
                Book book1 = new Book("Harry Potter 1", 1991, author1,
                    "Harry Potter recibe una carta que cambiará su vida para siempre...");
                book1.setImage(loadImage("static/images/HP1.jpg"));
                bookRepository.save(book1);
            }

            if (!bookRepository.existsByName("Harry Potter 2")) {
                Book book2 = new Book("Harry Potter 2", 1992, author1,
                    "Tras derrotar una vez más a lord Voldemort...");
                book2.setImage(loadImage("static/images/HP2.jpg"));
                bookRepository.save(book2);
            }

            if (!bookRepository.existsByName("Juegos del Hambre")) {
                Book book3 = new Book("Juegos del Hambre", 2005, author2,
                    "Panem organiza cada año Los juegos del hambre...");
                book3.setImage(loadImage("static/images/JDH.jpg"));
                bookRepository.save(book3);
            }

            // Usuarios
            createUserIfNotExists("user", "pass", "USER");
            createUserIfNotExists("user2", "pass2", "USER");
            createUserIfNotExists("admin", "admin", "ADMIN");

            // Reseña
            Optional<User> user = userRepository.findByName("user");
            Optional<Book> book = bookRepository.findByName("Harry Potter 1");
            if (user.isPresent() && book.isPresent() && reviewRepository.count() == 0) {
                reviewRepository.save(new Review(3, "Está bien", book.get(), user.get()));
            }

            System.out.println("=== Datos de ejemplo cargados correctamente ===");

        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(SampleData.class)
                .error("Error al inicializar los datos de ejemplo", e);
        }
    }

    private byte[] loadImage(String path) throws Exception {
        ClassPathResource resource = new ClassPathResource(path);
        try (InputStream in = resource.getInputStream()) {
            return in.readAllBytes();
        }
    }

    private void createUserIfNotExists(String username, String password, String role) {
        if (!userRepository.existsByName(username)) {
            userRepository.save(new User(username, passwordEncoder.encode(password), role));
        }
    }
}
