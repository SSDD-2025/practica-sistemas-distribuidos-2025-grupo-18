package es.codeurjc.trabajoweb_vscode.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record BookDTO(
        Long id,
        String name,
        int yearPub,
        @JsonIgnore
        AuthorSimpleDTO author
        ) {

}
