package es.codeurjc.trabajoweb_vscode.DTO;

public record BookDTO(
Long id,
String name,
int yearPub,
AuthorSimpleDTO author
) {

}
