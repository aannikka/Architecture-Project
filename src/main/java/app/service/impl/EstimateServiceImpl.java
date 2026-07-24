package app.service.impl;

import app.entity.Estimate;
import app.exception.EntityNotFoundException;
import app.repository.EstimateRepository;
import app.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EstimateServiceImpl implements BaseService<Estimate> {

    private final EstimateRepository estimateRepository;

    @Override
    public Estimate create(Estimate estimate) {
        return estimateRepository.save(estimate);
    }

    @Override
    public Estimate findById(Long id) {
        return estimateRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
                String.format("Estimate with id %d not found", id)));
    }

    @Override
    public Estimate update(Long id, Estimate estimate) {
        Estimate existingEstimate = findById(id);
        existingEstimate.setTotalPrice(estimate.getTotalPrice());
        existingEstimate.setProject(estimate.getProject());
        return estimateRepository.save(existingEstimate);
    }

    @Override
    public List<Estimate> findAll() {
        return estimateRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        estimateRepository.deleteById(id);
    }
}
