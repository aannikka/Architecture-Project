package app.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequest (

      @NotBlank
      String username,

      @NotBlank
      String password,

      @NotBlank
      String email
){}
