package app.dto.response;

public record UserResponse (
    Long id,
    String username,
    String email
){}
