package app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record MaterialRequest (

    @NotBlank
    String name,

    @NotBlank
    String unit,

    @Positive
    BigDecimal price,

    @Positive
    double consumptionRate,

    @NotNull
    Long categoryId
) {}
