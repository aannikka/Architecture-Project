package app.service;

import app.dto.request.MaterialCategoryRequest;
import app.dto.response.MaterialCategoryResponse;
import app.entity.MaterialCategory;
import app.exception.EntityNotFoundException;
import app.mapper.MaterialCategoryMapper;
import app.repository.MaterialCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MaterialCategoryService {

    private final MaterialCategoryRepository materialCategoryRepository;
    private final MaterialCategoryMapper mapper;

    public MaterialCategoryResponse create(MaterialCategoryRequest request) {
        MaterialCategory category = mapper.toEntity(request);
        MaterialCategory saved = materialCategoryRepository.save(category);
        return mapper.toResponse(saved);
    }

    public MaterialCategoryResponse findById(Long id) {
        MaterialCategory category = getCategory(id);
        return mapper.toResponse(category);
    }

    public List<MaterialCategoryResponse> findAll() {
        return materialCategoryRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MaterialCategoryResponse update(Long id, MaterialCategoryRequest request) {
        MaterialCategory category = getCategory(id);
        category.setName(request.name());
        MaterialCategory updated = materialCategoryRepository.save(category);
        return mapper.toResponse(updated);
    }

    public void deleteById(Long id) {
        materialCategoryRepository.delete(getCategory(id));
    }

    private MaterialCategory getCategory(Long id) {
        return materialCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(
                                "Material category with id %d not found",
                                id
                        )
                ));
    }
}