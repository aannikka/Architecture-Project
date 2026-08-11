package app.dto.shorts;

import java.math.BigDecimal;

public record EstimateItemShortResponse (
     Long id,
     String material,
     BigDecimal quantity,
     BigDecimal totalPrice
) {}
