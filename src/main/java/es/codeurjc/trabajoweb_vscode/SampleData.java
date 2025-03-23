package es.codeurjc.trabajoweb_vscode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostConstruct
    public void init() {
        try {

            Author author1 = new Author();
            author1.setName("J.K. Rowling");
            author1.setBio("Es una escritora conocida por ser la autora de la serie Harry Potter, que ha superado los 500 millones de ejemplares vendidos");
            authorRepository.save(author1);

            Book book1 = new Book();
            book1.setName("Harry Potter 1");
            book1.setYearPub(1991);
            book1.setAuthor(author1);
            Path imagePath1 = Paths.get("src/main/resources/static/images/HP1.jpg"); 
            byte[] imageBytes1 = Files.readAllBytes(imagePath1);
            book1.setImage(imageBytes1);
            book1.setDescription("Harry Potter recibe una carta que cambiará su vida para siempre. En ella le comunican que ha sido aceptado como alumno en el Colegio Hogwarts de Magia.");
            bookRepository.save(book1);


            Book book2 = new Book();
            book2.setName("Harry Potter 2");
            book2.setYearPub(1992);
            book2.setAuthor(author1);
            Path imagePath2 = Paths.get("src/main/resources/static/images/HP2.jpg");
            byte[] imageBytes2 = Files.readAllBytes(imagePath2);
            book2.setImage(imageBytes2);
            bookRepository.save(book2);

        
            /*Book book3 = new Book();
            book3.setName("Harry Potter 3");
            book3.setYearPub(1993);
            book3.setAuthor(author1);
            Path imagePath3 = Paths.get("src/main/resources/static/images/HP3.jpg"); 
            byte[] imageBytes3 = Files.readAllBytes(imagePath3);
            book3.setImage(imageBytes3);
            bookRepository.save(book3);*/


            Author author2 = new Author();
            author2.setName("Suzanne Collins");
            author2.setBio("Es una escritora y guionista estadounidense, creadora de la famosa serie de Los juegos del hambre.");
            authorRepository.save(author2);

            Book book3 = new Book();
            book3.setName("Juegos del Hambre");
            book3.setYearPub(2005);
            book3.setAuthor(author2);
            Path imagePath3 = Paths.get("src/main/resources/static/images/JDH.jpg"); 
            byte[] imageBytes3 = Files.readAllBytes(imagePath3);
            book3.setImage(imageBytes3);
            book3.setDescription("Panem organiza cada año Los juegos del hambre. En ellos, 24 jóvenes compiten en una batalla en la que solo puede haber un superviviente.");
            bookRepository.save(book3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
