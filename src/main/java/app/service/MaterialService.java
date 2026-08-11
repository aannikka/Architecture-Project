package app.service;

import app.dto.request.MaterialRequest;
import app.dto.response.MaterialResponse;
import app.entity.Material;
import app.entity.MaterialCategory;
import app.exception.EntityNotFoundException;
import app.mapper.MaterialMapper;
import app.repository.MaterialCategoryRepository;
import app.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper mapper;
    private final MaterialCategoryRepository materialCategoryRepository;

    public MaterialResponse create(MaterialRequest request) {
        MaterialCategory category = getCategory(request.categoryId());
        Material material = mapper.toEntity(request, category);
        Material saved = materialRepository.save(material);
        return mapper.toResponse(saved);
    }

    public MaterialResponse findById(Long id) {
        return mapper.toResponse(getMaterial(id));
    }

    public List<MaterialResponse> findAll() {
        return materialRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MaterialResponse update(Long id, MaterialRequest request) {
        Material material = getMaterial(id);
        MaterialCategory category = getCategory(request.categoryId());
        material.setName(request.name());
        material.setUnit(request.unit());
        material.setPrice(request.price());
        material.setConsumptionRate(request.consumptionRate());
        material.setCategory(category);
        Material updated = materialRepository.save(material);
        return mapper.toResponse(updated);
    }

    public void deleteById(Long id) {
        materialRepository.delete(getMaterial(id));
    }

    private Material getMaterial(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Material with id %d not found", id)));
    }

    private MaterialCategory getCategory(Long id) {
        return materialCategoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Category with id %d not found", id)));
    }
}
