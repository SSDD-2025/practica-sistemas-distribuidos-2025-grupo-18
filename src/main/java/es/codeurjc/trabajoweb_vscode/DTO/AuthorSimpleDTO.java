package es.codeurjc.trabajoweb_vscode.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record AuthorSimpleDTO(
        @JsonIgnore
        Long id,
        String name,
        String bio) {

}
