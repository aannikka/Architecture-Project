package app.dto.response;

import java.math.BigDecimal;

public record EstimateItemResponse (
    Long id,
    BigDecimal quantity,
    BigDecimal unitPrice,
    BigDecimal totalPrice,
    String material
){}
