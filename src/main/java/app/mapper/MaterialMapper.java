package app.mapper;

import app.dto.request.MaterialRequest;
import app.dto.response.MaterialResponse;
import app.entity.Material;
import app.entity.MaterialCategory;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {

    public Material toEntity(MaterialRequest request, MaterialCategory category){
        return Material.builder()
                .name(request.name())
                .unit(request.unit())
                .price(request.price())
                .consumptionRate(request.consumptionRate())
                .category(category)
                .build();
    }

    public MaterialResponse toResponse(Material material){
        return new MaterialResponse(
                material.getId(),
                material.getName(),
                material.getUnit(),
                material.getPrice(),
                material.getConsumptionRate(),
                material.getCategory().getName()
        );
    }
}
