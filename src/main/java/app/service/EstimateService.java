package app.service;

import app.dto.request.EstimateRequest;
import app.dto.response.EstimateResponse;
import app.entity.Estimate;
import app.entity.Project;
import app.exception.EntityNotFoundException;
import app.mapper.EstimateMapper;
import app.repository.EstimateRepository;
import app.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EstimateService {

    private final EstimateRepository estimateRepository;
    private final ProjectRepository projectRepository;
    private final EstimateMapper mapper;

    public EstimateResponse create(EstimateRequest request) {
        Project project = getProject(request.projectId());
        Estimate estimate = mapper.toEntity(project);
        Estimate saved = estimateRepository.save(estimate);
        return mapper.toResponse(saved);
    }

    public EstimateResponse findById(Long id) {
        return mapper.toResponse(getEstimate(id));
    }

    public EstimateResponse update(Long id, EstimateRequest request) {
        Estimate estimate = getEstimate(id);
        Project project = getProject(request.projectId());
        estimate.setProject(project);
        Estimate updated =  estimateRepository.save(estimate);
        return mapper.toResponse(updated);
    }

    public List<EstimateResponse> findAll() {
        return estimateRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public void deleteById(Long id) {
        estimateRepository.delete(getEstimate(id));
    }

    private Project getProject(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Project with id %d not found", id)));
    }

    private Estimate getEstimate(Long id) {
        return estimateRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
                String.format("Estimate with id %d not found", id)));
    }
}
