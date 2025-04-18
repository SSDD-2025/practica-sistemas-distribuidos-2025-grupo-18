package es.codeurjc.trabajoweb_vscode.DTO;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.codeurjc.trabajoweb_vscode.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

UserDTO toDTO(User user);

@Mapping(target = "encodedPassword")
@Mapping(target = "bookLists")
List<UserDTO> toDTOs(Collection<User> users);


@Mapping(target = "encodedPassword", ignore = true)
User toDomain(UserDTO userDTO);
}