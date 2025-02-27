package es.codeurjc.trabajoweb_vscode.controller;

import java.util.Optional;

import es.codeurjc.trabajoweb_vscode.model.*;
import es.codeurjc.trabajoweb_vscode.repository.*;
import es.codeurjc.trabajoweb_vscode.service.*;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class GeneralController {

	@Autowired
	private UserRepository userService;



   @GetMapping("/")
	public String getPosts(){
		return "index";
	}

	

	@GetMapping("/posts/{id}")
	public String getPost(Model model, @PathVariable long id) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			model.addAttribute("user", user.get());
			//String likedText = userService.isPostLikedByCurrentUser(post.get()) ? "Unlike" : "Like";
			//model.addAttribute("likedText", likedText);
			return "show_user";
		} else {
			return "user_not_found";
		}
	}


    @GetMapping("/error")
	public String ADIOS(){
		return "error";
	}
    
    
}
