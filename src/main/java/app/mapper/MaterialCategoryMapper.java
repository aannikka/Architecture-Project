package app.mapper;

import app.dto.request.MaterialCategoryRequest;
import app.dto.response.MaterialCategoryResponse;
import app.entity.MaterialCategory;
import org.springframework.stereotype.Component;

@Component
public class MaterialCategoryMapper {

    public MaterialCategory toEntity(MaterialCategoryRequest request) {
        return MaterialCategory.builder()
                .name(request.name())
                .build();
    }

    public MaterialCategoryResponse toResponse(MaterialCategory category) {
        return new MaterialCategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}
