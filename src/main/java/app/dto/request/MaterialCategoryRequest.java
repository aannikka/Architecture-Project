package app.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MaterialCategoryRequest(
     @NotBlank
     String name
) {}
