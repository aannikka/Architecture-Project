package app.service.impl;

import app.entity.MaterialCategory;
import app.exception.EntityNotFoundException;
import app.repository.MaterialCategoryRepository;

import app.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MaterialCategoryServiceImpl implements BaseService<MaterialCategory> {

    private final MaterialCategoryRepository materialCategoryRepository;

    @Override
    public MaterialCategory create(MaterialCategory materialCategory) {
        return materialCategoryRepository.save(materialCategory);
    }

    @Override
    public MaterialCategory update(Long id, MaterialCategory materialCategory) {
       MaterialCategory existingCategory = findById(id);
       existingCategory.setName(materialCategory.getName());
       return materialCategoryRepository.save(existingCategory);
    }

    @Override
    public MaterialCategory findById(Long id) {
        return materialCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Material category with id %d not found", id)));
    }

    @Override
    public List<MaterialCategory> findAll() {
        return materialCategoryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        materialCategoryRepository.deleteById(id);
    }
}
