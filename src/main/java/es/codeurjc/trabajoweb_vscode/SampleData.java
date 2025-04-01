package es.codeurjc.trabajoweb_vscode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import es.codeurjc.trabajoweb_vscode.model.*;
import es.codeurjc.trabajoweb_vscode.repository.*;

//LO QUITO, LUEGO REALIZAREMOS PRUEBAS


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

            Author author1 = new Author("J.K. Rowling","Es una escritora conocida por ser la autora de la serie Harry Potter, que ha superado los 500 millones de ejemplares vendidos");
            authorRepository.save(author1);

            Book book1 = new Book("Harry Potter 1",1991,author1,"Harry Potter recibe una carta que cambiará su vida para siempre. En ella le comunican que ha sido aceptado como alumno en el Colegio Hogwarts de Magia.");
            Path imagePath1 = Paths.get("src/main/resources/static/images/HP1.jpg"); 
            byte[] imageBytes1 = Files.readAllBytes(imagePath1);
            book1.setImage(imageBytes1);
            bookRepository.save(book1);


            Book book2 = new Book("Harry Potter 2",1992,author1,"Tras derrotar una vez más a lord Voldemort, su siniestro enemigo en Harry Potter y la piedra filosofal, Harry espera impaciente en casa de sus insoportables tíos el inicio del segundo curso del Colegio Hogwarts de Magia.");
            Path imagePath2 = Paths.get("src/main/resources/static/images/HP2.jpg");
            byte[] imageBytes2 = Files.readAllBytes(imagePath2);
            book2.setImage(imageBytes2);
            bookRepository.save(book2);

            Author author2 = new Author("Suzanne Collins","Es una escritora y guionista estadounidense, creadora de la famosa serie de Los juegos del hambre.");
            authorRepository.save(author2);

            Book book3 = new Book("Juegos del Hambre",2005,author2,"Panem organiza cada año Los juegos del hambre. En ellos, 24 jóvenes compiten en una batalla en la que solo puede haber un superviviente.");
            Path imagePath3 = Paths.get("src/main/resources/static/images/JDH.jpg"); 
            byte[] imageBytes3 = Files.readAllBytes(imagePath3);
            book3.setImage(imageBytes3);
            bookRepository.save(book3);

            User user1 = new User("user", passwordEncoder.encode("pass"), "USER");
            userRepository.save(user1);

            Review review1 = new Review(3, "Esta bien",book1,user1);
            reviewRepository.save(review1);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
