package es.codeurjc.trabajoweb_vscode.DTO;

import java.util.List;


public record UserDTO (    
    Long id,
	String name,
	List<String> roles
    ) {
}
