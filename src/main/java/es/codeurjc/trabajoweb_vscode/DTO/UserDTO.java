package es.codeurjc.trabajoweb_vscode.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public record UserDTO (
    @JsonIgnore    
    Long id,
	String name,
	List<String> roles
    ) {
}
