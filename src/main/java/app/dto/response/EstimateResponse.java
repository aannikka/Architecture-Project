package app.dto.response;

import app.dto.shorts.EstimateItemShortResponse;

import java.math.BigDecimal;
import java.util.List;

public record EstimateResponse(
    Long id,
    BigDecimal totalPrice,
    Long projectId,
    List<EstimateItemShortResponse> items
) {}
