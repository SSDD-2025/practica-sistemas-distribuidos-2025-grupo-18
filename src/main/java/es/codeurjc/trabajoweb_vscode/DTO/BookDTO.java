package es.codeurjc.trabajoweb_vscode.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record BookDTO(
        @JsonIgnore
        Long id,
        String name,
        int yearPub,
        AuthorSimpleDTO author
        ) {

}
