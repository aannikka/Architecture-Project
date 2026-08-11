package app.mapper;

import app.dto.request.EstimateRequest;
import app.dto.response.EstimateResponse;
import app.dto.shorts.EstimateItemShortResponse;
import app.entity.Estimate;
import app.entity.Project;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class EstimateMapper {

    public Estimate toEntity(Project project) {
        return Estimate.builder()
                .project(project)
                .totalPrice(BigDecimal.ZERO)
                .build();
    }

    public EstimateResponse toResponse(Estimate estimate) {
        List<EstimateItemShortResponse> items =
                estimate.getEstimateItems() == null
                        ? List.of()
                        : estimate.getEstimateItems()
                        .stream()
                        .map(item -> new EstimateItemShortResponse(
                                item.getId(),
                                item.getMaterial().getName(),
                                item.getQuantity(),
                                item.getTotalPrice()
                        ))
                        .toList();

        return new EstimateResponse(
                estimate.getId(),
                estimate.getTotalPrice(),
                estimate.getProject().getId(),
                items
        );
    }
}
