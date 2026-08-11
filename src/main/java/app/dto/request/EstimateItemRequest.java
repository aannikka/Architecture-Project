package app.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record EstimateItemRequest(
     @NotNull
     @Positive
     BigDecimal quantity,

     @NotNull
     Long materialId,

     @NotNull
     Long estimateId
) {}
