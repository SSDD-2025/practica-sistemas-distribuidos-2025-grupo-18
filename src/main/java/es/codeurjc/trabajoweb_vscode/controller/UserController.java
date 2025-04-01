package es.codeurjc.trabajoweb_vscode.controller;

import es.codeurjc.trabajoweb_vscode.model.*;

import es.codeurjc.trabajoweb_vscode.service.*;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


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
        user.setName(name);  // Actualiza el nombre
        userService.save(user);  // Guarda los cambios
        return "redirect:/";  // Redirige de vuelta a la lista de usuarios
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