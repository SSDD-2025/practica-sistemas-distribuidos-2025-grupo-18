package es.codeurjc.trabajoweb_vscode.DTO;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import es.codeurjc.trabajoweb_vscode.model.Author;


@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO toDTO(Author author);

    List<AuthorDTO> toDTOs(Collection<Author> authors);

    @Mapping(target = "books", ignore = true)
    Author toDomain(AuthorDTO authorDTO);
}