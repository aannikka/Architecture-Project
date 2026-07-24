package app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjectRequest (
   @NotBlank
   String name,

   @NotBlank
   String description,

   @NotBlank
   String address,

   @NotNull
   Long userId
){}
