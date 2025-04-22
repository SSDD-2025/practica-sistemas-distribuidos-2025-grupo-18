package es.codeurjc.trabajoweb_vscode.DTO;

public record ReviewSimpleDTO (
    int rate,
    String textReview,
    Long bookId,
    Long userId)
{
}
