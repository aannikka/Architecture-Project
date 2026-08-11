package app.service;

import app.dto.request.BuildingRequest;
import app.dto.response.BuildingResponse;
import app.entity.Building;
import app.entity.Project;
import app.exception.EntityNotFoundException;
import app.mapper.BuildingMapper;
import app.repository.BuildingRepository;
import app.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final ProjectRepository projectRepository;
    private final BuildingMapper mapper;

    public BuildingResponse create(BuildingRequest request) {
        Project project = getProject(request.projectId());
        Building building = mapper.toEntity(request, project);
        Building saved = buildingRepository.save(building);
        return mapper.toResponse(saved);
    }

    public BuildingResponse update(Long id, BuildingRequest request) {
        Building building = getBuilding(id);
        Project project = getProject(request.projectId());
        building.setLength(request.length());
        building.setWidth(request.width());
        building.setFloors(request.floors());
        building.setFloorHeight(request.floorHeight());
        building.setWallThickness(request.wallThickness());
        building.setRoofType(request.roofType());
        building.setFoundationType(request.foundationType());
        building.setProject(project);
        Building updated = buildingRepository.save(building);
        return mapper.toResponse(updated);
    }

    public BuildingResponse findById(Long id) {
        return mapper.toResponse(getBuilding(id));
    }

    public List<BuildingResponse> findAll() {
        return buildingRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public void deleteById(Long id) {
        buildingRepository.delete(getBuilding(id));
    }

    private Building getBuilding(Long id) {
        return buildingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Building with id %d not found", id)));
    }

    private Project getProject(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Project with id %d not found", id)));
    }
}
