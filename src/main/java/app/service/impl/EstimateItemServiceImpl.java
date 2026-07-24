package app.service.impl;

import app.entity.EstimateItem;
import app.exception.EntityNotFoundException;
import app.repository.EstimateItemRepository;
import app.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EstimateItemServiceImpl implements BaseService<EstimateItem> {

    private final EstimateItemRepository estimateItemRepository;

    @Override
    public EstimateItem create(EstimateItem estimateItem) {
        return estimateItemRepository.save(estimateItem);
    }

    @Override
    public EstimateItem update(Long id, EstimateItem estimateItem) {
        EstimateItem existingItem = findById(id);
        existingItem.setQuantity(estimateItem.getQuantity());
        existingItem.setUnitPrice(estimateItem.getUnitPrice());
        existingItem.setTotalPrice(estimateItem.getTotalPrice());
        existingItem.setMaterial(estimateItem.getMaterial());
        return estimateItemRepository.save(existingItem);
    }

    @Override
    public EstimateItem findById(Long id) {
        return estimateItemRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
                String.format("EstimateItem with id: %d not found", id)));
    }

    @Override
    public List<EstimateItem> findAll() {
        return estimateItemRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        estimateItemRepository.deleteById(id);
    }
}
