package app.mapper;

import app.dto.request.EstimateItemRequest;
import app.dto.response.EstimateItemResponse;
import app.entity.Estimate;
import app.entity.EstimateItem;
import app.entity.Material;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EstimateItemMapper {

    public EstimateItem toEntity(EstimateItemRequest request, Material material, Estimate estimate) {

        BigDecimal unitPrice = material.getPrice();

        BigDecimal totalPrice = unitPrice
                .multiply(request.quantity());


        return EstimateItem.builder()
                .quantity(request.quantity())
                .unitPrice(unitPrice)
                .totalPrice(totalPrice)
                .material(material)
                .estimate(estimate)
                .build();
    }

    public EstimateItemResponse toResponse(EstimateItem estimateItem) {
        return new EstimateItemResponse(
                estimateItem.getId(),
                estimateItem.getQuantity(),
                estimateItem.getUnitPrice(),
                estimateItem.getTotalPrice(),
                estimateItem.getMaterial().getName()
        );
    }
}
