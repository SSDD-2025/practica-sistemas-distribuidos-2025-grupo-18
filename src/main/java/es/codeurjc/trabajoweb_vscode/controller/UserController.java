package es.codeurjc.trabajoweb_vscode.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.trabajoweb_vscode.model.User;
import es.codeurjc.trabajoweb_vscode.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("user", userService.findByName(principal.getName()));

        } else {
            model.addAttribute("logged", false);
            model.addAttribute("admin", false);
        }
    }

    @GetMapping("")
    public String showInfo(Model model, Principal principal, HttpServletRequest request) {
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);

        return "user-info";
    }

    @PostMapping("/change-name")
    public String changeName(@RequestParam String username, Principal principal, Model model) {
        User user = userService.findByName(principal.getName());

        if (username.isEmpty()) {
            model.addAttribute("error", "El nombre de usuario no puede estar vacío");
            return "user-info";
        }

        if (userService.existsByName(username) && !user.getName().equals(username)) {
            model.addAttribute("error", "Este nombre de usuario no está disponible");
            return "user-info";
        }

        user.setName(username);
        userService.save(user);
        return "redirect:/user";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String password, Principal principal) {
        User user = userService.findByName(principal.getName());

        user.setEncodedPassword(passwordEncoder.encode(password));
        userService.save(user);
        return "redirect:/user";
    }

    @PostMapping("/delete")
    public String deleteUser(Model model, Principal principal) {
        User user = userService.findByName(principal.getName());
        if (user != null) {

            userService.delete(user.getId());

        }
        return "redirect:/user";
    }

    @GetMapping("/book-lists")
    public String getBookLists(Model model, Principal principal, HttpServletRequest request) {
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        return "user-lists";
    }

    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        User user = userService.findById(id);

        model.addAttribute("user", user);
        return "user-details";
    }

    //NO HE HECHO CAMBIOS
    @PostMapping("/save")
    public String save(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit-user/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "edit-user";  // Vista para editar usuario
    }

    // Procesar el formulario de edición de usuario
    @PostMapping("/edit-user/{id}")
    public String editUser(@PathVariable Long id, @RequestParam String name) {
        User user = userService.findById(id);

        /*if(userService.findByName(name) != null) {
            model.addAttribute("errorMessage", "El nombre ya está en uso.");
            model.addAttribute("user", user); 
            return "redirect:/user/edit-user/" + id;  
        }*/
        user.setName(name);
        userService.save(user);
        return "redirect:/";
    }

    // Mostrar formulario de creación de usuario
    @GetMapping("/add")
    public String showAddUserForm() {
        return "add-user";
    }

    /*  Guardar el nuevo usuario
    @PostMapping("/add")
    public String addUser(@RequestParam String name) {
        User user = new User(name);
        userService.save(user);
        return "redirect:/users";
    }
*
    // Eliminar un usuario
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }*/
}
