package app.service;

import app.dto.request.EstimateItemRequest;
import app.dto.response.EstimateItemResponse;
import app.entity.Estimate;
import app.entity.EstimateItem;
import app.entity.Material;
import app.exception.EntityNotFoundException;
import app.mapper.EstimateItemMapper;
import app.repository.EstimateItemRepository;
import app.repository.EstimateRepository;
import app.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EstimateItemService {

    private final EstimateItemRepository estimateItemRepository;
    private final MaterialRepository materialRepository;
    private final EstimateRepository estimateRepository;
    private final EstimateItemMapper mapper;

    public EstimateItemResponse create(EstimateItemRequest request) {
        Material material = getMaterial(request.materialId());
        Estimate estimate = getEstimate(request.estimateId());
        EstimateItem estimateItem = mapper.toEntity(request,material,estimate);
        EstimateItem saved = estimateItemRepository.save(estimateItem);
        recalculateEstimateTotal(estimate);
        return mapper.toResponse(saved);
    }

    public EstimateItemResponse update(Long id, EstimateItemRequest request) {
        EstimateItem item = getEstimateItem(id);

        Estimate oldEstimate = item.getEstimate();

        Material material = getMaterial(request.materialId());
        Estimate estimate = getEstimate(request.estimateId());

        if (!item.getMaterial().getId().equals(material.getId())) {
            item.setMaterial(material);
            item.setUnitPrice(material.getPrice());
        }

        item.setQuantity(request.quantity());
        item.setEstimate(estimate);
        item.setTotalPrice(
                item.getUnitPrice().multiply(request.quantity())
        );

        EstimateItem updated = estimateItemRepository.save(item);
        recalculateEstimateTotal(oldEstimate);

        if (!oldEstimate.getId().equals(estimate.getId())) {
            recalculateEstimateTotal(estimate);
        }
        return mapper.toResponse(updated);
    }

    public EstimateItemResponse findById(Long id) {
        return mapper.toResponse(getEstimateItem(id));
    }

    public List<EstimateItemResponse> findAll() {
        return estimateItemRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public void deleteById(Long id) {
        EstimateItem item = getEstimateItem(id);
        Estimate estimate = item.getEstimate();
        estimateItemRepository.delete(item);
        recalculateEstimateTotal(estimate);
    }

    private Material getMaterial(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Material with id %d not found", id)));
    }

    private Estimate getEstimate(Long id) {
        return estimateRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
                String.format("Estimate with id %d not found", id)));
    }

    private EstimateItem getEstimateItem(Long id) {
        return estimateItemRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
                String.format("Estimate item with id %d not found", id)));
    }

    private void recalculateEstimateTotal(Estimate estimate) {
        BigDecimal total = estimateItemRepository
                .findAllByEstimateId(estimate.getId())
                .stream()
                .map(EstimateItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        estimate.setTotalPrice(total);
        estimateRepository.save(estimate);
    }
}
