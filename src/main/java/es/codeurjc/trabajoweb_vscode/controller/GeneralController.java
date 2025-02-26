package es.codeurjc.trabajoweb_vscode.controller;

//import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GeneralController {

   @GetMapping("/")
	public String getPosts(){
		return "index";
	}


    @GetMapping("/error")
	public String ADIOS(){
		return "error";
	}
    
    
}
