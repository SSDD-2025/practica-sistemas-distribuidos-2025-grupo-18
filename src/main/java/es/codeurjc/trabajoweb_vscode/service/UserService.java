package es.codeurjc.trabajoweb_vscode.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.trabajoweb_vscode.DTO.UserDTO;
import es.codeurjc.trabajoweb_vscode.DTO.UserMapper;
import es.codeurjc.trabajoweb_vscode.model.Book;
import es.codeurjc.trabajoweb_vscode.model.BookList;
import es.codeurjc.trabajoweb_vscode.model.Review;
import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;


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

    public List<User> findByNameContainingIgnoreCase(String name){
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public boolean existsByName(String name){
        return userRepository.existsByName(name);
    }
    public boolean alreadyExitsAReview(Long bookId, Long userId){
        Book book = bookService.findById(bookId);
        User user = findById(userId);
        List <Review> reviews = user.getReviews();
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

}