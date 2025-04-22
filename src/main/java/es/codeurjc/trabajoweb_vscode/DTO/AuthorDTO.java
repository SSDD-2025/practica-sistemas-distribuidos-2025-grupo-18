package es.codeurjc.trabajoweb_vscode.DTO;

import java.util.List;

public record AuthorDTO(
        Long id,
        String name,
        String bio,
        List<BookSimpleDTO> books) {

}
