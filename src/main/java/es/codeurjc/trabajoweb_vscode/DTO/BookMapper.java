package es.codeurjc.trabajoweb_vscode.DTO;

import java.util.Base64;
import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.codeurjc.trabajoweb_vscode.model.Book;


@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDTO(Book book);

    @Mapping(target = "author", ignore = true)
    Book toDomain(BookDTO bookDTO);

    @Named("byteArrayToBase64")
    public static String byteArrayToBase64(byte[] image) {
        return image != null ? java.util.Base64.getEncoder().encodeToString(image) : null;
    }

    @Named("base64ToByteArray")
    public static byte[] base64ToByteArray(String base64) {
        return base64 != null ? java.util.Base64.getDecoder().decode(base64) : null;
    }

}
