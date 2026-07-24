package app.dto.response;

import java.math.BigDecimal;

public record MaterialResponse (
    Long id,
    String name,
    String unit,
    BigDecimal price,
    double consumptionRate,
    String category
) {}
