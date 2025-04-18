package es.codeurjc.trabajoweb_vscode.DTO;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.codeurjc.trabajoweb_vscode.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDTO(Book book);

    List<BookDTO> toDTOs(Collection<Book> books);
    
    @Mapping(target = "author", ignore = true)
    Book toDomain(BookDTO bookDTO);
}
