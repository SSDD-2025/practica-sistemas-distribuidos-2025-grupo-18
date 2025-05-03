package es.codeurjc.trabajoweb_vscode.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record AuthorDTO(
        @JsonIgnore
        Long id,
        String name,
        String bio,
        @JsonIgnore
        List<BookSimpleDTO> books) {

}
